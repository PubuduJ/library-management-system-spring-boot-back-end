package lk.pubudu.app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lk.pubudu.app.util.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnNoteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2065322407421709958L;
    @NotBlank(groups = ValidationGroups.Update.class, message = "Member UUID number cannot be empty or null")
    @Pattern(regexp = "^[A-Fa-f\\d]{8}(-[A-Fa-f\\d]{4}){3}-[A-Fa-f\\d]{12}$", message = "Invalid member uuid")
    private String memberId;
    @Valid
    private List<ReturnItemDTO> returnItems = new ArrayList<>();
}
