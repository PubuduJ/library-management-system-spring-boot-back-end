package lk.pubudu.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnItemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5021523427392452328L;
    @NotNull(message = "Issue note ID cannot be null")
    private Integer issueNoteId;
    @NotBlank(message = "Book ISBN cannot be empty or null")
    @Pattern(regexp = "^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$", message = "Invalid book isbn")
    private String isbn;
}
