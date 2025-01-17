package cotato.cokathon_backend.domains.member.entity;

import static lombok.AccessLevel.*;

import org.hibernate.annotations.ColumnDefault;

import cotato.cokathon_backend.domains.ai.dto.EmotionResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "member")
public class Member {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@Column(nullable = false)
	@Setter
	@ColumnDefault("0")
	private int positive = 0;

	@Column(nullable = false)
	@Setter
	@ColumnDefault("0")
	private int negative = 0;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "member_emotion_id")
	private MemberEmotion memberEmotion;

	@Builder
	private Member(String email, MemberEmotion memberEmotion) {
		this.email = email;
		this.memberEmotion = memberEmotion;
	}

	public void updatePositiveAndNegative(EmotionResponse emotionResponse) {
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

		this.positive += positive;
		this.negative += negative;
	}
}
