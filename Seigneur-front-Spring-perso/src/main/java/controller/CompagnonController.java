package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Compagnon;
import service.CompagnonService;

@Controller
@RequestMapping("/compagnon")
public class CompagnonController {

	@Autowired
	private CompagnonService compagnonService;

	@GetMapping({ "", "/" })
	public String list(Model model) {
		model.addAttribute("compagnons", compagnonService.getAll());
		return "compagnon/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		compagnonService.suppression(id);
		return "redirect:/compagnon";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, Model model) {
		return goEdit(model, compagnonService.getById(id));
	}

	@GetMapping("/add")
	public String add(Model model) {
		return goEdit(model, new Compagnon());
	}

	private String goEdit(Model model, Compagnon compagnon) {
		model.addAttribute("compagnon", compagnon);
		return "compagnon/edit";
	}

	@PostMapping("")
//	public String save(@RequestParam("id") Long id, @RequestParam("nom") String nom) {
//		Compagnon compagnon = new Compagnon(nom);
//		if (id != null) {
//			compagnon.setId(id);
//		}
//		compagnonService.creationOuModification(compagnon);
//		return "redirect:/compagnon";
//	}

	public String save(@ModelAttribute Compagnon compagnon) {
		compagnonService.creationOuModification(compagnon);
		return "redirect:/compagnon";
	}
}
