package xiaomi.domain.dto.letter;

import java.time.LocalDateTime;

import lombok.Data;
import xiaomi.domain.entity.letter.Letter;

@Data
public class LetterResponseDto {

	private Long no;
	private String writer;
	private String contents;
	private LocalDateTime reg_date;

	public LetterResponseDto(Letter entity) {
		this.no = entity.getNo();
		this.writer = entity.getWriter();
		this.contents = entity.getContents();
		this.reg_date = entity.getReg_date();
	}
	
	
}
