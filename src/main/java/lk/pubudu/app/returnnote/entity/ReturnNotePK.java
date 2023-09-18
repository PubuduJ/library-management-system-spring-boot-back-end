package lk.pubudu.app.returnnote.entity;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.issuenote.entity.IssueItem;
import lk.pubudu.app.issuenote.entity.IssueNote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnNotePK implements Serializable {
    @Serial
    private static final long serialVersionUID = 7104970688144119002L;
    private IssueItem issueItem;

    public ReturnNotePK(IssueNote issueNote, Book book) {
        this.issueItem = new IssueItem(issueNote, book);
    }
}
