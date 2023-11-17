package mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		log.info(" ---- [MVC-Basic] The GET method ... ");
		model.addAttribute("name", name);
		return "greeting";
	}

}
