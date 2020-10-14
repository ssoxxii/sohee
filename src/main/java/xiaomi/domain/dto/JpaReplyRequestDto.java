package xiaomi.domain.dto;

import lombok.Data;
import xiaomi.domain.entity.JpaReply;

@Data
public class JpaReplyRequestDto {
	private String content;
	private String writer;
	private long b_no;
	
	public JpaReply toEntity() {
		return JpaReply.builder()
				.content(content)
				.writer(writer)
				.b_no(b_no)
				.build();
	}
}
