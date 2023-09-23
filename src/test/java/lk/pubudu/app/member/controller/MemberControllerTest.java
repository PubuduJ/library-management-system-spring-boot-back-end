package lk.pubudu.app.member.controller;

import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @Mock
    private MemberService memberService;
    @InjectMocks
    private MemberController memberController;

    @Test
    void createMember() {
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        ResponseEntity<MemberDTO> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(memberDTO);

        when(memberService.createMember(memberDTO)).thenReturn(memberDTO);

        assertEquals(responseEntity.getBody().toString(), memberController.createMember(memberDTO).getBody().toString());
    }

    @Test
    void loadAllMembers() {
        ArrayList<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567"));

        when(memberService.loadAllMembers()).thenReturn(memberList);

        assertEquals(1, memberController.loadAllMembers().getBody().size());
    }

    @Test
    void searchMembers() {
        String query = "Pubudu";
        ArrayList<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567"));

        when(memberService.searchMembers(query)).thenReturn(memberList);

        assertEquals(1, memberController.searchMembers(query).getBody().size());
    }

    @Test
    void deleteMember() {
        String id = "104ccff3-c584-4782-a582-8a06479b4600";

        memberService.deleteMember(id);

        assertDoesNotThrow(() -> {
            memberController.deleteMember(id);
        });
    }

    @Test
    void getMemberDetails() {
        String id = "104ccff3-c584-4782-a582-8a06479b4600";
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "071-1234567");
        ResponseEntity<MemberDTO> responseEntity = ResponseEntity.status(HttpStatus.OK).body(memberDTO);

        when(memberService.getMemberDetails(id)).thenReturn(memberDTO);

        assertEquals(memberDTO.toString(), memberController.getMemberDetails(id).getBody().toString());
    }

    @Test
    void loadMembersByPage() {
    }

    @Test
    void searchMembersByPage() {
    }

    @Test
    void updateMember() {
    }
}