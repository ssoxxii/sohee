package xiaomi.service;

import java.util.List;
import java.util.Vector;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;
import xiaomi.domain.dto.JpaBoardRequestDto;
import xiaomi.domain.dto.JpaBoardRequestUpdateDto;
import xiaomi.domain.dto.JpaBoardResponseDto;
import xiaomi.domain.dto.MyPageDto;
import xiaomi.domain.entity.JpaBoard;
import xiaomi.domain.entity.JpaBoardRepository;

@Log4j2
@Service
public class JpaBoardServiceImpl implements JpaBoardService {
	@Autowired
	private JpaBoardRepository repository;

	@Override
	public List<JpaBoardResponseDto> findAll() {
		List<JpaBoard> result=repository.findAll();
		
		//create collection<JpaBoardResponseDto>
		List<JpaBoardResponseDto> list=new Vector<>();
		for(JpaBoard entity : result) {
			//result에저장된 entity정보를 JpaBoardResponseDto 변경후
			JpaBoardResponseDto dto=new JpaBoardResponseDto(entity);
			//List<JpaBoardResponseDto> 에 저장
			list.add(dto);
		}
		
		return list;
	}

	@Override
	public void save(JpaBoardRequestDto dto) {
		repository.save(dto.toEntity());
	}

	@Override
	public JpaBoardResponseDto findById(Long no) {
		JpaBoard result=repository.findById(no).orElse(null);
		//DB결과가 Entity객체이므로 Dto로 데이터 변경하여 리턴하자..
		JpaBoardResponseDto dto=new JpaBoardResponseDto(result);
		
		return dto;
	}
	
	@Transactional
	@Override
	public void update(JpaBoardRequestUpdateDto update) {
		repository.findById(update.getNo())
			.map(result->result.update(update))
			.orElse(null);
		
	}

	@Override
	public void delete(Long no) {
		repository.deleteById(no);
	}

	@Override
	public ModelAndView findAll(int page) {
		long start=System.nanoTime();
		//int page=0;//첫페이지
		int size=10;//페이지에 게시될 게시글수
		Sort sort=Sort.by(Direction.DESC, "no");//정렬방법,정렬요소
		
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<JpaBoard> resultPage=repository.findAll(pageable);
		
		//PageDto<JpaBoard> pageDto=new PageDto<>(resultPage);
		MyPageDto pageDto=new MyPageDto(page, resultPage.getTotalPages());
		//log.debug("size :"+resultPage.getSize());
		log.debug("page-tot :"+resultPage.getTotalPages());
		//log.debug("getNumber :"+resultPage.getNumber());
		//log.debug("getNumberOfElements :"+resultPage.getNumberOfElements());
		//log.debug(resultPage.isFirst());
		//log.debug(resultPage.isLast());
		//log.debug(resultPage.hasNext());
		//log.debug(resultPage.hasPrevious());
		
		
		//page에서 List<JpaBoard> 얻어오기
		List<JpaBoard> result=resultPage.getContent();
		//create collection<JpaBoardResponseDto>
		List<JpaBoardResponseDto> list=new Vector<>();
		for(JpaBoard entity : result) {
			//result에저장된 entity정보를 JpaBoardResponseDto 변경후
			JpaBoardResponseDto dto=new JpaBoardResponseDto(entity);
		
			
			//List<JpaBoardResponseDto> 에 저장
			list.add(dto);
		}
		ModelAndView mv =new ModelAndView();
		mv.addObject("jpaList", list);
		mv.addObject("pageInfo", pageDto);
		long end=System.nanoTime();
		System.out.println((end-start)/1000000000.0+ "nano초");
		return mv;
	}
	
	
}
