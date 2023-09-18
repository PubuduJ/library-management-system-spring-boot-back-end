package lk.pubudu.app.util;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.BookDTO;
import lk.pubudu.app.dto.IssueNoteDTO;
import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.issuenote.entity.IssueItem;
import lk.pubudu.app.issuenote.entity.IssueNote;
import lk.pubudu.app.issuenote.repository.IssueNoteRepository;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Transformer {
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final IssueNoteRepository issueNoteRepository;

    public Member toMemberEntity(MemberDTO memberDTO) {
        return modelMapper.map(memberDTO, Member.class);
    }

    public MemberDTO toMemberDTO(Member member) {
        return modelMapper.map(member, MemberDTO.class);
    }

    public Book toBookEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public BookDTO toBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public IssueNote toIssueNoteEntity(IssueNoteDTO issueNoteDTO) {
        Optional<Member> member = memberRepository.findById(issueNoteDTO.getMemberId());
        return new IssueNote(0, Date.valueOf(LocalDate.now()),member.get());
    }

    public List<IssueItem> toIssueItemEntityList(IssueNoteDTO issueNoteDTO) {
        ArrayList<String> books = issueNoteDTO.getBooks();
        ArrayList<IssueItem> issueItemList = new ArrayList<>();
        for (String isbn : books) {
            IssueItem issueItem = new IssueItem(issueNoteRepository.findById(issueNoteDTO.getId()).get(), bookRepository.findById(isbn).get());
            issueItemList.add(issueItem);
        }
        return issueItemList;
    }

}
