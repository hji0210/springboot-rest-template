package kr.co.app.controller;

import kr.co.app.model.Book;
import kr.co.app.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class APIController {

    @Autowired
    private APIService apiService;
    @GetMapping("/api/books")
    public ResponseEntity allBooks(HttpServletRequest request, HttpServletResponse response) {
        List<Book> list = apiService.allBooks();
        // ObjectUtils.isEmpty(@Nullable Object obj)은 객체의 값이 비어있거나 null인 것을 체크하는 자바에서 제공해주는 함수
        if(ObjectUtils.isEmpty(list)) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 실패
        else return ResponseEntity.status(HttpStatus.OK).body(list); // 성공
    }
    
    @PostMapping("/api/books")
    public ResponseEntity addBook(@RequestBody Book book, HttpServletRequest request, HttpServletResponse response) {
        int cnt = apiService.addBook(book);
        if(cnt == 1) { // 글쓰기 성공
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }else { // 글쓰기 실패
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/api/books/{id}")
    public ResponseEntity viewBook(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        Book book = apiService.getBook(id);
        if(ObjectUtils.isEmpty(book)) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 실패
        else return ResponseEntity.status(HttpStatus.OK).body(book); // 성공
    }
}