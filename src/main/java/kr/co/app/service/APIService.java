package kr.co.app.service;

import kr.co.app.mapper.BookMapper;
import kr.co.app.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIService {

    @Autowired
    private BookMapper mapper;

    public List<Book> allBooks() {
        return mapper.allBooks();
    }

    public Book getBook(String id) {
        return mapper.getBook(id);
    }

    public int addBook(Book book) {
        return mapper.addBook(book);
    }
}
