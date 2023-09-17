package lk.pubudu.app.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "book")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = -8718657608094911954L;
    @Id
    @Column(columnDefinition = "VARCHAR(25)")
    private String isbn;
    @Column(nullable = false, columnDefinition = "VARCHAR(250)")
    private String title;
    @Column(nullable = false, columnDefinition = "VARCHAR(250)")
    private String author;
    @Column(nullable = false)
    private Integer copies;
}
