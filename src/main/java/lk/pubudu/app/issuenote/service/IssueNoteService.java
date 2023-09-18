package lk.pubudu.app.issuenote.service;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.IssueNoteDTO;
import lk.pubudu.app.exception.NotAvailableException;
import lk.pubudu.app.exception.NotFoundException;
import lk.pubudu.app.issuenote.repository.IssueItemRepository;
import lk.pubudu.app.issuenote.repository.IssueNoteRepository;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        /* Check the member existence */
        if (availability.isEmpty()) {
            throw new NotFoundException("Member doesn't exist");
        }
        for (String isbn : issueNoteDTO.getBooks()) {
            Optional<Book> isExist = bookRepository.findById(isbn);
            /* Check the book existence */
            if (isExist.isEmpty()) {
                throw new NotFoundException("Book: " + isbn + " doesn't exist");
            }
            /* Check the availability of the book */
            Integer availableCopies = bookRepository.getAvailableBookCopies(isbn).get();
            if (availableCopies == 0) {
                throw new NotAvailableException("Isbn no: " + isbn + " book is not available at the moment");
            }
            List<String> alreadyIssued = bookRepository.isAlreadyIssued(isbn, issueNoteDTO.getMemberId());
            if (!alreadyIssued.isEmpty()) {

            }
        }
        return null;
    }

    public void test() {
        List<String> alreadyIssued = bookRepository.isAlreadyIssued("2714641a-301e-43d5-9d31-ad916d075700", "978-3-16-148410-3");
        for (String isbn : alreadyIssued) {
            System.out.println(isbn);
        }
    }
}
