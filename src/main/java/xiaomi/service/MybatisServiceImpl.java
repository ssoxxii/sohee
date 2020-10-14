package xiaomi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import xiaomi.domain.dto.MyPageDto;
import xiaomi.domain.dto.MybatisDto;
import xiaomi.mapper.MybatisMapper;

@Service
public class MybatisServiceImpl implements MybatisService{
	@Autowired
	private MybatisMapper mapper; //mybatis는 db접근을 mapper로 함
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public ModelAndView mybatislist(int page) {
		long start=System.nanoTime();
		//return mapper.findAll();
		int limit =5;
		int offset=(page-1)*limit;
		//RowBounds 를 이용한 페이지 처리
		RowBounds rowbounds=new RowBounds(offset, limit);
		//totalPage필요
		int rowTotal=mapper.getCountOfRows();//전체 게시글수
		int pageTotal=rowTotal/limit; //13/5
		if(rowTotal%limit !=0) {
			pageTotal++;
		}
		MyPageDto myPageDto=new MyPageDto(page, pageTotal);
		
		List<MybatisDto> list=mapper.findAllDesc(rowbounds);
		
		ModelAndView mv =new ModelAndView();
		mv.addObject("pageInfo", myPageDto);
		mv.addObject("mybatisList", list);
		long end=System.nanoTime();
		System.out.println((end-start)/1000000000.0+ "nano초");
		return mv;
	}

	@Override
	public void save(MybatisDto dto) {
		//ip세팅
		dto.setUser_ip(request.getRemoteAddr());
		mapper.save(dto);
	}


	@Override
	public MybatisDto detail(int no) {
		return mapper.detail(no);
	}


	@Override
	public void delete(int no) {
		mapper.delete(no);
	}


	@Override
	public void edit(MybatisDto dto) {
		mapper.update(dto);
	}


}