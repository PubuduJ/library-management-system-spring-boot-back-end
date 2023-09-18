package lk.pubudu.app.dto;

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
    private String memberId;
    private ArrayList<String> books = new ArrayList<>();
}
