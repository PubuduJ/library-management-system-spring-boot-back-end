package lk.pubudu.app.issuenote.entity;

import jakarta.persistence.*;
import lk.pubudu.app.member.entity.Member;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue-note")
@ToString(exclude = "issueItemSet")
@EqualsAndHashCode(exclude = "issueItemSet")
public class IssueNote implements Serializable {
    @Serial
    private static final long serialVersionUID = 6696261572257759301L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private Member member;
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "issueNote")
    private Set<IssueItem> issueItemSet = new HashSet<>();

    public IssueNote(Integer id, Date date, Member member) {
        this.id = id;
        this.date = date;
        this.member = member;
    }
}
