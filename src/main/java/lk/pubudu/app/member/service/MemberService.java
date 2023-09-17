package lk.pubudu.app.member.service;

import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.member.repository.MemberRepository;
import lk.pubudu.app.util.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Transformer transformer;

    public MemberDTO createMember(MemberDTO memberDTO) {
        return null;
    }
}
