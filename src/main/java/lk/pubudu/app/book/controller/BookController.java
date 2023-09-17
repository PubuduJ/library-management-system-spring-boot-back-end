package lk.pubudu.app.book.controller;

import lk.pubudu.app.book.service.BookService;
import lk.pubudu.app.dto.BookDTO;
import lk.pubudu.app.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDTO));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BookDTO>> loadAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.loadAllBooks());
    }

}
