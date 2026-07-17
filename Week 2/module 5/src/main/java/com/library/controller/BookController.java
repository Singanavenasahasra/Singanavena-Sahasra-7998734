package com.library.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.library.entity.Book;
import com.library.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    // No @Autowired needed here since it's the only constructor!
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.saveBook(book);
    }
}