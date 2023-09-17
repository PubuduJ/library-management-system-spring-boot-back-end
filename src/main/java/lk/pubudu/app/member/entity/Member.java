package lk.pubudu.app.member.entity;

import jakarta.persistence.*;
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
@Table(name = "member")
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
    @Column(nullable = false, columnDefinition = "VARCHAR(11)")
    private String contact;
}
