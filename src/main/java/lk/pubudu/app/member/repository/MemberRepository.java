package lk.pubudu.app.member.repository;

import lk.pubudu.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByContact(String contact);

    @Query("SELECT M FROM Member M WHERE M.id LIKE ?1 OR M.name LIKE ?1 OR M.address LIKE ?1 OR M.contact LIKE ?1")
    List<Member> findMembersByQuery(String query);

}
