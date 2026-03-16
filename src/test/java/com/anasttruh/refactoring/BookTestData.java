package com.anasttruh.refactoring;

import java.util.List;
import com.anasttruh.refactoring.Book;

public class BookTestData {
    public static final Book BOOK_1984 = new Book(1L, "1984", "George Orwell", 1949, "978-0451524935");
    public static final Book BOOK_CLEAN_CODE = new Book(2L, "Clean Code", "Robert C. Martin", 2008, "978-0132350884");
    public static final Book BOOK_NEW = new Book(3L, "Test Book", "Test Author", 2024, "111-222");

    public static final List<Book> BOOKS_LIST = List.of(BOOK_1984, BOOK_CLEAN_CODE);

    // Вспомогательный метод для создания запроса на добавление книги
    public static Book createBookRequest(String title, String author, Integer year, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);
        book.setIsbn(isbn);
        return book;
    }
}

// file:///C:/Users/trukh/CoffeeCupWalletProject/RefactoringLabs/build/reports/tests/test/index.html
// Результаты