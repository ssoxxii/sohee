package xiaomi.configuration.auth.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import xiaomi.domain.dto.Role;
import xiaomi.domain.entity.User;

//DTO 객체입니다.
@Getter
public class OAuthAttributes {
	
	Map<String, Object> attributes;
	String nameAttributeKey;
	String name;
	String email;
	String picture;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String picture) {
		super();
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
	
	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		//구글인지 페북인지 구분하는게 registrationId
		
		if(registrationId.equals("google")) {
			return ofGoogle(userNameAttributeName, attributes);
		}else if(registrationId.equals("naver")) {
			return ofNaver("id", attributes);
		}
		return null;
		
	}
	
	/*Naver*/
	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> response=(Map<String, Object>) attributes.get("response");
		return OAuthAttributes.builder()
				.name((String) response.get("name"))
				.email((String) response.get("email"))
				.picture((String) response.get("profile_image"))
				.attributes(response)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	/*Google*/
	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		
		return OAuthAttributes.builder()
				.name((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.picture((String) attributes.get("picture"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
		
	}
	
	public User toEntity() {
		return User.builder()
				.name(name)
				.email(email)
				.picture(picture)
				.pw("oauthuser")
				.role(Role.GUEST)
				.build();
	}
}
