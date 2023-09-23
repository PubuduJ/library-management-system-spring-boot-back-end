package lk.pubudu.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.io.Serial;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -884619388462360509L;
    @NotBlank(message = "Book ISBN cannot be empty or null")
    @Pattern(regexp = "^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$", message = "Invalid book isbn")
    private String isbn;
    @NotBlank(message = "Book title cannot be empty or null")
    @Pattern(regexp = "^[A-Za-z][A-Za-z. ]+$", message = "Invalid book title")
    private String title;
    @NotBlank(message = "Book author cannot be empty or null")
    @Pattern(regexp = "^[A-Za-z][A-Za-z. ]+$", message = "Invalid book author name")
    private String author;
    @NotNull(message = "Book copies cannot be null")
    private Integer copies;
}
