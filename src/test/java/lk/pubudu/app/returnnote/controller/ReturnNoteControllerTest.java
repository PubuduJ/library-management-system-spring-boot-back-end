package lk.pubudu.app.returnnote.controller;

import lk.pubudu.app.dto.ReturnItemDTO;
import lk.pubudu.app.dto.ReturnNoteDTO;
import lk.pubudu.app.returnnote.entity.ReturnNote;
import lk.pubudu.app.returnnote.service.ReturnNoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturnNoteControllerTest {

    @Mock
    private ReturnNoteService returnNoteService;
    @InjectMocks
    private ReturnNoteController returnNoteController;

    @Test
    void createNewReturnNote() {
        ArrayList<ReturnItemDTO> returnItemList = new ArrayList<>();
        returnItemList.add(new ReturnItemDTO(1, "978-3-16-148410-0"));
        returnItemList.add(new ReturnItemDTO(2, "978-3-16-148410-1"));
        returnItemList.add(new ReturnItemDTO(3, "978-3-16-148410-2"));
        ReturnNoteDTO returnNoteDTO = new ReturnNoteDTO("104ccff3-c584-4782-a582-8a06479b4600", returnItemList);

        when(returnNoteService.createNewReturnNote(returnNoteDTO)).thenReturn(returnNoteDTO);

        assertEquals(returnNoteDTO.getMemberId(), returnNoteController.createNewReturnNote(returnNoteDTO).getBody().getMemberId());
    }
}