package xiaomi.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import xiaomi.domain.entity.JpaReply;

@NoArgsConstructor
@Data
public class JpaReplyResponseDto {
	private long r_no;
	private String content;
	private String writer;
	private LocalDateTime reg_date;
	private long b_no;
	
	public JpaReplyResponseDto(JpaReply reply){
		r_no=reply.getR_no();
		content=reply.getContent();
		writer=reply.getWriter();
		reg_date=reply.getReg_date();
		b_no=reply.getB_no();
	}
}
