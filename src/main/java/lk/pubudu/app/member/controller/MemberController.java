package lk.pubudu.app.member.controller;

import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/api/members")
@RequiredArgsConstructor
@CrossOrigin
public class MemberController {

    private final MemberService memberService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberDTO));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<MemberDTO>> loadAllMembers() {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.loadAllMembers());
    }

    @GetMapping(params = "q", produces = "application/json")
    public ResponseEntity<List<MemberDTO>> searchMembers(@RequestParam String q) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.searchMembers(q));
    }
}
