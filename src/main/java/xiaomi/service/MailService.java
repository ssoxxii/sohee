package xiaomi.service;

import xiaomi.domain.dto.MailRequestDto;

public interface MailService {

	void mailSend(String email);

	String mailCheck(MailRequestDto dto);

}
