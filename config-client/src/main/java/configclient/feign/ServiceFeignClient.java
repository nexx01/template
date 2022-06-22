package configclient.feign;

import configclient.entity.Book;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "service-b", url = "http://localhost:8084/")
//если вам нужно использовать внешнюю веб-службу, которая не является частью вашей архитектуры
// микросервисов и не зарегистрирована в вашей службе Eureka, то используйте URL в качестве параметра аннотации
// @FeignClient.
public interface ServiceFeignClient {

    @RequestLine("GET /getAllBooks")
    List<Book> getAllBooks();

    class FeignHolder {

        public static ServiceFeignClient create() {  //вложенный класс FeignHolder, в котором создаем Feign и
            // настраиваем его (encoder, decoder, путь к Сервису B, объявляем FallbackFactory в случае если что-то
            // пойдет не так) и далее в нем переопределяем тот самый метод, который мы хотим вызвать в Сервисе B.
            FallbackFactory<ServiceFeignClient> fallbackFactory = new FallbackFactory<ServiceFeignClient>() {
                @Override
                public ServiceFeignClient create(Throwable throwable) {
                    return new ServiceFeignClient() {
                        @Override
                        public List<Book> getAllBooks() {
                            System.out.println(throwable.getMessage());
                            throwable.printStackTrace();
                            return null;
                        }
                    };
                }

            };
            return HystrixFeign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder())
                    .target(ServiceFeignClient.class, "http://localhost:8084/", fallbackFactory);
        }
    }
}
