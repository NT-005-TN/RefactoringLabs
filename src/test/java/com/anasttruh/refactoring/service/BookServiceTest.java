package com.anasttruh.refactoring.service;

import com.anasttruh.refactoring.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        // Создаём сервис и инициализируем данные вручную (без Spring контекста)
        bookService = new BookService();
        bookService.initTestData(); // Используем вынесенный метод вместо @PostConstruct
    }

    @Test
    @DisplayName("findAll без фильтров возвращает все книги")
    void findAll_noFilters_returnsAllBooks() {
        List<Book> result = bookService.findAll(null, null);

        assertThat(result).hasSize(2);
        assertThat(result)
                .extracting(Book::getTitle)
                .contains("1984", "Clean Code");
    }

    @Test
    @DisplayName("findAll по author (частичное совпадение, регистронезависимо)")
    void findAll_filterByAuthor_partialMatch_caseInsensitive() {
        List<Book> result = bookService.findAll("orwell", null);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("1984");
    }

    @Test
    @DisplayName("findAll по title (частичное совпадение)")
    void findAll_filterByTitle_partialMatch() {
        List<Book> result = bookService.findAll(null, "clean");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAuthor()).isEqualTo("Robert C. Martin");
    }

    @Test
    @DisplayName("findAll с двумя фильтрами — пересечение")
    void findAll_withBothFilters_intersection() {
        List<Book> result = bookService.findAll("George Orwell", "1984");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isNotNull();
    }

    @Test
    @DisplayName("findAll с несовпадающими фильтрами — пустой результат")
    void findAll_withNonMatchingFilters_returnsEmpty() {
        List<Book> result = bookService.findAll("Tolkien", "Harry Potter");

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("addBook с валидными данными создаёт книгу с ID")
    void addBook_validData_createsBookWithId() {
        Book result = bookService.addBook("New Book", "Author", 2024, "123-456");

        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo("New Book");
        assertThat(result.getAuthor()).isEqualTo("Author");
        assertThat(result.getYear()).isEqualTo(2024);
        assertThat(result.getIsbn()).isEqualTo("123-456");
    }

    @Test
    @DisplayName("addBook без title выбрасывает IllegalArgumentException")
    void addBook_emptyTitle_throwsException() {
        assertThatThrownBy(() -> bookService.addBook("", "Author", null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title and author are required");
    }

    @Test
    @DisplayName("addBook без author выбрасывает IllegalArgumentException")
    void addBook_emptyAuthor_throwsException() {
        assertThatThrownBy(() -> bookService.addBook("Title", "   ", null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title and author are required");
    }

    @Test
    @DisplayName("addBook с null year/isbn — книга создаётся с null полями")
    void addBook_optionalFieldsNull_success() {
        Book result = bookService.addBook("Minimal Book", "Minimal Author", null, null);

        assertThat(result.getYear()).isNull();
        assertThat(result.getIsbn()).isNull();
        assertThat(result.getId()).isNotNull();
    }
}