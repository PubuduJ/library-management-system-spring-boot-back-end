package lk.pubudu.app.book.controller;

import jakarta.validation.Valid;
import lk.pubudu.app.book.service.BookService;
import lk.pubudu.app.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/api/books")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDTO));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BookDTO>> loadAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.loadAllBooks());
    }

    @GetMapping(params = "q", produces = "application/json")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String q) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.searchBooks(q));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<BookDTO> getBookDetails(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookDetails(id));
    }

    @GetMapping(params = {"size", "page"}, produces = "application/json")
    public ResponseEntity<List<BookDTO>> loadBooksByPage(@RequestParam int size, @RequestParam int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(bookService.loadAllBooks().size()));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookService.loadBooksByPage(size, page));
    }

    @GetMapping(params = {"q", "size", "page"}, produces = "application/json")
    public ResponseEntity<List<BookDTO>> searchBooksByPage(@RequestParam String q, @RequestParam int size, @RequestParam int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(bookService.searchBooks(q).size()));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookService.searchBooksByPage(q, size, page));
    }

    @PatchMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String id,@Valid @RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBook(id, bookDTO));
    }

}
