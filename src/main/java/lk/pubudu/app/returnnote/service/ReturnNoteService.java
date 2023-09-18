package lk.pubudu.app.returnnote.service;

import lk.pubudu.app.dto.ReturnNoteDTO;
import lk.pubudu.app.returnnote.repository.ReturnNoteRepository;
import lk.pubudu.app.util.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReturnNoteService {

    private final ReturnNoteRepository returnNoteRepository;
    private final Transformer transformer;

    public ReturnNoteDTO createNewReturnNote(ReturnNoteDTO returnNoteDTO) {
        return null;
    }
}
