package bookservice.controller;

import bookservice.entity.Book;
import bookservice.service.BookService.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class BookController {

    public static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private BookService bookService;

    @RequestMapping("/")
    public String home() {
        String home = "Book-Service running at port: " + environment.getProperty("local.server.port");
        log.info(home);
        return home;
    }

    @GetMapping("/show")
    public List<Book> getAllBooksList() {
        log.info("Get data from database (Feign Client on client-service side");
        return bookService.findAllBooks();
    }

    @GetMapping("/data")
    public List<Book> data() {
        log.info("Get data from database ( RestTemplate on client-service side");
     return    bookService.findAllBooks();
    }
}
