package xiaomi.domain.dto.search;

import lombok.Data;

@Data
public class SearchRequestDto {
	private String searchType;
	private String searchText;
}