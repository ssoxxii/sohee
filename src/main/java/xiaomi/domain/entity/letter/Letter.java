package xiaomi.domain.entity.letter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "xiaomi_letter")
@Entity
public class Letter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	@Column(nullable = false)
	private String contents;
	@Column(nullable = false, columnDefinition = "text")
	private String writer;
	@CreatedDate
	private LocalDateTime reg_date;
	
	@Builder
	public Letter(String contents, String writer) {
		this.contents = contents;
		this.writer = writer;
	}
	
	
}
