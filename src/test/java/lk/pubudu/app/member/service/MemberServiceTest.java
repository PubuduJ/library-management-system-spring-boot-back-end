package lk.pubudu.app.member.service;

import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.exception.NotFoundException;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import lk.pubudu.app.util.Transformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private Transformer transformer;
    @InjectMocks
    private MemberService memberService;

    @Test
    void createMember() {
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");

        when(memberRepository.findByContact(memberDTO.getContact())).thenReturn(Optional.empty());
        when(transformer.toMemberEntity(memberDTO)).thenReturn(member);
        when(memberRepository.save(member)).thenReturn(member);
        when(transformer.toMemberDTO(member)).thenReturn(memberDTO);

        assertDoesNotThrow(() -> {
            memberService.createMember(memberDTO);
        });
    }

    @Test
    void loadAllMembers() {
        ArrayList<Member> allMembers = new ArrayList<>();
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        allMembers.add(member);

        when(memberRepository.findAll()).thenReturn(allMembers);
        when(transformer.toMemberDTO(member)).thenReturn(memberDTO);

        assertEquals(1, memberService.loadAllMembers().size());
    }

    @Test
    void searchMembers() {
        String query = "Pubudu";
        ArrayList<Member> members = new ArrayList<>();
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        members.add(member);

        when(memberRepository.findMembersByQuery("%"+query+"%")).thenReturn(members);
        when(transformer.toMemberDTO(member)).thenReturn(memberDTO);

        assertDoesNotThrow(() -> {
            memberService.searchMembers(query);
        });
    }

    @Test
    void deleteMember() {
        String id = "104ccff3-c584-4782-a582-8a06479b4600";
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");

        when(memberRepository.findById(id)).thenReturn(Optional.of(member));
        memberRepository.deleteById(member.getId());

        assertDoesNotThrow(() -> {
            memberService.deleteMember(id);
        });
    }

    @Test
    void getMemberDetails() {
        String id = "104ccff3-c584-4782-a582-8a06479b4600";

        when(memberRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            memberService.getMemberDetails(id);
        });
    }

    @Test
    void loadMembersByPage() {
        int size = 5;
        int page = 0;
        int offset = (page - 1) * size;
        ArrayList<Member> members = new ArrayList<>();
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        members.add(member);

        when(memberRepository.findMembersByPage(size, offset)).thenReturn(members);
        when(transformer.toMemberDTO(member)).thenReturn(memberDTO);

        assertEquals(1, memberService.loadMembersByPage(size, page).size());
    }

    @Test
    void searchMembersByPage() {
        String query = "Pubudu";
        int size = 5;
        int page = 0;
        int offset = (page - 1) * size;
        ArrayList<Member> members = new ArrayList<>();
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        Member member = new Member("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");
        members.add(member);

        when(memberRepository.searchMembersByPage("%"+query+"%", size, offset)).thenReturn(members);
        when(transformer.toMemberDTO(member)).thenReturn(memberDTO);

        assertEquals(1, memberService.searchMembersByPage(query, size, page).size());
    }

    @Test
    void updateMember() {
        MemberDTO memberDTO = new MemberDTO("104ccff3-c584-4782-a582-8a06479b4600", "Pubudu", "Horana", "-71-1234567");

        when(memberRepository.findById(memberDTO.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            memberService.updateMember(memberDTO.getId(), memberDTO);
        });
    }
}