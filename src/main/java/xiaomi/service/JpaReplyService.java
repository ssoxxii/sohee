package xiaomi.service;

import java.util.List;

import xiaomi.domain.dto.JpaReplyRequestDto;
import xiaomi.domain.dto.JpaReplyResponseDto;

public interface JpaReplyService {

	void reg(JpaReplyRequestDto dto);

	List<JpaReplyResponseDto> findByBNo(long b_no);

}
