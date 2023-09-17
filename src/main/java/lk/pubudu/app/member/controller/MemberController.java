package lk.pubudu.app.member.controller;

import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.member.repository.MemberRepository;
import lk.pubudu.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<MemberDTO> getMemberDetails(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemberDetails(id));
    }

    @GetMapping(params = {"size", "page"}, produces = "application/json")
    public ResponseEntity<List<MemberDTO>> loadMembersByPage(@RequestParam int size, @RequestParam int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(memberService.loadAllMembers().size()));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(memberService.loadMembersByPage(size, page));
    }

    @GetMapping(params = {"q", "size", "page"}, produces = "application/json")
    public ResponseEntity<List<MemberDTO>> searchMembersByPage(@RequestParam String q, @RequestParam int size, @RequestParam int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(memberService.loadAllMembers().size()));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(memberService.searchMembersByPage(q, size, page));
    }

    @PatchMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable String id, @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.updateMember(id, memberDTO));
    }

}
