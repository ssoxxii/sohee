package xiaomi.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import xiaomi.domain.entity.Guest;

@NoArgsConstructor
@Data
public class GuestResponseDto {
	private long commno;
	private String content;
	private String writer;
	private LocalDateTime reg_date;
	
	public GuestResponseDto(Guest guest){
		commno=guest.getCommno();
		content=guest.getContent();
		writer=guest.getWriter();
		reg_date=guest.getReg_date();
	}
}
