package soprAjc.Seigneurbackspringbootperso.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import soprAjc.Seigneurbackspringbootperso.exception.PersonnageException;
import soprAjc.Seigneurbackspringbootperso.model.JsonViews;
import soprAjc.Seigneurbackspringbootperso.model.Personnage;
import soprAjc.Seigneurbackspringbootperso.model.Race;
import soprAjc.Seigneurbackspringbootperso.service.CompagnonService;
import soprAjc.Seigneurbackspringbootperso.service.EquipementService;
import soprAjc.Seigneurbackspringbootperso.service.PersonnageService;

@RestController
@RequestMapping("api/personnage")
@CrossOrigin(origins="*")
public class PersonnageRestController {

	@Autowired
	private PersonnageService personnageService;
	@Autowired
	private CompagnonService compagnonService;
	@Autowired 
	private EquipementService equipementService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Personnage> getAll() {
		return personnageService.getAll();
	}

	@JsonView(JsonViews.Personnage.class)
	@GetMapping("/{id}")
	public Personnage getById(@PathVariable("id") Long id) {
		return personnageService.getById(id);
	}
	
	@JsonView(JsonViews.PersonnageAvecInventaire.class)
	@GetMapping("/{id}/inventaire")
	public Personnage getByIdWithInventaire(@PathVariable("id") Long id) {
		return personnageService.getByIdWithInventaire(id);
	}
	
	@JsonView(JsonViews.PersonnageAvecQuetes.class)
	@GetMapping("/{id}/quetes")
	public Personnage getByIdWithQuetes(@PathVariable("id") Long id) {
		return personnageService.getByIdWithQuetes(id);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Personnage create(@Valid @RequestBody Personnage personnage, BindingResult br) {
		if (br.hasErrors()) {
			throw new PersonnageException();
		}
		personnageService.creation(personnage);
		return personnage;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Personnage replace(@Valid @RequestBody Personnage personnage, BindingResult br,
			@PathVariable Long id) {
		if (personnage.getId()==null) {
			personnage.setId(id);
		}
		personnageService.update(personnage);
		return personnageService.getById(id);
	}

	//Ne permet que la modification de nom,race,pv et vivant
	@JsonView(JsonViews.Common.class)
	@PatchMapping("/{id}")
	public Personnage update(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		Personnage personnageEnBase = personnageService.getById(id);
		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Personnage.class, k);
			ReflectionUtils.makeAccessible(field);
			if(k.equals("race")) {
				personnageEnBase.setRace(Race.valueOf((String)v));
				
			}else {
				ReflectionUtils.setField(field, personnageEnBase, v);
			}
		});
		personnageService.update(personnageEnBase);
		return personnageEnBase;
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@JsonView(JsonViews.Common.class)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		personnageService.suppression(id);
	}

}
