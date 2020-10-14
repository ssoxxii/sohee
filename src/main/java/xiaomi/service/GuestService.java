package xiaomi.service;

import java.util.List;

import org.springframework.ui.Model;

import xiaomi.domain.dto.GuestRequestDto;
import xiaomi.domain.dto.GuestResponseDto;

public interface GuestService {


	void reg(GuestRequestDto dto);

	
	void findAll(Model model, int page);

}
