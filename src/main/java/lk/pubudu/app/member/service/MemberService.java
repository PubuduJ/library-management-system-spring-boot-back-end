package lk.pubudu.app.member.service;

import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.member.entity.Member;
import lk.pubudu.app.member.repository.MemberRepository;
import lk.pubudu.app.util.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Transformer transformer;

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
}
