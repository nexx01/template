package com.example.clientservice.service;

import com.example.clientservice.feign.BookServiceConnector;
import com.example.clientservice.model.Book;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    @Autowired
    BookServiceConnector bookServiceConnector;
    @Autowired
    RestTemplate restTemplate;

    public List<Book> getAllBooks() {
        return bookServiceConnector.getAllBooks();
    }

    public List<Book> getAllBooksRestTemplate() {
        List<Book> forObject = restTemplate.getForObject("http://book-service/data", List.class);
        log.info("{}",forObject);
        return forObject;
    }

}
