package lk.pubudu.app.book.repository;

import lk.pubudu.app.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT B FROM Book B WHERE B.isbn LIKE ?1 OR B.title LIKE ?1 OR B.author LIKE ?1")
    List<Book> findBooksByQuery(String query);

    @Query(value = "SELECT * FROM book LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Book> findBooksByPage(int limit, int offset);

    @Query(value = "SELECT * FROM book WHERE book.isbn LIKE ?1 OR book.title LIKE ?1 OR book.author LIKE ?1 LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Book> searchBooksByPage(String query, int limit, int offset);

    @Query(value = """
            SELECT (B.copies - COUNT(II.isbn) + COUNT(R.isbn)) AS `available_copies`
            FROM `issue-item` II
            LEFT JOIN `return-note` R ON II.issue_id = R.issue_id AND II.isbn = R.isbn
            RIGHT JOIN book B ON II.isbn = B.isbn
            WHERE B.isbn = ?1
            GROUP BY B.isbn""", nativeQuery = true)
    Optional<Integer> getAvailableBookCopies(String isbn);

}
