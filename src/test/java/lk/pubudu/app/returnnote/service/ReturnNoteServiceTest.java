package lk.pubudu.app.returnnote.service;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.ReturnItemDTO;
import lk.pubudu.app.dto.ReturnNoteDTO;
import lk.pubudu.app.issuenote.entity.IssueNote;
import lk.pubudu.app.issuenote.repository.IssueItemRepository;
import lk.pubudu.app.issuenote.repository.IssueNoteRepository;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.returnnote.entity.ReturnNotePK;
import lk.pubudu.app.returnnote.repository.ReturnNoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturnNoteServiceTest {

    @Mock
    private ReturnNoteRepository returnNoteRepository;
    @Mock
    private IssueItemRepository issueItemRepository;
    @Mock
    private IssueNoteRepository issueNoteRepository;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private ReturnNoteService returnNoteService;

    @Test
    void createNewReturnNote() {
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        ArrayList<ReturnItemDTO> returnItems = new ArrayList<>();
        ReturnItemDTO returnItemDTO = new ReturnItemDTO(1, "978-3-16-148410-0");
        returnItems.add(returnItemDTO);
        ReturnNoteDTO returnNoteDTO = new ReturnNoteDTO("104ccff3-c584-4782-a582-8a06479b4600", returnItems);

        ArrayList<Integer> validIssueItem = new ArrayList<>();
        validIssueItem.add(1);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2021);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();

        IssueNote issueNote = new IssueNote(1, date, member);

        when(issueItemRepository.isValidIssueItem(returnNoteDTO.getMemberId(), returnItemDTO.getIssueNoteId(), returnItemDTO.getIsbn())).thenReturn(validIssueItem);
        when(issueNoteRepository.findById(returnItemDTO.getIssueNoteId())).thenReturn(Optional.of(issueNote));
        when(bookRepository.findById(returnItemDTO.getIsbn())).thenReturn(Optional.of(book));
        when(returnNoteRepository.existsById(new ReturnNotePK(issueNote, book))).thenReturn(false);
        returnNoteRepository.insertReturnNote(new Date(), returnItemDTO.getIssueNoteId(), returnItemDTO.getIsbn());

        assertDoesNotThrow(() -> {
            returnNoteService.createNewReturnNote(returnNoteDTO);
        });
    }
}