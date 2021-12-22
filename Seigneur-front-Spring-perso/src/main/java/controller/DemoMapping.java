package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class DemoMapping {

	@RequestMapping({ "/hello", "/page1" })
	public String getMapping(Model model, HttpSession session) {
		model.addAttribute("prenom", "olivier");
		return "hello";
	}

	// @RequestMapping(value="/page2",method = RequestMethod.GET)
	@GetMapping("/page2")
	public String autrePage() {
		return "page1";
	}
}
