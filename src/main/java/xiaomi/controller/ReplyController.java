package xiaomi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xiaomi.domain.dto.JpaReplyRequestDto;
import xiaomi.domain.dto.JpaReplyResponseDto;
import xiaomi.service.JpaReplyService;

@Controller
public class ReplyController {
	@Autowired
	JpaReplyService service;
	
	@ResponseBody //data 전달
	@PostMapping("/reply/reg")
	public String reg(JpaReplyRequestDto dto) {
		service.reg(dto);
		return "등록완료";
	}
	
	@PostMapping("/reply/list")
	public String list(long b_no, Model model) {
		System.out.println(b_no);
		List<JpaReplyResponseDto> list=service.findByBNo(b_no);
		System.out.println(list.size());
		model.addAttribute("replyList", list);
		return "/jpa/replyList";
	}
}
