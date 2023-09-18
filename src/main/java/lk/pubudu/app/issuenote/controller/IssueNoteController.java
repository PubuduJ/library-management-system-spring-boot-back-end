package lk.pubudu.app.issuenote.controller;

import lk.pubudu.app.dto.BookDTO;
import lk.pubudu.app.dto.IssueNoteDTO;
import lk.pubudu.app.issuenote.service.IssueNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/issue-notes")
@RequiredArgsConstructor
@CrossOrigin
public class IssueNoteController {

    private final IssueNoteService issueNoteService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<IssueNoteDTO> createNewIssueNote(@RequestBody IssueNoteDTO issueNoteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(issueNoteService.createNewIssueNote(issueNoteDTO));
    }

}
