package lk.pubudu.app.book.service;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.BookDTO;
import lk.pubudu.app.util.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final Transformer transformer;

    @Transactional
    public BookDTO createBook(BookDTO bookDTO) {
        Optional<Book> availability = bookRepository.findById(bookDTO.getIsbn());
        if (availability.isPresent()) {
            throw new DuplicateKeyException("Book is already exist in the system");
        }
        Book book = bookRepository.save(transformer.toBookEntity(bookDTO));
        return transformer.toBookDTO(book);
    }
}
