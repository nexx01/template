package com.example.clientservice.controller;

import com.example.clientservice.model.Book;
import com.example.clientservice.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController
@Slf4j
@RequiredArgsConstructor
public class BookController {

    private final TestService testService;

    @GetMapping("/getAllBooksByFeignClient")
    public List<Book> getBooks() {
        log.info("-->getBookBbyFeign");
        return testService.getAllBooks();
    }

    @GetMapping("/getAllBooksByRestTemplate")
    public List<Book> getBooksRestTempate() {
        log.info("-->getBookBbyFeign");
        return testService.getAllBooksRestTemplate();
    }

}
