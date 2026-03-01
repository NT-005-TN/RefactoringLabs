package com.anasttruh.refactoring.dto;

import com.anasttruh.refactoring.Book;

/**
 * DTO для ответа на запрос POST /api/books.
 * Формат совместим с исходной версией API.
 */
public class AddBookResponse {

    private boolean success;
    private Book book;
    private String error;
    private String message;

    public AddBookResponse() {
    }

    public static AddBookResponse success(Book book) {
        AddBookResponse response = new AddBookResponse();
        response.setSuccess(true);
        response.setBook(book);
        return response;
    }

    public static AddBookResponse failure(String errorType, String message) {
        AddBookResponse response = new AddBookResponse();
        response.setSuccess(false);
        response.setError(errorType);
        response.setMessage(message);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}