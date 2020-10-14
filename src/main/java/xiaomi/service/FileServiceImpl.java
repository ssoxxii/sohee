package xiaomi.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import xiaomi.domain.dto.FileRequestDto;
import xiaomi.domain.dto.FileResponseDto;
import xiaomi.domain.entity.Files;
import xiaomi.domain.entity.FilesRepository;

@Repository
public class FileServiceImpl implements FileService {

	@Autowired
	private FilesRepository repository;
	
	@Override
	public void save(FileRequestDto dto) {
		repository.save(dto.toEntity());
	}

	@Override
	public List<FileResponseDto> findAll() {
		Sort sort=Sort.by(Direction.ASC,"no");
		List<FileResponseDto> list=new Vector<>();// vector쓰는 이유?
		List<Files> result=repository.findAll(sort);
		//dto로 바꿔주기 위해 for문 사용
		for(Files files:result) {
			FileResponseDto dto=new FileResponseDto(
					files.getNo(), 
					files.getFileName(), 
					"url('/upload/"+files.getFileName()+"')",
					files.getT_text(), 
					files.getD_text(), 
					files.getUsed());
			list.add(dto);
		}
		
		return list;
	}

	@Override
	public void delete(long no) {
		repository.deleteById(no);
	}

}
