package xiaomi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.ListIndexBase;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert //null무시
@NoArgsConstructor
@Getter //db 무결성 보장
@SequenceGenerator(name = "seq_files_gen", sequenceName = "seq_files", initialValue = 1, allocationSize = 1) //oracle auto_increment
@EntityListeners(AuditingEntityListener.class)
@Table(name = "file_img") //table 이름 지정하여 생성
@Entity
public class Files {
	
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_files_gen") //oracle ver.
	@GeneratedValue (strategy = GenerationType.IDENTITY)//auto_increment 
	// auto로 하면? 0으로 시작하고, 제너레이터 seq가 돌아감 => mysql버전
	@Id
	long no;
	
	@Column(nullable = false)
	String fileName;
	@Column(nullable = false)
	String t_text;
	@Column(nullable = false)
	String d_text;
	
	@Column(columnDefinition = "varchar(255) default 'on'")
	private String used;

	@Builder //inner class를 builder 메소드로 만들어줌 
	public Files(long no, String fileName, String t_text, String d_text, String used) {
		this.no = no;
		this.fileName = fileName;
		this.t_text = t_text;
		this.d_text = d_text;
		this.used = used;
	}
	
}
