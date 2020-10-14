package xiaomi.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //default 생성자 대신 사용
@Data
public class FileResponseDto {
	private long no;
	private String fileName;
	private String file_url;
	private String t_text;
	private String d_text;
	private String used;
	
	public FileResponseDto(long no, String fileName, String file_url, String t_text, String d_text, String used) {
		this.no = no;
		this.fileName = fileName;
		this.file_url = file_url;
		this.t_text = t_text;
		this.d_text = d_text;
		this.used = used;
	}
	
	
}
