package xiaomi.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaomi.domain.dto.JpaReplyRequestDto;
import xiaomi.domain.dto.JpaReplyResponseDto;
import xiaomi.domain.entity.JpaReply;
import xiaomi.domain.entity.JpaReplyRepository;

@Service
public class JpaReplyServiceImpl implements JpaReplyService {
	
	@Autowired
	JpaReplyRepository repository;
	
	@Override
	public void reg(JpaReplyRequestDto dto) {
		repository.save(dto.toEntity());
		
	}

	@Override
	public List<JpaReplyResponseDto> findByBNo(long b_no) {
		List<JpaReply> result=repository.findByBNo(b_no);
		//List<JpaReply> result=repository.findByBNoOrderByRNoDesc(b_no);
		
		List<JpaReplyResponseDto> list=new Vector<>();
		for(JpaReply reply:result) {
			JpaReplyResponseDto dto=new JpaReplyResponseDto(reply);
			list.add(dto);
		}
		
		return list;
	}
}
