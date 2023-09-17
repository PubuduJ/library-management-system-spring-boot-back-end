package lk.pubudu.app.member.entity;

import jakarta.persistence.*;
import lk.pubudu.app.issuenote.entity.IssueNote;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
@ToString(exclude = "issueNoteSet")
@EqualsAndHashCode(exclude = "issueNoteSet")
public class Member implements Serializable {
    @Serial
    private static final long serialVersionUID = 2257371345449457587L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    private String name;
    @Column(nullable = false, columnDefinition = "VARCHAR(250)")
    private String address;
    @Column(nullable = false, columnDefinition = "VARCHAR(11)", unique = true)
    private String contact;
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "member")
    private Set<IssueNote> issueNoteSet = new HashSet<>();

    public Member(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
}
