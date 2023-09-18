package lk.pubudu.app.returnnote.service;

import lk.pubudu.app.book.repository.BookRepository;
import lk.pubudu.app.dto.ReturnItemDTO;
import lk.pubudu.app.dto.ReturnNoteDTO;
import lk.pubudu.app.exception.AlreadyReturnedException;
import lk.pubudu.app.exception.NotFoundException;
import lk.pubudu.app.issuenote.repository.IssueItemRepository;
import lk.pubudu.app.issuenote.repository.IssueNoteRepository;
import lk.pubudu.app.returnnote.entity.ReturnNotePK;
import lk.pubudu.app.returnnote.repository.ReturnNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnNoteService {

    private final ReturnNoteRepository returnNoteRepository;
    private final IssueItemRepository issueItemRepository;
    private final IssueNoteRepository issueNoteRepository;
    private final BookRepository bookRepository;

    @Transactional
    public ReturnNoteDTO createNewReturnNote(ReturnNoteDTO returnNoteDTO) {
        for (ReturnItemDTO returnItemDTO : returnNoteDTO.getReturnItems()) {
            List<Integer> validIssueItem = issueItemRepository.isValidIssueItem(returnNoteDTO.getMemberId(),
                    returnItemDTO.getIssueNoteId(), returnItemDTO.getIsbn());
            if (validIssueItem.isEmpty()) {
                throw new NotFoundException(String.format("Either member: %s, issue note id: %s, isbn: %s don't exist or this return item is not belong to this member",
                        returnNoteDTO.getMemberId(),
                        returnItemDTO.getIssueNoteId(),
                        returnItemDTO.getIsbn()));
            }
            boolean isExist = returnNoteRepository.existsById(new ReturnNotePK(
                    issueNoteRepository.findById(returnItemDTO.getIssueNoteId()).get(),
                    bookRepository.findById(returnItemDTO.getIsbn()).get())
            );
            if (isExist) {
                throw new AlreadyReturnedException("This isbn " + returnItemDTO.getIsbn() +  " book have been already returned");
            }

            returnNoteRepository.insertReturnNote(new Date(), returnItemDTO.getIssueNoteId(), returnItemDTO.getIsbn());

        }
        return returnNoteDTO;
    }
}
