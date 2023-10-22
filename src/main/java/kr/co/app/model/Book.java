package kr.co.app.model;

import lombok.Data;

@Data
public class Book {
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private String releaseDate;
    private String isbn;
}
