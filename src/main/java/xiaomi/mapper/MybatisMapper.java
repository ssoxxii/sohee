package xiaomi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import xiaomi.domain.dto.MybatisDto;

@Mapper
public interface MybatisMapper {
	//interface를 구현할 구현체는 xml (연결필요)

	List<MybatisDto> mybatislist();
	//BoardMapper selectBoardList는 mapper.xml의 id와 일치 

	void save(MybatisDto dto);

	MybatisDto detail(int no);

	void delete(int no);

	void update(MybatisDto dto);

	List<MybatisDto> findAllDesc(RowBounds rowbounds);

	int getCountOfRows();

}