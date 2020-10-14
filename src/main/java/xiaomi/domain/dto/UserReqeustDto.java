package xiaomi.domain.dto;

import lombok.Data;
import xiaomi.domain.entity.User;

@Data
public class UserReqeustDto {
	private String email;
	private String pw;
	
	public User toEntity() {
		return User.builder()
				.email(email)
				.pw(pw)
				.role(Role.USER)
				.name("NoName")
				.build();
	}
}
