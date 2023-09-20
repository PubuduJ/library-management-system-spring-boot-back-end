package lk.pubudu.app.member.service;

import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.exception.ConstraintViolationException;
import lk.pubudu.app.exception.NotFoundException;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import lk.pubudu.app.util.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Transformer transformer;

    @Transactional
    public MemberDTO createMember(MemberDTO memberDTO) {
        Optional<Member> availability = memberRepository.findByContact(memberDTO.getContact());
        if (availability.isPresent()) {
            throw new DuplicateKeyException("Member is already exist in the system");
        }
        Member member = memberRepository.save(transformer.toMemberEntity(memberDTO));
        return transformer.toMemberDTO(member);
    }

    public List<MemberDTO> loadAllMembers() {
        List<Member> allMembers = memberRepository.findAll();
        ArrayList<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member : allMembers) {
            memberDTOList.add(transformer.toMemberDTO(member));
        }
        return memberDTOList;
    }

    public List<MemberDTO> searchMembers(String q) {
        String query = "%".concat(q).concat("%");
        List<Member> membersByQuery = memberRepository.findMembersByQuery(query);
        ArrayList<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member : membersByQuery) {
            memberDTOList.add(transformer.toMemberDTO(member));
        }
        return memberDTOList;
    }

    @Transactional
    public void deleteMember(String id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new NotFoundException("Member doesn't exist in the system");
        }
        memberRepository.deleteById(member.get().getId());
    }

    public MemberDTO getMemberDetails(String id) {
        Optional<Member> availability = memberRepository.findById(id);
        if (availability.isEmpty()) {
            throw new NotFoundException("Member doesn't exist in the system");
        }
        return transformer.toMemberDTO(availability.get());
    }

    public List<MemberDTO> loadMembersByPage(int size, int page) {
        int offset = (page - 1) * size;
        List<Member> members = memberRepository.findMembersByPage(size, offset);
        ArrayList<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member : members) {
            memberDTOList.add(transformer.toMemberDTO(member));
        }
        return memberDTOList;
    }

    public List<MemberDTO> searchMembersByPage(String q, int size, int page) {
        String query = "%".concat(q).concat("%");
        int offset = (page - 1) * size;
        List<Member> membersByPage = memberRepository.searchMembersByPage(query, size, offset);
        ArrayList<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member : membersByPage) {
            memberDTOList.add(transformer.toMemberDTO(member));
        }
        return memberDTOList;
    }

    @Transactional
    public MemberDTO updateMember(String id, MemberDTO memberDTO) {
        Optional<Member> availability = memberRepository.findById(id);
        if (availability.isEmpty()) {
            throw new NotFoundException("Member doesn't exist in the system");
        }
        Member updatedMember = memberRepository.save(transformer.toMemberEntity(memberDTO));
        return transformer.toMemberDTO(updatedMember);
    }
}
