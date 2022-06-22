package configclient.controller;

import configclient.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController
public class BookController {

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return List.of(new Book("Война и Мир", "Л.Н.Толстой"),
                new Book("Преступление и Наказание", "Ф.М. Достоевский"));
    }

}
