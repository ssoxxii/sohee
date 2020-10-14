package xiaomi.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import xiaomi.domain.dto.FileRequestDto;
import xiaomi.domain.dto.FileResponseDto;
import xiaomi.domain.entity.Files;
import xiaomi.service.FileService;

@Log4j2
@Controller
public class FileController {
	
	@Autowired
	private FileService service;
	
	/*파일 삭제*/
	@GetMapping("/file/delete/{no}")
	public String delete(@PathVariable long no) {
		service.delete(no);
		return "redirect:/file/list";
	}
	
	/*파일 업로드 페이지 리스트*/
	@GetMapping("/file/upload")
	public String fileUpload(Model model) {
		//db에서 listdata가지고 오기
		List<FileResponseDto> list=service.findAll();
		model.addAttribute("list",list);
		return "/file/list";
	}
	
	/*파일 등록*/
	@GetMapping("/file/reg")
	public String fileReg() {
		return "/file/reg";
	}
	
	/*파일 등록 후 리스트 호출*/
	@PostMapping ("/file/reg")
	public String fileReg(FileRequestDto dto, MultipartFile file) throws IllegalStateException, IOException {
		String fileName=file.getOriginalFilename();
		dto.setFileName(fileName);
		//파일 업로드
		//File dest=new ClassPathResource("upload").getFile();
		ClassPathResource cr=new ClassPathResource("static/upload");
		File dir=cr.getFile();
		File dest=new File(dir,fileName);
		file.transferTo(dest);
		
		log.debug(dto);
		service.save(dto);
		return "redirect:/file/upload";
	}
}
