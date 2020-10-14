package xiaomi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xiaomi.domain.dto.GuestRequestDto;
import xiaomi.domain.dto.GuestResponseDto;
import xiaomi.service.GuestService;

@Controller
public class GuestController {
	@Autowired
	GuestService service;
	
	
	@PostMapping("/guest/reg")
	public String list(GuestRequestDto dto) {
		System.out.println(dto);
		service.reg(dto);
		return "/guest/list";
	}
	
	
	@GetMapping("/guest/listItem/{page}")
	public String list(Model model, @PathVariable int page) {
		service.findAll(model, page);
		
		return "/guest/CommentList";
	}
	
	@GetMapping("/guest/list/{page}")
	public String list(@PathVariable int page, Model model) {
		model.addAttribute("page", page);
		return "/guest/list";
	}
	
	
}
