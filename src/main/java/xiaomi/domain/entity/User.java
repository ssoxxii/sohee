package xiaomi.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xiaomi.domain.dto.Role;

@ToString
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)//entity생성시 자동으로 구현 (날짜)
@Entity
public class User {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)//MYSQL//ORACLE은 Sequence로
	@Id
	Long id;
	
	@Column(nullable = false)
	String email;
	@Column(nullable = false)
	String pw;
	
	@Enumerated(EnumType.STRING)//GUEST,USER로 저장하여 알아보기 쉽게(default는 int)
	@Column(nullable = false)
	Role role;//Role.GUEST/Role.USER가 올 수 있음
	@Column(nullable = false)
	String name;
	
	@Column
	String picture;
	
	@CreatedDate //XiaomiApplication에 auditing추가하고 entity에 listener추가
	@Column(nullable = false)
	LocalDateTime createdate;

	//dto에서 toEntity로 만들기 위해 생성자 생성
	@Builder // default constructor 필요. @@NoArgsConstructor
	public User(String email, String pw, Role role, String name, String picture) {
		super();
		this.email = email;
		this.pw = pw;
		this.role = role;
		this.name = name;
		this.picture = picture;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}

	public User update(String name, String picture) {
		this.name=name;
		this.picture=picture;
		return this;
	}
	
	
}
