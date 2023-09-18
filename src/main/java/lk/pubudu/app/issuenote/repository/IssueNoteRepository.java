package lk.pubudu.app.issuenote.repository;

import lk.pubudu.app.issuenote.entity.IssueNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueNoteRepository extends JpaRepository<IssueNote, Integer> {

}
