package xiaomi.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import xiaomi.domain.entity.JpaBoard;

@Data
public class JpaBoardResponseDto {
	private Long no;
	private String subject;
	private String content;
	private String user_ip;
	private String writer;
	private int count;
	private LocalDateTime reg_date;
	
	public JpaBoardResponseDto(JpaBoard entity) {
		this.no = entity.getNo();
		this.subject = entity.getSubject();
		this.content = entity.getContent();
		this.user_ip = entity.getUser_ip();
		this.writer = entity.getWriter();
		this.count = entity.getCount();
		this.reg_date = entity.getReg_date();
	}
}
