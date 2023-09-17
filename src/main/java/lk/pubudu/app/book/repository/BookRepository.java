package lk.pubudu.app.book.repository;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT B FROM Book B WHERE B.isbn LIKE ?1 OR B.title LIKE ?1 OR B.author LIKE ?1")
    List<Book> findBooksByQuery(String query);

}
