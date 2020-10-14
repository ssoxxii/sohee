package xiaomi.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import xiaomi.domain.dto.MailRequestDto;
import xiaomi.domain.entity.Verification;
import xiaomi.domain.entity.VerificationRepository;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;
	@Autowired
	VerificationRepository repository;
	
	@Override
	public void mailSend(String email) {
		System.out.println(email);
		SimpleMailMessage message=new SimpleMailMessage();
		
		String code=createKey(); //발급한 키
		
		message.setFrom("eeehos95@gmail.com");//메일 보내는 이메일 주소
		message.setTo(email);//메일 받을 이메일 주소
		
		message.setSubject("[쏘시월드] 회원가입을 위한 인증메일입니다.");
		//message.setText("회원가입을 위한 인증번호입니다. [인증번호: "+System.nanoTime()+"]");
		message.setText("회원가입을 위한 인증번호입니다. [인증번호: "+code+"]");
		
		mailSender.send(message);
		
		//entity객체 생성 후 email,code 를 save
		Verification entity=new Verification(email,code);
		repository.save(entity);
	}

	/* create random key*/
	private String createKey() {
		StringBuffer key= new StringBuffer();
		Random rand=new Random();
		for(int i=0; i<6; i++) { //6자
			
			int idx=rand.nextInt(3); //0,1,2
			
			switch(idx) { //알파벳 26자
			case 0://영소문자(a97 to z122)
				key.append((char)(rand.nextInt(26)+97));//0+97=a
				break;
			case 1://영대문자(A65 to Z90)
				key.append((char)(rand.nextInt(26)+65));//0+65=A
				break;
			case 2: //숫자 (0 to 9)
				key.append(rand.nextInt(10));
			}
		}
		
		return key.toString();
	}

	@Override
	public String mailCheck(MailRequestDto dto) {
		Optional<Verification> op=repository.findByEmailAndCode(dto.getEmail(),dto.getCode());
		if(op.isPresent()) {
			//Verification entity=op.get();
			return "인증되었습니다.";
		}else {
			return "인증번호와 일치하지 않습니다.";
		}
		
	}
	
}
