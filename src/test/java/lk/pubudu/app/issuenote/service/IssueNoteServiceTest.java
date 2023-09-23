package lk.pubudu.app.issuenote.service;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.IssueNoteDTO;
import lk.pubudu.app.issuenote.entity.IssueItem;
import lk.pubudu.app.issuenote.entity.IssueNote;
import lk.pubudu.app.issuenote.repository.IssueItemRepository;
import lk.pubudu.app.issuenote.repository.IssueNoteRepository;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import lk.pubudu.app.util.Transformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IssueNoteServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private IssueNoteRepository issueNoteRepository;
    @Mock
    private IssueItemRepository issueItemRepository;
    @Mock
    private Transformer transformer;
    @InjectMocks
    private IssueNoteService issueNoteService;

    @Test
    void createNewIssueNote() {
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        ArrayList<String> books = new ArrayList<>();
        ArrayList<Integer> availableLimit = new ArrayList<>();
        availableLimit.add(5);
        IssueNoteDTO issueNoteDTO = new IssueNoteDTO(1, LocalDate.of(2022, 1, 1), "104ccff3-c584-4782-a582-8a06479b4600", books);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2021);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();

        IssueNote issueNote = new IssueNote(1, date, member);
        ArrayList<IssueItem> issueItemList = new ArrayList<>();
        issueItemList.add(new IssueItem(issueNote, book));

        when(memberRepository.findById(issueNoteDTO.getMemberId())).thenReturn(Optional.of(member));
        for (String isbn : issueNoteDTO.getBooks()) {
            when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
            when(bookRepository.getAvailableBookCopies(isbn)).thenReturn(Optional.of(5));
            when(bookRepository.isAlreadyIssued(isbn, issueNoteDTO.getMemberId())).thenReturn(books);
        }
        when(bookRepository.availableBookLimit(issueNoteDTO.getMemberId())).thenReturn(availableLimit);
        when(transformer.toIssueNoteEntity(issueNoteDTO)).thenReturn(issueNote);
        when(issueNoteRepository.save(issueNote)).thenReturn(issueNote);
        when(transformer.toIssueItemEntityList(issueNoteDTO)).thenReturn(issueItemList);
        issueItemRepository.saveAll(issueItemList);

        assertDoesNotThrow(() -> {
            issueNoteService.createNewIssueNote(issueNoteDTO);
        });
    }
}