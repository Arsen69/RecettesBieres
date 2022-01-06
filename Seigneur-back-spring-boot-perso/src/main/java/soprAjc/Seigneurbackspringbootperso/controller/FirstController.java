package soprAjc.Seigneurbackspringbootperso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

	public FirstController() {
	}

	// @RequestMapping(value = "/hello",method = RequestMethod.GET)
	@RequestMapping("/hello")
	public String hello(@RequestParam(name = "prenom", required = false, defaultValue = "world") String prenom,
			Model model) {
		model.addAttribute("prenom", prenom);
		return "hello";
	}
	
	
}
