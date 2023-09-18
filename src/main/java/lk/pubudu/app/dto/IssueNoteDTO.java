package lk.pubudu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueNoteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 9027429810218577665L;
    private Integer id;
    private Date date;
    private String memberId;
    private ArrayList<String> books = new ArrayList<>();
}
