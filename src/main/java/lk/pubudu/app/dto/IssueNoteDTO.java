package lk.pubudu.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lk.pubudu.app.util.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueNoteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 9027429810218577665L;
    private Integer id;
    private LocalDate date;
    @NotBlank(groups = ValidationGroups.Update.class, message = "Member UUID number cannot be empty or null")
    @Pattern(regexp = "^[A-Fa-f\\d]{8}(-[A-Fa-f\\d]{4}){3}-[A-Fa-f\\d]{12}$", message = "Invalid member uuid")
    private String memberId;
    @NotNull(message = "Books ISBN array cannot be null")
    private ArrayList<String> books = new ArrayList<>();
}
