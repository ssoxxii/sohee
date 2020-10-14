package xiaomi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xiaomi.domain.dto.MailRequestDto;
import xiaomi.service.MailService;

@Controller
public class MailController {
	
	@Autowired
	private MailService service;
	
	@ResponseBody
	@PostMapping("/mail")
	public void mail(String email) {
		//System.out.println(email);
		service.mailSend(email);
	}
	
	@ResponseBody
	@PostMapping("/mail/check")
	public String mailCheck(MailRequestDto dto) {
		//System.out.println(dto);
		return service.mailCheck(dto);
	}
	
}
