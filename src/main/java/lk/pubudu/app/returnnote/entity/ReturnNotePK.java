package lk.pubudu.app.returnnote.entity;

import lk.pubudu.app.issuenote.entity.IssueItem;
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
}
