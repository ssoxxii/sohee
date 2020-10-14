package xiaomi.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import xiaomi.domain.dto.GuestRequestDto;
import xiaomi.domain.dto.GuestResponseDto;
import xiaomi.domain.dto.MyPageDto;
import xiaomi.domain.entity.Guest;
import xiaomi.domain.entity.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	GuestRepository repository;
	
	@Override
	public void reg(GuestRequestDto dto) {
		repository.save(dto.toEntity());
		
	}

	
	@Override
	public void findAll(Model model, int page) {
		Sort sort=Sort.by(Direction.DESC, "commno"); 
		Pageable pageable=PageRequest.of(page-1, 5, sort);
		Page<Guest> pageResult=repository.findAll(pageable);
		
		MyPageDto myPageDto=new MyPageDto(page, pageResult.getTotalPages());
		System.out.println(myPageDto);
		List<GuestResponseDto> list=new Vector<>();
		for(Guest reply:pageResult.getContent()) {
			GuestResponseDto dto=new GuestResponseDto(reply);
			list.add(dto);
		}
		
		model.addAttribute("commList",list);
		model.addAttribute("pageInfo", myPageDto);
		
	}
}
