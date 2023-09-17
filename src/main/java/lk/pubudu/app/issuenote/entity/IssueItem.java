package lk.pubudu.app.issuenote.entity;

import jakarta.persistence.*;
import lk.pubudu.app.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue-item")
public class IssueItem implements Serializable {
    @Serial
    private static final long serialVersionUID = -2771788874263935209L;
    @Id
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private IssueNote issueNote;
    @Id
    @ManyToOne
    @JoinColumn(name = "isbn")
    private Book book;
}
