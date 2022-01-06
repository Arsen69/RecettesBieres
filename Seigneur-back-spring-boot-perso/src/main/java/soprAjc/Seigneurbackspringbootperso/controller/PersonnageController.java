package soprAjc.Seigneurbackspringbootperso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import soprAjc.Seigneurbackspringbootperso.model.Arme;
import soprAjc.Seigneurbackspringbootperso.model.Armure;
import soprAjc.Seigneurbackspringbootperso.model.Monture;
import soprAjc.Seigneurbackspringbootperso.model.Personnage;
import soprAjc.Seigneurbackspringbootperso.model.Race;
import soprAjc.Seigneurbackspringbootperso.service.CompagnonService;
import soprAjc.Seigneurbackspringbootperso.service.EquipementService;
import soprAjc.Seigneurbackspringbootperso.service.PersonnageService;

@Controller
@RequestMapping("/personnage")
public class PersonnageController {

	@Autowired
	private PersonnageService personnageService;
	
	@Autowired
	private EquipementService equipementService;
	
	@Autowired
	private CompagnonService compagnonService;

	@GetMapping({ "", "/" })
	public String list(Model model) {
		model.addAttribute("personnages", personnageService.getAll());
		return "personnage/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		personnageService.suppression(id);
		return "redirect:/personnage";
	}

	private String goEdit(Model model, Personnage personnage) {
		model.addAttribute("personnage", personnage);
		model.addAttribute("races", Race.values());
		model.addAttribute("armes", equipementService.getAllArme());
		model.addAttribute("armures", equipementService.getAllArmure());
		model.addAttribute("montures", equipementService.getAllMonture());
		model.addAttribute("compagnons",compagnonService.getAll());
		return "personnage/edit";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, Model model) {
		return goEdit(model, personnageService.getById(id));
	}

	@GetMapping("/add")
	public String add(Model model) {
		return goEdit(model, new Personnage());
	}

	@PostMapping("")
	public String save(@Valid @ModelAttribute("personnage") Personnage personnage, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return goEdit(model, personnage);
		}
		if (personnage.getId() == null) {
			personnageService.creation(personnage);
		} else {
			personnageService.update(personnage);
		}
		return "redirect:/personnage";
	}
}
