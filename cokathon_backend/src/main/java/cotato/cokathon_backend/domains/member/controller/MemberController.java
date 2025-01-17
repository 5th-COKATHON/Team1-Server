package cotato.cokathon_backend.domains.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.cokathon_backend.common.dto.DataResponse;
import cotato.cokathon_backend.common.dto.ErrorResponse;
import cotato.cokathon_backend.domains.member.dto.request.CreateMemberRequest;
import cotato.cokathon_backend.domains.member.dto.response.CreateMemberResponse;
import cotato.cokathon_backend.domains.member.dto.response.FindMemberEmotionResponse;
import cotato.cokathon_backend.domains.member.dto.response.FindMemberPointResponse;
import cotato.cokathon_backend.domains.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	@Operation(
		summary = "멤버 생성",
		description = """
			이메일을 통해 멤버 생성""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공"
			),
			@ApiResponse(
				responseCode = "409",
				description = "이미 존재하는 이메일입니다.[M-001]",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))
			)
		}
	)
	public ResponseEntity<DataResponse<CreateMemberResponse>> createMember(
		@RequestBody @Valid CreateMemberRequest request
	) {
		CreateMemberResponse response = memberService.createMember(request);

		return ResponseEntity.ok(DataResponse.from(response));
	}

	@GetMapping("/{memberId}/point")
	@Operation(
		summary = "포인트 조회",
		description = """
			멤버의 포인트를 조회한다.""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공"
			),
			@ApiResponse(
				responseCode = "404",
				description = "멤버를 찾을 수 없습니다.[M-002]",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))
			)
		}
	)
	public ResponseEntity<DataResponse<FindMemberPointResponse>> findMemberPoint(
		@PathVariable Long memberId
	) {
		FindMemberPointResponse response = memberService.findMemberPoint(memberId);

		return ResponseEntity.ok(DataResponse.from(response));
	}

	@GetMapping("/{memberId}/emotions")
	@Operation(
		summary = "감정 조회",
		description = """
			멤버의 감정 포인트들을 조회한다.""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공"
			),
			@ApiResponse(
				responseCode = "404",
				description = "멤버를 찾을 수 없습니다.[M-002]",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))
			)
		}
	)
	public ResponseEntity<DataResponse<FindMemberEmotionResponse>> findMemberEmotion(
		@PathVariable Long memberId
	) {
		FindMemberEmotionResponse response = memberService.findMemberEmotion(memberId);

		return ResponseEntity.ok(DataResponse.from(response));
	}
}
