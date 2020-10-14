package xiaomi.service;

import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.MybatisDto;

public interface MybatisService {

	void save(MybatisDto dto);

	MybatisDto detail(int no);

	void delete(int no);

	void edit(MybatisDto dto);

	ModelAndView mybatislist(int page);

}