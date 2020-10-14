package xiaomi.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //필드(멤버)에 대해 셋팅해주는 생성자 
@Getter
public enum Role {
	/*열거형 데이터*/
	GUEST("ROLE_GUEST","GUEST"), USER("ROLE_USER","USER");
	
	private final String key;
	private final String title; 
	//Spring Security에서는 권한 코드에 항상 'ROLE_*'가 항상 있어야 함
	
	
	
}
