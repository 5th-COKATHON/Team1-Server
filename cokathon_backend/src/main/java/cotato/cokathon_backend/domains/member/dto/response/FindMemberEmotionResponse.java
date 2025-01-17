package cotato.cokathon_backend.domains.member.dto.response;

import cotato.cokathon_backend.domains.member.entity.MemberEmotion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class FindMemberEmotionResponse {

	private int happiness;
	private int sadness;
	private int anger;
	private int fear;
	private int love;
	private int disgust;
	private int surprise;
	private int gratitude;
	private int regret;
	private int hope;

	public static FindMemberEmotionResponse from(MemberEmotion memberEmotion) {
		return new FindMemberEmotionResponse(
			memberEmotion.getHappiness(),
			memberEmotion.getSadness(),
			memberEmotion.getAnger(),
			memberEmotion.getFear(),
			memberEmotion.getLove(),
			memberEmotion.getDisgust(),
			memberEmotion.getSurprise(),
			memberEmotion.getGratitude(),
			memberEmotion.getRegret(),
			memberEmotion.getHope()
		);
	}
}
