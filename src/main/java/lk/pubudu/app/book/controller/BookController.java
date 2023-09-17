package lk.pubudu.app.book.controller;

import lk.pubudu.app.book.service.BookService;
import lk.pubudu.app.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/books")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDTO));
    }

}
