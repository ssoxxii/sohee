package xiaomi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;
import xiaomi.domain.dto.UserReqeustDto;
import xiaomi.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	/*회원가입*/
	@GetMapping("/user/signup")
	public String join() {
		return "/sign/join";
	}
	
	@PostMapping("/user/signup")
	public ModelAndView join(UserReqeustDto dto) {
		//log.debug(dto);
		ModelAndView mv=service.save(dto);
		mv.setViewName("/sign/login");
		return mv;
	}
	
	/*로그인*/
	@GetMapping("/user/login")
	public String login() {
		return "/sign/login";
	}
	@PostMapping("/user/login")
	public ModelAndView login(UserReqeustDto dto) {
		//ModelAndView mv=service.login(dto);
		return service.login(dto);
	}
	
	/*로그아웃*/
	@GetMapping("/user/logout")
	public String logout() {
		service.logout();
		return "redirect:/";
	}
	
	
}
