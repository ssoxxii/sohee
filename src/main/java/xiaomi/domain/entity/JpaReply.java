package xiaomi.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)//날짜 자동으로 입력처리하기위해..
@Table(name = "xiaomi_jpareply")
@Entity
public class JpaReply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long r_no;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String writer;
	
	
	@CreatedDate //날짜 자동 입력
	private LocalDateTime reg_date;
	
	private long b_no;
	
	@Builder
	public JpaReply(String content, String writer, long b_no ) {
		super();
		this.content = content;
		this.writer = writer;
		this.b_no = b_no;
	}

	
	
	
	
}
