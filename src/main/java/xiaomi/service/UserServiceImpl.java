package xiaomi.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.LogInfo;
import xiaomi.domain.dto.UserReqeustDto;
import xiaomi.domain.entity.User;
import xiaomi.domain.entity.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public ModelAndView save(UserReqeustDto dto) {
		//email존재하는지 확인
		// 존재하지 않으면 .orElse(객체) 객체를 생성
		Optional<User> op=repository.findByEmail(dto.getEmail());
		ModelAndView mv=new ModelAndView();
		if(op.isPresent()) {
			//이미 존재하는 경우
			mv.addObject("log_msg","이미 존재하는 ID입니다.");
		}else {
			//존재하지 않을 경우
			repository.save(dto.toEntity());
			mv.addObject("welcome_msg","회원가입을 환영합니다."+dto.getEmail()+"계정으로 가입되었습니다.");
		}
		return mv;
	}
	
	@Autowired
	HttpSession session;
	
	@Override
	public ModelAndView login(UserReqeustDto dto) {
		Optional<User> op=repository.findByEmailAndPw(dto.getEmail(), dto.getPw());
		System.out.println(session);
		ModelAndView mv=new ModelAndView();
		if(op.isPresent()) {
			//존재->세션에 로그인정보 등록->index 페이지로 이동
			session.setAttribute("user", new LogInfo( op.get()) );
			mv.setViewName("redirect:/");
		}else {
			//비회원-> 안내메시지 날리고-> 로그인 다시 시도하도록 login 페이지로 이동
			mv.addObject("log_msg","아이디 비밀번호가 일치하지 않습니다.");
			mv.setViewName("/sign/login");
		}
		return mv;
	}

	@Override
	public void logout() {
		session.removeAttribute("user");
		
	}

}
