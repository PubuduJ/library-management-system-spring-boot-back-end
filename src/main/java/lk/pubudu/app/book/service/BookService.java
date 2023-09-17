package lk.pubudu.app.book.service;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.BookDTO;
import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.exception.NotFoundException;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.util.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    public List<BookDTO> loadAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        ArrayList<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : allBooks) {
            bookDTOList.add(transformer.toBookDTO(book));
        }
        return bookDTOList;
    }

    public List<BookDTO> searchBooks(String q) {
        String query = "%".concat(q).concat("%");
        List<Book> booksByQuery = bookRepository.findBooksByQuery(query);
        ArrayList<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : booksByQuery) {
            bookDTOList.add(transformer.toBookDTO(book));
        }
        return bookDTOList;
    }

    public BookDTO getBookDetails(String id) {
        Optional<Book> availability = bookRepository.findById(id);
        if (availability.isEmpty()) {
            throw new NotFoundException("Book doesn't exist in the system");
        }
        return transformer.toBookDTO(availability.get());
    }
}
