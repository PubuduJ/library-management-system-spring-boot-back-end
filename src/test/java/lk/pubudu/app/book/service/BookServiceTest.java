package lk.pubudu.app.book.service;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.BookDTO;
import lk.pubudu.app.exception.NotFoundException;
import lk.pubudu.app.util.Transformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private Transformer transformer;
    @InjectMocks
    private BookService bookService;

    @Test
    void createBook() {
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        when(bookRepository.findById(bookDTO.getIsbn())).thenReturn(Optional.empty());
        when(transformer.toBookEntity(bookDTO)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(transformer.toBookDTO(book)).thenReturn(bookDTO);

        assertEquals(bookDTO.toString(), bookService.createBook(bookDTO).toString());
    }

    @Test
    void loadAllBooks() {
        ArrayList<Book> allBooks = new ArrayList<>();
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        allBooks.add(book);

        when(bookRepository.findAll()).thenReturn(allBooks);
        when(transformer.toBookDTO(book)).thenReturn(bookDTO);

        assertEquals(1, bookService.loadAllBooks().size());
    }

    @Test
    void searchBooks() {
        String query = "Martin Fowler";
        ArrayList<Book> books = new ArrayList<>();
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        books.add(book);

        when(bookRepository.findBooksByQuery("%"+query+"%")).thenReturn(books);
        when(transformer.toBookDTO(book)).thenReturn(bookDTO);

        assertEquals(1, bookService.searchBooks(query).size());
    }

    @Test
    void getBookDetails() {
        String id = "978-3-16-148410-0";
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(transformer.toBookDTO(book)).thenReturn(bookDTO);

        assertEquals(bookDTO.toString(), bookService.getBookDetails(id).toString());
    }

    @Test
    void loadBooksByPage() {
        int size = 5;
        int page = 0;
        int offset = (page - 1) * size;
        ArrayList<Book> books = new ArrayList<>();
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        books.add(book);

        when(bookRepository.findBooksByPage(size, offset)).thenReturn(books);
        when(transformer.toBookDTO(book)).thenReturn(bookDTO);

        assertEquals(1, bookService.loadBooksByPage(size, page).size());
    }

    @Test
    void searchBooksByPage() {
        String query = "Martin Fowler";
        int size = 5;
        int page = 0;
        int offset = (page - 1) * size;
        ArrayList<Book> books = new ArrayList<>();
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        books.add(book);

        when(bookRepository.searchBooksByPage("%"+query+"%", size, offset)).thenReturn(books);
        when(transformer.toBookDTO(book)).thenReturn(bookDTO);

        assertEquals(1, bookService.searchBooksByPage(query, size, page).size());
    }

    @Test
    void updateBook() {
        String id = "978-3-16-148410-0";
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
           bookService.updateBook(id, bookDTO);
        });

    }
}