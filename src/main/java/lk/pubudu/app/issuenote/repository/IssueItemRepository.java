package lk.pubudu.app.issuenote.repository;

import lk.pubudu.app.issuenote.entity.IssueItem;
import lk.pubudu.app.issuenote.entity.IssueItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueItemRepository extends JpaRepository<IssueItem, IssueItemPK> {

    @Query(value = """
            SELECT issue_id
            FROM `issue-item` II
            INNER JOIN `issue-note` `IN` ON II.issue_id = `IN`.id
            WHERE `IN`.member_id = ?1 AND II.issue_id = ?2 AND II.isbn = ?3""", nativeQuery = true)
    List<Integer> isValidIssueItem(String memberId, Integer issueId, String isbn);

}
