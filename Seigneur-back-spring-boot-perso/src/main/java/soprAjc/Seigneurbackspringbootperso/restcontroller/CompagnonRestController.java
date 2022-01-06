package soprAjc.Seigneurbackspringbootperso.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
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

import soprAjc.Seigneurbackspringbootperso.exception.CompagnonException;
import soprAjc.Seigneurbackspringbootperso.model.Compagnon;
import soprAjc.Seigneurbackspringbootperso.model.JsonViews;
import soprAjc.Seigneurbackspringbootperso.service.CompagnonService;

@RestController
@RequestMapping("/api/compagnon")
public class CompagnonRestController {

	@Autowired
	private CompagnonService compagnonService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Compagnon> getAll() {
		return compagnonService.getAll();
	}

	@GetMapping("/maitre")
	@JsonView(JsonViews.CompagnonAvecMaitre.class)
	public List<Compagnon> getAllWithMaitre() {
		return compagnonService.getAll();
	}

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Compagnon getById(@PathVariable("id") Long id) {
		return compagnonService.getById(id);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Compagnon create(@Valid @RequestBody Compagnon compagnon, BindingResult br) {
		if (br.hasErrors()) {
			throw new CompagnonException();
		}
		compagnonService.creationOuModification(compagnon);
		return compagnon;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Compagnon replace(@Valid @RequestBody Compagnon compagnon, BindingResult br, @PathVariable("id") Long id) {
		compagnonService.creationOuModification(compagnon);
		return compagnonService.getById(id);
	}

	@JsonView(JsonViews.Common.class)
	@PatchMapping("/{id}")
	public Compagnon update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
		Compagnon compagnonEnBase = compagnonService.getById(id);
		fields.forEach((k,v)->{
			Field field = ReflectionUtils.findField(Compagnon.class, k);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, compagnonEnBase, v);
		});
		compagnonService.creationOuModification(compagnonEnBase);
		return compagnonEnBase;
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@JsonView(JsonViews.Common.class)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		compagnonService.suppression(id);
	}

}
