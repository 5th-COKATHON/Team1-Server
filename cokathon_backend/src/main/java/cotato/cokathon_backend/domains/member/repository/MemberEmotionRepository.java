package cotato.cokathon_backend.domains.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.cokathon_backend.domains.member.entity.MemberEmotion;

public interface MemberEmotionRepository extends JpaRepository<MemberEmotion, Long> {

	Optional<MemberEmotion> findByMemberId(Long memberId);
}
