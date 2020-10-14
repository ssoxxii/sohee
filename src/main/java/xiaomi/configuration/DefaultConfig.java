package xiaomi.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class DefaultConfig {
	@Autowired
	private ApplicationContext applicationContext;//mapper읽기
	//어플리케이션이 동작되는 동안 필요한 클래스 모아두는 곳 (데이터 누적)
	
	@Autowired
	DataSource dataSource;//굳이 셋팅하지 않아도 properties 데이터를 읽어온다.
	
	/*mybatis 사용시 설정하는 부분*/
	@Bean //bean등록은 싱글톤 패턴 의미
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		//sql세션팩토리 
		SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
		log.debug(dataSource);
		
		//datasource
		factoryBean.setDataSource(dataSource);
		//mapper
		Resource[] mapperLocations=
				applicationContext.getResources("classpath:/mapper/**/mapper-*.xml");
		// /mapper/**/ : mapper폴더 아래 모든 폴더를 지칭
	    // mapper-*.xml :  mapper-로 시작하고 확장자가 xml인 모든 파일을 지칭
		factoryBean.setMapperLocations(mapperLocations);
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		log.debug(sqlSessionFactory);
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
