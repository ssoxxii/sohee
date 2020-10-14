package xiaomi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import xiaomi.domain.dto.FileRequestDto;
import xiaomi.domain.dto.FileResponseDto;

@Service
public interface FileService {

	void save(FileRequestDto dto);

	List<FileResponseDto> findAll();

	void delete(long no);

}
