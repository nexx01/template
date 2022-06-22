package com.example.clientservice.feign;

import com.example.clientservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "book-service",  //имя удаленного сервиса
        url = "${book-service.url}"
        //        ,
//        configuration = FeignClient.class //configurations - это конфиг нашего Feign клиента
)
public interface BookServiceConnector {  //По имени BookConnector или например BookServiceConnectorFeignClient любой сможет
    // понять что это Feign клиент, который стучится на ServiceB.

    @GetMapping("/show")
    List<Book> getAllBooks();

}
