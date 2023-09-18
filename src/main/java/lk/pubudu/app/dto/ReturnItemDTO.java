package lk.pubudu.app.dto;

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
    private Integer issueNoteId;
    private String isbn;
}
