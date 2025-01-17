package cotato.cokathon_backend.domains.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalcEmotionPointResponse {

	private GPTEmotionResponse emotionResponse;
	private EmotionPointResponse emotionPointResponse;
}
