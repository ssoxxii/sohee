package xiaomi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.JpaBoardRequestDto;
import xiaomi.domain.dto.JpaBoardRequestUpdateDto;
import xiaomi.domain.dto.JpaBoardResponseDto;
import xiaomi.service.JpaBoardService;

@Controller
public class JpaController {
	
	@Autowired
	private JpaBoardService service;

	/*페이징*/
	/*게시판 이동 및 db 불러오기*/
	@GetMapping("/jpa/list/{page}")
	public ModelAndView list(@PathVariable int page) {
		//페이지에 갖고갈 데이터 갖고작업을
		//누구한테 시킬까요?
		ModelAndView mv=service.findAll(page);
		//model.addAttribute("jpaList", list);
		mv.setViewName("/jpa/list");//이동할 페이지 정보
		
		return mv;
	}
	
	/*게시판 글쓰기*/
	@GetMapping("/jpa/write")
	public String write() {
		return "/jpa/write";
	}
	
	@PostMapping("jpa/write")
	public String write(JpaBoardRequestDto dto, HttpServletRequest request) {
		dto.setUser_ip(request.getRemoteAddr() );
		service.save(dto);
		return "redirect:/jpa/list/1";
	}
	
	/*게시글 디테일*/
	@GetMapping("/jpa/{no}")
	public String detail(@PathVariable Long no, Model model, @Param("page") int page) {
		JpaBoardResponseDto dto=service.findById(no);
		//MVC 
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		
		return "/jpa/detail";//페이지이동
	}
	
	/*게시글 수정*/
	@PostMapping("/jpa/edit")
	public String edit(JpaBoardRequestUpdateDto update) {
		service.update(update);
		return "redirect:/jpa/list/1";
	}
	
	/*게시글 삭제*/
	@GetMapping("/jpa/delete/{no}")
	public String delete(@PathVariable Long no) {
		service.delete(no);
		return "redirect:/jpa/list/1";
	}
	
	/*검색*/
	
}
