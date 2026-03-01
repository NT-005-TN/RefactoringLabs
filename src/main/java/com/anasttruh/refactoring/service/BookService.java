package com.anasttruh.refactoring.service;

import com.anasttruh.refactoring.Book;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Сервис для управления книгами: хранение, фильтрация, добавление.
 */
@Service
public class BookService {

    private final List<Book> storage = new CopyOnWriteArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @PostConstruct
    public void init() {
        storage.add(new Book(idCounter.getAndIncrement(), "1984", "George Orwell", 1949, "978-0451524935"));
        storage.add(new Book(idCounter.getAndIncrement(), "Clean Code", "Robert C. Martin", 2008, "978-0132350884"));
        System.out.println("🗄️ DB INITED WITH HARDCODED BOOKS");
    }

    /**
     * Получение списка книг с опциональной фильтрацией.
     * @param author фильтр по автору (частичное совпадение, без учёта регистра)
     * @param title фильтр по названию (частичное совпадение, без учёта регистра)
     * @return отфильтрованный список книг
     */
    public List<Book> findAll(String author, String title) {
        // Имитация задержки как в исходном коде
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }

        return storage.stream()
                .filter(book -> {
                    boolean match = true;
                    if (author != null && !author.isBlank()
                            && !book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                        match = false;
                    }
                    if (title != null && !title.isBlank()
                            && !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                        match = false;
                    }
                    return match;
                })
                .collect(Collectors.toList());
    }

    /**
     * Добавление новой книги.
     * @param title название книги (обязательно)
     * @param author автор книги (обязательно)
     * @param year год издания (опционально)
     * @param isbn ISBN (опционально)
     * @return сохранённая книга с присвоенным ID
     * @throws IllegalArgumentException если title или author пустые
     */
    public Book addBook(String title, String author, Integer year, String isbn) {
        if (title == null || title.isBlank() || author == null || author.isBlank()) {
            throw new IllegalArgumentException("Title and author are required");
        }
        Book newBook = new Book(idCounter.getAndIncrement(), title, author, year, isbn);
        storage.add(newBook);
        return newBook;
    }
}