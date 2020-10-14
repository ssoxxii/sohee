package xiaomi.domain.dto;

import lombok.Data;
import xiaomi.domain.entity.Verification;

@Data
public class MailRequestDto {
	//ajax랑 변수명 통일시켜야 함
	private String email;
	private String code;
	
	//data넘겨받아서 db업무담당인 entity에 넘김
	public Verification toEntity() {
		return Verification.builder()
				.email(email)
				.code(code)
				.build();
	}
}
