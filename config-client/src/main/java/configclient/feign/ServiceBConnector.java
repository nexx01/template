package configclient.feign;

import configclient.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "service-b", //имя удаленного сервиса
        url = "${service-b.url}"
//        ,
//        configuration = FeignClient.class //configurations - это конфиг нашего Feign клиента
)
public interface ServiceBConnector { //По имени ServiceBConnector или например ServiceBFeignClient любой сможет
    // понять что это Feign клиент, который стучится на ServiceB.

    @GetMapping("/getAllBooks")
    List<Book> getAllBooks();
}
