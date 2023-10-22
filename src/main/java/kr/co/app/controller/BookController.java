package kr.co.app.controller;

import kr.co.app.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8081/api/books";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/listBook")
    public String viewBookList(Model model) {
        ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {}
        );
        List<Book> books = responseEntity.getBody();
        model.addAttribute("allBooks", books);

        return "listBook";
    }

    @GetMapping("/viewBook/{id}")
    public String viewBook(@PathVariable("id") String id, Model model) {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(
                BASE_URL + "/" + id,
                Book.class
        );
        Book book = responseEntity.getBody();
        model.addAttribute("book", book);

        return "viewBook";
    }

    @GetMapping("/addViewBook")
    public String addViewBook() {
        return "addViewBook";
    }

    @PostMapping("/addBook")
    @ResponseBody
    public String addBook(@ModelAttribute Book book, Model model) { // form.serialize() => Book (dto)
                                                                            // input tag의 값이 다들어감 (키, 밸류로) ex;) let obj = {"bookId", "5", ... "isbn": "5"}
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity(
                BASE_URL,
                book,
                Book.class
        );
        HttpStatus statusCode = responseEntity.getStatusCode();
        //String body = statusCode == HttpStatus.OK ? "success" : "fail";
        String body = "";
        if(statusCode == HttpStatus.OK) {
            body = "success";
        } else {
            body = "fail";
        }

        return body;
    }

    @GetMapping("/updateViewBook/{id}")
    public String updateViewBook(@PathVariable("id") String id, Model model) {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(
                BASE_URL + "/" + id,
                Book.class
        );
        Book book = responseEntity.getBody();
        model.addAttribute("book", book);

        return "updateViewBook";
    }

    @PostMapping("/updateBook/{id}")
    public String updateBook(@PathVariable("id") String id, @ModelAttribute Book book) {
        restTemplate.put(
                BASE_URL + "/" + id,
                book
        );

        return "redirect:/listBook";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") String id) {
        restTemplate.delete(
                BASE_URL + "/" + id
        );

        return "redirect:/listBook";
    }
}
