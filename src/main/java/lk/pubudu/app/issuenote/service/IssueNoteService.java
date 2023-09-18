package lk.pubudu.app.issuenote.service;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.IssueNoteDTO;
import lk.pubudu.app.exception.NotFoundException;
import lk.pubudu.app.issuenote.repository.IssueItemRepository;
import lk.pubudu.app.issuenote.repository.IssueNoteRepository;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueNoteService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final IssueNoteRepository issueNoteRepository;
    private final IssueItemRepository issueItemRepository;

    public IssueNoteDTO createNewIssueNote(IssueNoteDTO issueNoteDTO) {
        Optional<Member> availability = memberRepository.findById(issueNoteDTO.getMemberId());
        if (availability.isEmpty()) {
            throw new NotFoundException("Member doesn't exist");
        }
        for (String isbn : issueNoteDTO.getBooks()) {
            Optional<Book> isExist = bookRepository.findById(isbn);
            if (isExist.isEmpty()) {
                throw new NotFoundException("Book: " + isbn + " doesn't exist");
            }
            Integer availableCopies = bookRepository.getAvailableBookCopies(isbn).get();
            if (availableCopies == 0) {

            }
        }
        return null;
    }

    public void test() {
        Optional<Integer> availableBookCopies = bookRepository.getAvailableBookCopies("978-3-16-148410-1");
        System.out.println("Hello" + availableBookCopies.get());
    }
}
