package cotato.cokathon_backend.domains.member.service;

import static cotato.cokathon_backend.common.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.cokathon_backend.common.exception.ApiException;
import cotato.cokathon_backend.domains.ai.dto.EmotionPointResponse;
import cotato.cokathon_backend.domains.ai.dto.EmotionResponse;
import cotato.cokathon_backend.domains.ai.dto.GPTEmotionResponse;
import cotato.cokathon_backend.domains.member.dto.request.CreateMemberRequest;
import cotato.cokathon_backend.domains.member.dto.response.CreateMemberResponse;
import cotato.cokathon_backend.domains.member.dto.response.FindMemberEmotionResponse;
import cotato.cokathon_backend.domains.member.dto.response.FindMemberPointResponse;
import cotato.cokathon_backend.domains.member.entity.Member;
import cotato.cokathon_backend.domains.member.entity.MemberEmotion;
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
			Member member = memberRepository.findByEmail(request.email())
				.orElseThrow(() -> ApiException.from(MEMBER_NOT_FOUND));
			return new CreateMemberResponse(member.getId());
		}

		MemberEmotion memberEmotion = new MemberEmotion();
		Member member = Member.builder()
			.email(request.email())
			.memberEmotion(memberEmotion)
			.build();

		member = memberRepository.save(member);

		return new CreateMemberResponse(member.getId());
	}

	// 이메일 중복 체크
	public boolean isExistEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	public FindMemberPointResponse findMemberPoint(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> ApiException.from(MEMBER_NOT_FOUND));

		return new FindMemberPointResponse(member.getPositive(), member.getNegative());
	}

	// 멤버의 감정 포인트 조회
	public FindMemberEmotionResponse findMemberEmotion(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> ApiException.from(MEMBER_NOT_FOUND));

		return FindMemberEmotionResponse.from(member.getMemberEmotion());
	}

	// 멤버 긍정, 부정 계산
	public EmotionPointResponse calcEmotionPoint(GPTEmotionResponse emotionResponse) {
		int positive = 0;
		int negative = 0;

		// Positive emotions
		positive += emotionResponse.getHappiness() * 20;
		positive += emotionResponse.getLove() * 20;
		positive += emotionResponse.getGratitude() * 20;
		positive += emotionResponse.getHope() * 20;
		positive += emotionResponse.getSurprise() * 20;

		// Negative emotions
		negative += emotionResponse.getSadness() * 10;
		negative += emotionResponse.getAnger() * 10;
		negative += emotionResponse.getFear() * 10;
		negative += emotionResponse.getDisgust() * 10;
		negative += emotionResponse.getRegret() * 10;

		return new EmotionPointResponse(positive, negative);
	}
}
