package kr.co.app.mapper;

import kr.co.app.model.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    public List<Book> allBooks();

    public Book getBook(String id);

    public int addBook(Book book);
}
