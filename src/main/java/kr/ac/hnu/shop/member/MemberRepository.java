package kr.ac.hnu.shop.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
