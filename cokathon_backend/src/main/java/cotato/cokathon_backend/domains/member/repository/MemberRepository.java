package cotato.cokathon_backend.domains.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.cokathon_backend.domains.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	boolean existsByEmail(String email);
}
