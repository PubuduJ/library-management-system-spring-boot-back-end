package lk.pubudu.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lk.pubudu.app.util.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4396532185808315498L;
    @Null(groups = ValidationGroups.Create.class, message = "Member UUID should be null")
    @NotBlank(groups = ValidationGroups.Update.class, message = "Member UUID number cannot be empty or null")
    @Pattern(regexp = "^[A-Fa-f\\d]{8}(-[A-Fa-f\\d]{4}){3}-[A-Fa-f\\d]{12}$", message = "Invalid member uuid")
    private String id;
    @NotBlank(message = "Member name cannot be empty or null")
    @Pattern(regexp = "^[A-Za-z][A-Za-z. ]+$", message = "Invalid member name")
    private String name;
    @NotBlank(message = "Member address cannot be empty or null")
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-|/# ,.:;\\\\]+$", message = "Invalid member address")
    private String address;
    @NotBlank(message = "Member contact cannot be empty or null")
    @Pattern(regexp = "^\\d{3}-\\d{7}$", message = "Invalid member contact number")
    private String contact;
}
