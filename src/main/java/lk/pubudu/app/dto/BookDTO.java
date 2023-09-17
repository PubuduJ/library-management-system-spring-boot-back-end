package lk.pubudu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -884619388462360509L;
    private String isbn;
    private String title;
    private String author;
    private Integer copies;
}
