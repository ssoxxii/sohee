package xiaomi.configuration.auth;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xiaomi.configuration.auth.dto.OAuthAttributes;
import xiaomi.domain.dto.LogInfo;
import xiaomi.domain.entity.User;
import xiaomi.domain.entity.UserRepository;

//구글 로그인 이후 가져온 사용자 정보기반 회원가입, 정보수정, 세션저장 등 기능 처리하는 클래스
@RequiredArgsConstructor //final필드를 인젝션해주세요 ->autowired대신 final추가하고 사용
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	
	//@Autowired
	private final HttpSession httpsession;
	//@Autowired
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService delegate=new DefaultOAuth2UserService();
		OAuth2User aAuth2User=delegate.loadUser(userRequest);
		
		//소셜서비스(google, naver, kakao) 구분하기 위한 코드 
		String registrationId=userRequest.getClientRegistration().getRegistrationId();
		//data를 불러오기 위한 로그인 정보가 저장되어 있는 key값 -> naver/kakao는 지원하지 않음
		//google 기본코드: sub
		String userNameAttributeName=userRequest.getClientRegistration()
				.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		
		OAuthAttributes attributes=OAuthAttributes.of(registrationId, userNameAttributeName, aAuth2User.getAttributes());
		//socialUser db에 저장
		User user=saveOrUpdate(attributes);
		
		//session에 등록
		httpsession.setAttribute("user",new LogInfo(user));
		return new DefaultOAuth2User(
				Collections.singleton( new SimpleGrantedAuthority( user.getRoleKey() ) ),
				attributes.getAttributes(),
				attributes.getNameAttributeKey()
				);
	}
	
	
	private User saveOrUpdate(OAuthAttributes attributes) {
		
		User user=userRepository.findByEmail(attributes.getEmail())
				.map(e->e.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());
		return userRepository.save(user);
		
		//return attributes.toEntity();
	}

}
