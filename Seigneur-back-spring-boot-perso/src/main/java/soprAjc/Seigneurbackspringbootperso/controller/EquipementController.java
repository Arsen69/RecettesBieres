package soprAjc.Seigneurbackspringbootperso.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import soprAjc.Seigneurbackspringbootperso.model.Arme;
import soprAjc.Seigneurbackspringbootperso.model.Armure;
import soprAjc.Seigneurbackspringbootperso.model.Equipement;
import soprAjc.Seigneurbackspringbootperso.model.Monture;
import soprAjc.Seigneurbackspringbootperso.model.TypeMonture;
import soprAjc.Seigneurbackspringbootperso.service.EquipementService;

@Controller
@RequestMapping("/equipement")
public class EquipementController {

	@Autowired
	private EquipementService equipementService;

	@GetMapping({ "", "/" })
	public String list(Model model) {
		model.addAttribute("equipements", equipementService.getAll());
		return "equipement/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		equipementService.suppression(id);
		return "redirect:/equipement";
	}

	private String goEdit(Model model, Equipement equipement) {
		model.addAttribute("equipement", equipement);
		model.addAttribute("montures", TypeMonture.values());
		return "equipement/edit";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, Model model) {
		return goEdit(model, equipementService.getById(id));
	}

	@GetMapping("/add/Arme")
	public String addArme(Model model) {
		return goEdit(model, new Arme());
	}

	@GetMapping("/add/Armure")
	public String addArmure(Model model) {
		return goEdit(model, new Armure());
	}

	@GetMapping("/add/Monture")
	public String addMonture(Model model) {
		return goEdit(model, new Monture());
	}

	private String save(Equipement equipement, Model model) {
		if (equipement.getId() == null) {
			equipementService.creation(equipement);
		} else {
			equipementService.update(equipement);
		}
		return "redirect:/equipement";
	}

	@PostMapping("save/Arme")
	public String saveArme(@ModelAttribute Arme arme, Model model) {
		return save(arme, model);
	}

	@PostMapping("save/Armure")
	public String saveArmure(@ModelAttribute Armure armure, Model model) {
		return save(armure, model);
	}

	@PostMapping("save/Monture")
	public String saveMonture(@ModelAttribute Monture monture, Model model) {
		model.addAttribute("montures", TypeMonture.values());
		return save(monture, model);
	}

}
