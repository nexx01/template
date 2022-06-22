package com.example.clientservice.feign;

import com.example.clientservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "service-b",
        url = "${service-b.url}"
)
public interface BookClient {

    @GetMapping("/getAllBooksList")
    public List<Book> getAllBooks();

}
