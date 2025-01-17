package cotato.cokathon_backend.domains.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cotato.cokathon_backend.common.dto.DataResponse;
import cotato.cokathon_backend.domains.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/bot")
@RequiredArgsConstructor
public class CustomBotController {

	private final ChatService chatService;

	@GetMapping("/emotion/members/{memberId}")
	public ResponseEntity<DataResponse<Void>> calcEmotionPoint(
		@RequestParam(name = "prompt") String prompt,
		@PathVariable(name = "memberId") Long memberId
	) {
		chatService.calcEmotionPointAndSave(prompt, memberId);

		return ResponseEntity.ok(DataResponse.ok());
	}
}
