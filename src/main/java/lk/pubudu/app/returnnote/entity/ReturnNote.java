package lk.pubudu.app.returnnote.entity;

import jakarta.persistence.*;
import lk.pubudu.app.issuenote.entity.IssueItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "return-note")
@IdClass(ReturnNotePK.class)
public class ReturnNote implements Serializable {
    @Serial
    private static final long serialVersionUID = -1825809066197938424L;
    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "issue_id", referencedColumnName = "issue_id"),
            @JoinColumn(name = "isbn", referencedColumnName = "isbn")
    })
    private IssueItem issueItem;
    @Column(nullable = false)
    private Date date;
}
