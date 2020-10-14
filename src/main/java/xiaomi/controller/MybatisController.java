package xiaomi.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.MybatisDto;
import xiaomi.service.MybatisService;

@Controller
public class MybatisController {
	
	@Autowired
	private MybatisService service;//controller의 직원 service 임다
	
	/*수정*/
	@PostMapping("/mybatis/edit")
	public String edit(MybatisDto dto) {
		service.edit(dto);
		return "redirect:/mybatis/"+dto.getNo();
	}
	
	/*디테일 삭제*/
	@GetMapping("/mybatis/delete/{no}")
	public String delete(@PathVariable int no) {
		service.delete(no);
		return "redirect:/mybatis/list";
	}
	/*디테일*/
	@GetMapping("/mybatis/{no}")
	public ModelAndView detail(@PathVariable int no, ModelAndView mv) {
		mv.setViewName("/mybatis/detail");//페이지 이동
		MybatisDto detail=service.detail(no);
		mv.addObject("dto", detail);
		return mv;
	}
	/*쓰기*/
	@PostMapping("/mybatis/write")
	public String write(MybatisDto dto) {
		//dto.setUser_ip(request.getRemoteAddr());//service에서 셋팅해주는 게 좋음
		service.save(dto);
		return "redirect:/mybatis/list";
	}
	
	/*쓰기 페이지*/
	@GetMapping("/mybatis/write")
	public String write() {
		return "/mybatis/write";
	}
	
	/*리스트& 검색 */
	@GetMapping("/mybatis/list/{page}")
	public ModelAndView service(@PathVariable int page) {
		//db에서 data가져오기(select query->결과물 리턴)
		ModelAndView mv=service.mybatislist(page);
		mv.setViewName("/mybatis/list");
		//오늘 날짜인지 체크
		mv.addObject("today", LocalDate.now());
		// list.html 에서 데이터 읽어올 이름과 일치(이름, value)
		return mv;
	}
	/*
	@GetMapping("/mybatis/list/{page}")
	public ModelAndView service(ModelAndView mv) {
		//db에서 data가져오기(select query->결과물 리턴)
		List<MybatisDto> list=service.mybatislist();
		mv.setViewName("/mybatis/list");
		mv.addObject("mybatisList",list);
		// list.html 에서 데이터 읽어올 이름과 일치(이름, value)
		return mv;
	}
	*/
	
	/*
	@PostMapping("/mybatis/list/search/page")
	public String search(@PathVariable int page, SearchRequestDto dto) {
		//System.out.println(dto);
		ModelAndView mv=service.search(page,dto);
		return "redirect:/mybatis/list/1";
	}
	*/
	
}
