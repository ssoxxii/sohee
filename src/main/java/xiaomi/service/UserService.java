package xiaomi.service;

import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.UserReqeustDto;

public interface UserService {

	ModelAndView save(UserReqeustDto dto);

	ModelAndView login(UserReqeustDto dto);

	void logout();
	

}
