package cotato.cokathon_backend.domains.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.cokathon_backend.common.dto.DataResponse;
import cotato.cokathon_backend.common.dto.ErrorResponse;
import cotato.cokathon_backend.domains.member.dto.request.CreateMemberRequest;
import cotato.cokathon_backend.domains.member.dto.response.CreateMemberResponse;
import cotato.cokathon_backend.domains.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
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
}
