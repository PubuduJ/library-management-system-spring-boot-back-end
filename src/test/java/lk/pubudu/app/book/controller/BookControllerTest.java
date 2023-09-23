package lk.pubudu.app.book.controller;

import lk.pubudu.app.book.service.BookService;
import lk.pubudu.app.dto.BookDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;
    @InjectMocks
    private BookController bookController;

    @Test
    void createBook() {
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        when(bookService.createBook(bookDTO)).thenReturn(bookDTO);

        assertEquals(bookDTO.toString(), bookController.createBook(bookDTO).getBody().toString());
    }

    @Test
    void loadAllBooks() {
        ArrayList<BookDTO> bookList = new ArrayList<>();
        bookList.add(new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5));

        when(bookService.loadAllBooks()).thenReturn(bookList);

        assertEquals(1, bookController.loadAllBooks().getBody().size());
    }

    @Test
    void searchBooks() {
        String query = "Patterns";
        ArrayList<BookDTO> bookList = new ArrayList<>();
        bookList.add(new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5));

        when(bookService.searchBooks(query)).thenReturn(bookList);

        assertEquals(1, bookController.searchBooks(query).getBody().size());
    }

    @Test
    void getBookDetails() {
        String id = "978-3-16-148410-0";
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        when(bookService.getBookDetails(id)).thenReturn(bookDTO);

        assertEquals(bookDTO.toString(), bookController.getBookDetails(id).getBody().toString());
    }

    @Test
    void loadBooksByPage() {
        int size = 5;
        int page = 0;
        ArrayList<BookDTO> bookList = new ArrayList<>();
        bookList.add(new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5));

        when(bookService.loadBooksByPage(size, page)).thenReturn(bookList);

        assertEquals(1, bookController.loadBooksByPage(size, page).getBody().size());
    }

    @Test
    void searchBooksByPage() {
        String query = "Patterns";
        int size = 5;
        int page = 0;
        ArrayList<BookDTO> bookList = new ArrayList<>();
        bookList.add(new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5));

        when(bookService.searchBooksByPage(query, size, page)).thenReturn(bookList);

        assertEquals(1, bookController.searchBooksByPage(query, size, page).getBody().size());
    }

    @Test
    void updateBook() {
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        when(bookService.updateBook(bookDTO.getIsbn(), bookDTO)).thenReturn(bookDTO);

        assertEquals(bookDTO.toString(), bookController.updateBook(bookDTO.getIsbn(), bookDTO).getBody().toString());
    }
}