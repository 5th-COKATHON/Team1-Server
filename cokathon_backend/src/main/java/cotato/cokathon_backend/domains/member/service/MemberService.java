package cotato.cokathon_backend.domains.member.service;

import static cotato.cokathon_backend.common.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.cokathon_backend.common.exception.ApiException;
import cotato.cokathon_backend.domains.member.dto.request.CreateMemberRequest;
import cotato.cokathon_backend.domains.member.dto.response.CreateMemberResponse;
import cotato.cokathon_backend.domains.member.entity.Member;
import cotato.cokathon_backend.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	// 멤버 생성
	@Transactional
	public CreateMemberResponse createMember(CreateMemberRequest request) {
		// 이메일 중복 시 에러 발생
		if (isExistEmail(request.email())) {
			throw ApiException.from(MEMBER_EMAIL_DUPLICATION);
		}

		Member member = Member.builder()
			.email(request.email())
			.build();

		member = memberRepository.save(member);

		return new CreateMemberResponse(member.getId());
	}

	// 이메일 중복 체크
	public boolean isExistEmail(String email) {
		return memberRepository.existsByEmail(email);
	}
}
