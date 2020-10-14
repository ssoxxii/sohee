package xiaomi.domain.dto;

import lombok.Data;
import xiaomi.domain.entity.User;

@Data
public class LogInfo {

	private String email;
	private String name;
	
	public LogInfo(User user) {
		this.email = user.getEmail();
		this.name = user.getName();
	}
	
	
}
