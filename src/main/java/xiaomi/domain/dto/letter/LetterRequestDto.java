package xiaomi.domain.dto.letter;

import lombok.Data;
import xiaomi.domain.entity.letter.Letter;

@Data
public class LetterRequestDto {
	private String contents;
	private String writer;
	
	public Letter toEntity() {
		return Letter.builder()
				.contents(contents)
				.writer(writer)
				.build();
	}
	
}
