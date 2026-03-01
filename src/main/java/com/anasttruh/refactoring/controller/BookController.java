package com.anasttruh.refactoring.controller;

import com.anasttruh.refactoring.Book;
import com.anasttruh.refactoring.dto.BookListResponse;
import com.anasttruh.refactoring.dto.AddBookResponse;
import com.anasttruh.refactoring.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контроллер для управления книгами.
 */
@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * GET /api/books - получение списка книг с фильтрацией.
     */
    @GetMapping("/books")
    public ResponseEntity<BookListResponse> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title
    ) {
        List<Book> books = bookService.findAll(author, title);
        System.out.println("✅ GET /api/books processed, returned " + books.size() + " books");
        return ResponseEntity.ok(new BookListResponse("ok", books));
    }

    /**
     * POST /api/books - добавление новой книги.
     */
    @PostMapping("/books")
    public ResponseEntity<AddBookResponse> addBook(@RequestBody Book bookRequest) {
        try {
            Book savedBook = bookService.addBook(
                    bookRequest.getTitle(),
                    bookRequest.getAuthor(),
                    bookRequest.getYear(),
                    bookRequest.getIsbn()
            );
            System.out.println("📥 POST /api/books: added book \"" + savedBook.getTitle() + "\" by " + savedBook.getAuthor());
            return ResponseEntity.ok(AddBookResponse.success(savedBook));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(AddBookResponse.failure("IllegalArgumentException", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(AddBookResponse.failure("Exception", e.getMessage()));
        }
    }
}