package lk.pubudu.app.issuenote.repository;

import lk.pubudu.app.issuenote.entity.IssueItem;
import lk.pubudu.app.issuenote.entity.IssueItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueItemRepository extends JpaRepository<IssueItem, IssueItemPK> {

}
