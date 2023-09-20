package lk.pubudu.app.returnnote.controller;

import jakarta.validation.Valid;
import lk.pubudu.app.dto.ReturnNoteDTO;
import lk.pubudu.app.returnnote.service.ReturnNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/returns")
@RequiredArgsConstructor
@CrossOrigin
public class ReturnNoteController {

    private final ReturnNoteService returnNoteService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ReturnNoteDTO> createNewReturnNote(@Valid @RequestBody ReturnNoteDTO returnNoteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(returnNoteService.createNewReturnNote(returnNoteDTO));
    }

}
