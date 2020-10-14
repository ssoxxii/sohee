package xiaomi.service.letter;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.letter.LetterRequestDto;

@Service
public interface LetterService {

	void save(LetterRequestDto dto);

	ModelAndView findAll();

}
