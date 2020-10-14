package xiaomi.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuestRepository extends JpaRepository<Guest, Long> {
	
	//JPQL
	//@Query ("select g from Guest g order by commNo desc")
	//List<Guest> findByNo(@Param() long commNo);
	
	/*
	 *  Spring-Data-JPA에서 언더스코어(_)가 properties를 찾기 위한 탐색 경로 지정 예약어
	 * 메서드 쿼리 전략
	 * (쿼리 메소드: 메소드이름으로 쿼리를 생성하는 기능) 
	 * 조건 컬럼, 검색컬럼이나 정렬컬럼으로 용하는 컬럼은 소문자로 만드는 게 편리
	 * 1. By 뒤에는 컬러ㅓㅁ명 -> 첫 글자 대문자로 표기해서 column명이 bno면 Bno
	 * 2. 컬럼명을 carmel(낙타법)이나 snake(스네이크'_') 사용하지 말 것
	 */
	
}
