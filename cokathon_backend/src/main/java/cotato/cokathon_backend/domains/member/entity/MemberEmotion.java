package cotato.cokathon_backend.domains.member.entity;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberEmotion {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_emotion_id")
	private Long id;

	@ColumnDefault("0")
	private int happiness = 0;

	@ColumnDefault("0")
	private int sadness = 0;

	@ColumnDefault("0")
	private int anger = 0;

	@ColumnDefault("0")
	private int fear = 0;

	@ColumnDefault("0")
	private int love = 0;

	@ColumnDefault("0")
	private int disgust = 0;

	@ColumnDefault("0")
	private int surprise = 0;

	@ColumnDefault("0")
	private int gratitude = 0;

	@ColumnDefault("0")
	private int regret = 0;

	@ColumnDefault("0")
	private int hope = 0;
}
