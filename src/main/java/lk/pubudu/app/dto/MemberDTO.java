package lk.pubudu.app.dto;

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
    private String id;
    private String name;
    private String address;
    private String contact;
}
