package xiaomi.domain.dto;

import lombok.Data;
import xiaomi.domain.entity.Files;

@Data //dto는 다 data넣어줌 (get/set 역할)
public class FileRequestDto {
	private String t_text; //title
	private String d_text; //description
	
	private String fileName;
	
	//데이터를 저장하기 위해서는 Files 엔터티로 넘겨야함
	public Files toEntity() {
		
		//return new Files(fileName, t_text, d_text);
		return Files.builder()
				.fileName(fileName)
				.t_text(t_text)
				.d_text(d_text)
				.build();
	}
	
}
