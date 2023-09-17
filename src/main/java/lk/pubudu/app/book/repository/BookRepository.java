package lk.pubudu.app.book.repository;

import lk.pubudu.app.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT B FROM Book B WHERE B.isbn LIKE ?1 OR B.title LIKE ?1 OR B.author LIKE ?1")
    List<Book> findBooksByQuery(String query);

    @Query(value = "SELECT * FROM book LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Book> findBooksByPage(int limit, int offset);

    @Query(value = "SELECT * FROM book WHERE book.isbn LIKE ?1 OR book.title LIKE ?1 OR book.author LIKE ?1 LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Book> searchBooksByPage(String query, int limit, int offset);

}
