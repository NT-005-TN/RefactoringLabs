package com.anasttruh.refactoring.dto;

import com.anasttruh.refactoring.Book;
import java.util.List;

/**
 * DTO для ответа на запрос GET /api/books.
 * Формат совместим с исходной версией API.
 */
public class BookListResponse {

    private String status;
    private int count;
    private List<Book> books;

    public BookListResponse() {
    }

    public BookListResponse(String status, List<Book> books) {
        this.status = status;
        this.count = books != null ? books.size() : 0;
        this.books = books;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}