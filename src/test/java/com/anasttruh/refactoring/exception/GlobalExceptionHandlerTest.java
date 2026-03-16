package com.anasttruh.refactoring.exception;

import com.anasttruh.refactoring.dto.AddBookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Обработка общего исключения возвращает 500 с сообщением")
    void handleGeneralException_returnsErrorResponse() {
        Exception ex = new RuntimeException("Test error");

        ResponseEntity<AddBookResponse> response = handler.handleGeneralException(ex);

        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getError()).isEqualTo("RuntimeException");
        assertThat(response.getBody().getMessage()).isEqualTo("Test error");
    }
}