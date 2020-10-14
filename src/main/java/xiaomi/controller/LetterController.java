package xiaomi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.letter.LetterRequestDto;
import xiaomi.service.letter.LetterService;


@Controller
public class LetterController {
	
	@Autowired
	private LetterService service;
	
	/*list&detail*/
	@GetMapping("/letter/list")
	public ModelAndView list() {
		ModelAndView mv=service.findAll();
		mv.setViewName("/letter/list");
		return mv;
	}
	
	/*write*/
	@GetMapping("/letter/write")
	public String write() {
		return "/letter/write";
	}
	
	@PostMapping("/letter/write")
	public String save(LetterRequestDto dto) {
		service.save(dto);
		return "redirect:/letter/list";
	}
}
