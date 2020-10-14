package xiaomi.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.JpaBoardRequestDto;
import xiaomi.domain.dto.JpaBoardRequestUpdateDto;
import xiaomi.domain.dto.JpaBoardResponseDto;

public interface JpaBoardService {

	List<JpaBoardResponseDto> findAll();

	void save(JpaBoardRequestDto dto);

	JpaBoardResponseDto findById(Long no);

	void update(JpaBoardRequestUpdateDto update);

	void delete(Long no);

	ModelAndView findAll(int page);

}
