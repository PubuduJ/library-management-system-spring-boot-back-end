package lk.pubudu.app.issuenote.repository;

import lk.pubudu.app.issuenote.entity.IssueItem;
import lk.pubudu.app.issuenote.entity.IssueItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueItemRepository extends JpaRepository<IssueItem, IssueItemPK> {

}
