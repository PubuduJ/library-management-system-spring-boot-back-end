package lk.pubudu.app.util;

import lk.pubudu.app.book.entity.Book;
import lk.pubudu.app.dto.BookDTO;
import lk.pubudu.app.dto.MemberDTO;
import lk.pubudu.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Transformer {

    private final ModelMapper modelMapper;

    public Member toMemberEntity(MemberDTO memberDTO) {
        return modelMapper.map(memberDTO, Member.class);
    }

    public MemberDTO toMemberDTO(Member member) {
        return modelMapper.map(member, MemberDTO.class);
    }

    public Book toBookEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public BookDTO toBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
