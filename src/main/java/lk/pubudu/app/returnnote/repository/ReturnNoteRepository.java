package lk.pubudu.app.returnnote.repository;

import lk.pubudu.app.returnnote.entity.ReturnNote;
import lk.pubudu.app.returnnote.entity.ReturnNotePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface ReturnNoteRepository extends JpaRepository<ReturnNote, ReturnNotePK> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `return-note` (date, issue_id, isbn) VALUES (?1,?2,?3)", nativeQuery = true)
    void insertReturnNote(Date date, Integer issueId, String isbn);

}
