package configclient.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;

public class FeignConfig {

    @Bean
    public Decoder feignDecoder() {
        MappingJackson2CborHttpMessageConverter mappingJackson2CborHttpMessageConverter =
                new MappingJackson2CborHttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () ->
                new HttpMessageConverters(mappingJackson2CborHttpMessageConverter);
        return new ResponseEntityDecoder((new SpringDecoder(objectFactory))); // Мы можем указать из коробки декодер
        // для преобразования либо настроить свой. Я сделал это из коробки.
    }

    private ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

//    @Bean
//    public RequestInterceptor requestInterceptor(HeaderConverter headerConverter) {
//        return requestTemplate -> {
//            headerConverter.getCallContextHeaders().forEach(requestTemplate::header);
//            requestTemplate.header(MSAHeader.BusinessChannel.getName(), "DIGITAL");
//            requestTemplate.header(MSAHeader.BusinessSubChannel.getName(),"SPECIAL-HEADER");
//        };
//    }

}
