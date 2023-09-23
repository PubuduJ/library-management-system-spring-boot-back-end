package lk.pubudu.app.util;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.BookDTO;
import lk.pubudu.app.dto.IssueNoteDTO;
import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.issuenote.entity.IssueNote;
import lk.pubudu.app.issuenote.repository.IssueNoteRepository;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransformerTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private IssueNoteRepository issueNoteRepository;
    @InjectMocks
    private Transformer transformer;

    @Test
    void toMemberEntity() {
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");

        when(modelMapper.map(memberDTO, Member.class)).thenReturn(member);

        assertEquals(member.toString(), transformer.toMemberEntity(memberDTO).toString());
    }

    @Test
    void toMemberDTO() {
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");

        when(modelMapper.map(member, MemberDTO.class)).thenReturn(memberDTO);

        assertEquals(memberDTO.toString(), transformer.toMemberDTO(member).toString());
    }

    @Test
    void toBookEntity() {
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        when(modelMapper.map(bookDTO, Book.class)).thenReturn(book);

        assertEquals(book.toString(), transformer.toBookEntity(bookDTO).toString());
    }

    @Test
    void toBookDTO() {
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        BookDTO bookDTO = new BookDTO("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);

        when(modelMapper.map(book, BookDTO.class)).thenReturn(bookDTO);

        assertEquals(bookDTO.toString(), transformer.toBookDTO(book).toString());
    }

    @Test
    void toIssueNoteEntity() {
        ArrayList<String> books = new ArrayList<>();
        books.add("978-3-16-148410-0");
        books.add("978-3-16-148410-1");
        books.add("978-3-16-148410-2");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        IssueNoteDTO issueNoteDTO = new IssueNoteDTO(1, LocalDate.of(2021, 1, 1), "104ccff3-c584-4782-a582-8a06479b4600", books);
        IssueNote issueNote = new IssueNote(0, Date.valueOf(LocalDate.of(2021,1,1)), member);

        when(memberRepository.findById(issueNoteDTO.getMemberId())).thenReturn(Optional.of(member));

        assertEquals(issueNote.getMember().getId(), transformer.toIssueNoteEntity(issueNoteDTO).getMember().getId());
    }

    @Test
    void toIssueItemEntityList() {
        ArrayList<String> books = new ArrayList<>();
        books.add("978-3-16-148410-0");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        Book book = new Book("978-3-16-148410-0", "Patterns of Enterprise Application Architecture", "Martin Fowler", 5);
        IssueNoteDTO issueNoteDTO = new IssueNoteDTO(1, LocalDate.of(2021, 1, 1), "104ccff3-c584-4782-a582-8a06479b4600", books);
        IssueNote issueNote = new IssueNote(0, Date.valueOf(LocalDate.of(2021,1,1)), member);

        when(issueNoteRepository.findById(issueNoteDTO.getId())).thenReturn(Optional.of(issueNote));
        for (String isbn : issueNoteDTO.getBooks()) {
            when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
        }

        assertEquals(1, transformer.toIssueItemEntityList(issueNoteDTO).size());
    }
}