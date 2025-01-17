package cotato.cokathon_backend.domains.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cotato.cokathon_backend.domains.ai.dto.ChatGPTRequest;
import cotato.cokathon_backend.domains.ai.dto.ChatGPTResponse;
import cotato.cokathon_backend.domains.ai.dto.EmotionResponse;

@RestController
@RequestMapping("/api/bot")
public class CustomBotController {
	@Value("${openai.model}")
	private String model;

	@Value("${openai.api.url}")
	private String apiURL;

	@Autowired
	private RestTemplate template;

	@GetMapping("/chat")
	public EmotionResponse chat(@RequestParam(name = "prompt") String prompt) {
		String processedPrompt = """
			You are an AI that analyzes human emotions in a text. 
			Read the following diary entry carefully and count the occurrences of emotions in the text. 
			The emotions to identify and count are: happiness, sadness, anger, fear, love, disgust, surprise, gratitude, regret, and hope. 
			For each emotion, provide a count of how many times it is expressed in the text. 
			
			Here is the diary entry: 
			""" + prompt + """
			
			Respond in the following JSON format:
			{
			  "happiness": <count>,
			  "sadness": <count>,
			  "anger": <count>,
			  "fear": <count>,
			  "love": <count>,
			  "disgust": <count>,
			  "surprise": <count>,
			  "gratitude": <count>,
			  "regret": <count>,
			  "hope": <count>
			}
			""";

		ChatGPTRequest request = new ChatGPTRequest(model, processedPrompt);

		// GPT 응답을 EmotionResponse로 매핑
		String jsonResponse = template.postForObject(apiURL, request, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(jsonResponse, EmotionResponse.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to parse GPT response", e);
		}
	}

}
