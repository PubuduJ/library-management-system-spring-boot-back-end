package lk.pubudu.app.dto;

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
    private String memberId;
    private List<ReturnItemDTO> returnItems = new ArrayList<>();
}
