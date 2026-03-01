package com.anasttruh;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class Application {


    public static List<Book> globalBookStorage = new ArrayList<>();
    public static AtomicLong idCounter = new AtomicLong(1);

     
    public static class Book {
        public Long id;
        public String title;
        public String author;
        public Integer year;
        public String isbn;

        public Book(Long id, String title, String author, Integer year, String isbn) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.year = year;
            this.isbn = isbn;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("📚 BOOK APP STARTED!!!");
    }

    @PostConstruct
    public void init() {
         
        globalBookStorage.add(new Book(idCounter.getAndIncrement(), "1984", "George Orwell", 1949, "978-0451524935"));
        globalBookStorage.add(new Book(idCounter.getAndIncrement(), "Clean Code", "Robert C. Martin", 2008, "978-0132350884"));
        System.out.println("🗄️ DB INITED WITH HARDCODED BOOKS");
    }

     
    @GetMapping(value = "/api/books", produces = "application/json")
    public String getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title
    ) {
        String result = "";
        try {
            Thread.sleep(300);  

            List<Book> filtered = globalBookStorage;
            if (author != null || title != null) {
                filtered = new ArrayList<>();
                for (Book b : globalBookStorage) {
                    boolean match = true;
                    if (author != null && !b.author.toLowerCase().contains(author.toLowerCase())) match = false;
                    if (title != null && !b.title.toLowerCase().contains(title.toLowerCase())) match = false;
                    if (match) filtered.add(b);
                }
            }

             
            result = "{\"status\":\"ok\",\"count\":" + filtered.size() + ",\"books\":[";
            int i = 0;
            for (Book book : filtered) {
                if (i > 0) result += ",";
                result += "{";
                result += "\"id\":" + book.id + ",";
                result += "\"title\":\"" + escapeJson(book.title) + "\",";
                result += "\"author\":\"" + escapeJson(book.author) + "\",";
                result += "\"year\":" + book.year + ",";
                result += "\"isbn\":\"" + escapeJson(book.isbn) + "\"";
                result += "}";
                i++;
            }
            result += "]}";

            System.out.println("✅ GET /api/books processed, returned " + filtered.size() + " books");

        } catch (Exception e) {
            e.printStackTrace();
            result = "{\"status\":\"error\",\"message\":\"" + escapeJson(e.getMessage()) + "\"}";
        }
        return result;
    }

     
    @PostMapping(value = "/api/books", consumes = "application/json", produces = "application/json")
    public String addBook(@RequestBody String bodyRaw) {

        String response = "";
        try {
            if (bodyRaw == null || bodyRaw.trim().isEmpty()) {
                throw new IllegalArgumentException("Empty request body");
            }

             
            JsonObject json = JsonParser.parseString(bodyRaw).getAsJsonObject();

             
            String title = json.get("title").getAsString();
            String author = json.get("author").getAsString();
            Integer year = json.has("year") ? json.get("year").getAsInt() : null;
            String isbn = json.has("isbn") ? json.get("isbn").getAsString() : null;

             
            if (title == null || title.isBlank() || author == null || author.isBlank()) {
                throw new IllegalArgumentException("Title and author are required");
            }

             
            Book newBook = new Book(idCounter.getAndIncrement(), title, author, year, isbn);
            synchronized (globalBookStorage) {
                globalBookStorage.add(newBook);
            }

             
            response = "{\"success\":true,\"book\":{\"id\":" + newBook.id +
                    ",\"title\":\"" + escapeJson(newBook.title) + "\"" +
                    ",\"author\":\"" + escapeJson(newBook.author) + "\"" +
                    (newBook.year != null ? ",\"year\":" + newBook.year : "") +
                    (newBook.isbn != null ? ",\"isbn\":\"" + escapeJson(newBook.isbn) + "\"" : "") +
                    "}}";

            System.out.println("📥 POST /api/books: added book \"" + title + "\" by " + author);

        } catch (Throwable t) {
            t.printStackTrace();
            response = "{\"success\":false,\"error\":\"" + t.getClass().getSimpleName() +
                    "\",\"message\":\"" + escapeJson(t.getMessage()) + "\"}";
        }
        return response;
    }

     
    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}