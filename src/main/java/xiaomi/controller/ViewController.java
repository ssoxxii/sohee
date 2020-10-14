package xiaomi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	/*home*/
	@GetMapping("/")
	public String index() {
		return "/index";
	}
	
}
