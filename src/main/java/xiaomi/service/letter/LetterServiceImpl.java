package xiaomi.service.letter;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.letter.LetterRequestDto;
import xiaomi.domain.dto.letter.LetterResponseDto;
import xiaomi.domain.entity.letter.Letter;
import xiaomi.domain.entity.letter.LetterRepository;

@Service
public class LetterServiceImpl implements LetterService {

	@Autowired
	private LetterRepository repository;
	
	@Override
	public void save(LetterRequestDto dto) {
		repository.save(dto.toEntity());
	}

	@Override
	public ModelAndView findAll() {
		List<Letter> result=repository.findAll();
		List<LetterResponseDto> list=new Vector<>();
		for(Letter entity:result) {
			LetterResponseDto dto= new LetterResponseDto(entity);
			list.add(dto);
		}
		ModelAndView mv =new ModelAndView();
		System.out.println(list.size());
		mv.addObject("jpaList", list);
		return mv;
	}

}
