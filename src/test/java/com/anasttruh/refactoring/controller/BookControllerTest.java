package com.anasttruh.refactoring.controller;

import com.anasttruh.refactoring.Book;
import com.anasttruh.refactoring.dto.BookListResponse;
import com.anasttruh.refactoring.dto.AddBookResponse;
import com.anasttruh.refactoring.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.anasttruh.refactoring.BookTestData.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/books — возврат списка книг")
    void getAllBooks_success() throws Exception {
        // Arrange
        given(bookService.findAll(null, null)).willReturn(BOOKS_LIST);

        // Act & Assert
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ok"))
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.books[0].title").value("1984"))
                .andExpect(jsonPath("$.books[1].title").value("Clean Code"));
    }

    @Test
    @DisplayName("GET /api/books?author=orwell — фильтрация по автору")
    void getAllBooks_withAuthorFilter() throws Exception {
        given(bookService.findAll(eq("orwell"), isNull())).willReturn(List.of(BOOK_1984));

        mockMvc.perform(get("/api/books").param("author", "orwell"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.books[0].id").value(1));
    }

    @Test
    @DisplayName("POST /api/books — успешное добавление книги")
    void addBook_success() throws Exception {
        // Arrange: используем Book напрямую, как принимает контроллер
        Book request = createBookRequest("Test Book", "Test Author", 2024, "111-222");

        given(bookService.addBook("Test Book", "Test Author", 2024, "111-222"))
                .willReturn(BOOK_NEW);

        // Act & Assert
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.book.title").value("Test Book"))
                .andExpect(jsonPath("$.book.author").value("Test Author"));
    }

    @Test
    @DisplayName("POST /api/books с пустым title — 400 Bad Request")
    void addBook_emptyTitle_badRequest() throws Exception {
        Book request = createBookRequest("   ", "Author", null, null);

        // Мокаем выбрасывание исключения сервисом
        given(bookService.addBook("   ", "Author", null, null))
                .willThrow(new IllegalArgumentException("Title and author are required"));

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error").value("IllegalArgumentException"));
    }
}