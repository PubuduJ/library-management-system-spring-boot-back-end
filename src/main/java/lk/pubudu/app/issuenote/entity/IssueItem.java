package lk.pubudu.app.issuenote.entity;

import jakarta.persistence.*;
import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.returnnote.entity.ReturnNote;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue-item")
@ToString(exclude = "returnItem")
@EqualsAndHashCode(exclude = "returnItem")
@IdClass(IssueItemPK.class)
public class IssueItem implements Serializable {
    @Serial
    private static final long serialVersionUID = -2771788874263935209L;
    @Id
    @ManyToOne
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    private IssueNote issueNote;
    @Id
    @ManyToOne
    @JoinColumn(name = "isbn", referencedColumnName = "isbn")
    private Book book;
    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "issueItem")
    private ReturnNote returnItem;

    public IssueItem(IssueNote issueNote, Book book) {
        this.issueNote = issueNote;
        this.book = book;
    }
}
