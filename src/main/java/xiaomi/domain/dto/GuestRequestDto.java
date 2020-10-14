package xiaomi.domain.dto;

import lombok.Data;
import xiaomi.domain.entity.Guest;

@Data
public class GuestRequestDto {
	private String content;
	private String writer;
	
	public Guest toEntity() {
		return Guest.builder()
				.content(content)
				.writer(writer)
				.build();
	}
}
