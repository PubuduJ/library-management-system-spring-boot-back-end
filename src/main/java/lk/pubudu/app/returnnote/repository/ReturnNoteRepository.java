package lk.pubudu.app.returnnote.repository;

import lk.pubudu.app.returnnote.entity.ReturnNote;
import lk.pubudu.app.returnnote.entity.ReturnNotePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnNoteRepository extends JpaRepository<ReturnNote, ReturnNotePK> {

}
