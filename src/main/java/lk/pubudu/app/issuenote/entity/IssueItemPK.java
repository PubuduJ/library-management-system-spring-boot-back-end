package lk.pubudu.app.issuenote.entity;

import lk.pubudu.app.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueItemPK implements Serializable {
    @Serial
    private static final long serialVersionUID = -1471090006948147779L;
    private IssueNote issueNote;
    private Book book;
}
