package lk.pubudu.app.issuenote.service;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.IssueNoteDTO;
import lk.pubudu.app.exception.AlreadyIssuedException;
import lk.pubudu.app.exception.LimitExceedException;
import lk.pubudu.app.exception.NotAvailableException;
import lk.pubudu.app.exception.NotFoundException;
import lk.pubudu.app.issuenote.entity.IssueItem;
import lk.pubudu.app.issuenote.entity.IssueNote;
import lk.pubudu.app.issuenote.repository.IssueItemRepository;
import lk.pubudu.app.issuenote.repository.IssueNoteRepository;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import lk.pubudu.app.util.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueNoteService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final IssueNoteRepository issueNoteRepository;
    private final IssueItemRepository issueItemRepository;
    private final Transformer transformer;

    @Transactional
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
                throw new AlreadyIssuedException("Book: " + isbn + " has been already issued to the same member");
            }
        }
        /* Check how many books can be issued for this member (maximum = 3) */
        List<Integer> availableLimit = bookRepository.availableBookLimit(issueNoteDTO.getMemberId());
        if (availableLimit.size() != 1) {
            throw new LimitExceedException("Member's book limit has been exceeded");
        }
        if (availableLimit.get(0) < issueNoteDTO.getBooks().size()) {
            throw new LimitExceedException("Member's book limit has been exceeded");
        }

        /* Create issue note entity from IssueNoteDTO */
        IssueNote issueNote = transformer.toIssueNoteEntity(issueNoteDTO);
        IssueNote savedIssueNote = issueNoteRepository.save(issueNote);

        /* Get saved issue note id (auto generated) and set it to the issue note dto */
        Integer issueNoteId = savedIssueNote.getId();
        issueNoteDTO.setId(issueNoteId);

        /* Create issue item entity list from issue note dto */
        List<IssueItem> issueItemList = transformer.toIssueItemEntityList(issueNoteDTO);
        issueItemRepository.saveAll(issueItemList);
        issueNoteDTO.setDate(LocalDate.now());
        return issueNoteDTO;
    }

}
