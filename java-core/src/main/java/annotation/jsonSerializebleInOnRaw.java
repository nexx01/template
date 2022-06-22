package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Retention(RetentionPolicy.RUNTIME) //Видимость времени выполнения
@Target(ElementType.TYPE) //применять к типам класса
@interface JsonSerializable {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Init {   //Мы объявили общедоступную аннотацию с видимостью во время выполнения, которую мы можем
        // применять к методам наших классов.

    }

}

//При создании пользовательских аннотаций с помощью методов мы должны знать, что эти методы не должны иметь
// параметров и не могут генерировать исключение. Кроме того, возвращаемые типы ограничены примитивами, строкой,
// классом, перечислениями, аннотациями и массивами этих типов, а значение по умолчанию не может быть null.


@Retention(RetentionPolicy.RUNTIME) //Видимость рантайм
@Target(ElementType.FIELD) //применяется к полю
@interface JsonElement {

    public String key() default "";  // строковый параметр с пустой строкой по умолчанию

}


public class jsonSerializebleInOnRaw {

}


//Применение аннотаций
@JsonSerializable
class Person {

    @JsonElement
    private String firstName;
    @JsonElement
    private String lastName;
    // @JsonElement(key = "personAge")
    private String age;
    private String address;

    public Person(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @JsonSerializable.Init
    private void initNames() {
        this.firstName = this.firstName.substring(0, 1).toUpperCase()
                + this.firstName.substring(1);
        this.lastName = this.lastName.substring(0, 1).toUpperCase()
                + this.lastName.substring(1);
    }

}


//    Обработка аннотаций
class ObjectToJsonConverter {

    public String convertToJson(Object object) throws JsonSerializationException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new JsonSerializationException(e.getMessage());
        }
    }

    //Первым шагом будет проверка, является ли наш объект null или нет, а также имеет ли его тип аннотацию
    // @JsonSerializable или нет:
    private void checkIfSerializable(Object object) {
        if (Objects.isNull(object)) {
            throw new JsonSerializationException("The object to serialize is null");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new JsonSerializationException("The class " + clazz.getSimpleName() + " id not annotated with " +
                    "JsonSerializable");
        }
    }

    //Затем мы ищем любой метод с аннотацией @Init и выполняем его для инициализации полей нашего объекта:
    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(JsonSerializable.Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    //После инициализации мы перебираем поля нашего объекта, извлекаем ключ и значение элементов JSON и помещаем их в
    // карту. Затем мы создаем строку JSON из карты:
    private String getJsonString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementsMap.put(getKey(field), (String) field.get(object));
            }
        }

        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\""
                        + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    private String getKey(Field field) {
        String value = field.getAnnotation(JsonElement.class)
                .key();
        return value.isEmpty() ? field.getName() : value;
    }

}


class JsonSerializationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JsonSerializationException(String message) {
        super(message);
    }

}
