package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.CompagnonException;
import model.Compagnon;
import model.Personnage;
import repository.CompagnonRepository;

@Service
public class CompagnonService {

	@Autowired
	private CompagnonRepository compagnonRepo;
	@Autowired
	private PersonnageService personnageService;

	public void creationOuModification(Compagnon compagnon) {
		if (compagnon.getNom() == null) {
			throw new CompagnonException();
		}
		compagnonRepo.save(compagnon);
	}

	public void suppression(Long id) {
		suppression(getById(id));
	}

	public void suppression(Compagnon compagnon) {
		Compagnon compagnonEnBase = null;
		if (compagnon.getId() != null) {
			compagnonEnBase = compagnonRepo.findById(compagnon.getId()).orElseThrow(CompagnonException::new);
			compagnonRepo.delete(compagnonEnBase);
		} else {
			throw new CompagnonException();
		}
	}

	public void suppression(Personnage personnage) {
		if (personnage.getId() != null) {
			Personnage personnageEnBase = personnageService.getBy(personnage.getId());
			compagnonRepo.deleteByMaitre(personnageEnBase);
		} else {
			throw new CompagnonException();
		}
	}

	public Compagnon getById(Long id) {
		if (id != null) {
			return compagnonRepo.findById(id).orElseThrow(CompagnonException::new);
		}
		throw new CompagnonException();
	}

	public List<Compagnon> getAll() {
		return compagnonRepo.findAll();
	}

}
