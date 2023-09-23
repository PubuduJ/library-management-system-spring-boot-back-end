package lk.pubudu.app.issuenote.controller;

import lk.pubudu.app.dto.IssueNoteDTO;
import lk.pubudu.app.issuenote.service.IssueNoteService;
import lk.pubudu.app.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IssueNoteControllerTest {

    @Mock
    private IssueNoteService issueNoteService;
    @InjectMocks
    private IssueNoteController issueNoteController;

    @Test
    void createNewIssueNote() {
        ArrayList<String> books = new ArrayList<>();
        books.add("978-3-16-148410-0");
        books.add("978-3-16-148410-1");
        books.add("978-3-16-148410-2");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        IssueNoteDTO issueNoteDTO = new IssueNoteDTO(1, LocalDate.of(2021, 1, 1), "104ccff3-c584-4782-a582-8a06479b4600", books);

        when(issueNoteService.createNewIssueNote(issueNoteDTO)).thenReturn(issueNoteDTO);

        assertEquals(issueNoteDTO.getMemberId(), issueNoteController.createNewIssueNote(issueNoteDTO).getBody().getMemberId());
    }
}