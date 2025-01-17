package cotato.cokathon_backend.domains.member.entity;

import static lombok.AccessLevel.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
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

	@Builder
	private Member(String email) {
		this.email = email;
	}
}
