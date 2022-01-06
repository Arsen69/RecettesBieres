package service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.EquipementException;
import exception.PersonnageException;
import model.Arme;
import model.Armure;
import model.Equipement;
import model.Monture;
import model.Personnage;
import repository.CompagnonRepository;
import repository.PersonnageRepository;

//traitment
//service=> quelque chose qui fournit des fonctionnalites
@Service
public class PersonnageService {

	@Autowired
	private PersonnageRepository personnageRepo;
	@Autowired
	private CompagnonRepository compagnonRepo;
	@Autowired
	private CompagnonService compagnonService;
	@Autowired
	private EquipementService equipementService;

	private void updateRelation(Personnage personnage) {
		if (personnage.getArme() != null && personnage.getArme().getId() != null) {
			personnage.setArme((Arme) equipementService.getById(personnage.getArme().getId()));
		} else {
			personnage.setArme(null);
		}
		if (personnage.getArmure() != null && personnage.getArmure().getId() != null) {
			personnage.setArmure((Armure) equipementService.getById(personnage.getArmure().getId()));
		} else {
			personnage.setArmure(null);

		}
		if (personnage.getMonture() != null && personnage.getMonture().getId() != null) {
			personnage.setMonture((Monture) equipementService.getById(personnage.getMonture().getId()));
		} else {
			personnage.setArmure(null);
		}
		if (personnage.getFamilier() != null && personnage.getFamilier().getId() != null) {
			personnage.setFamilier(compagnonService.getById(personnage.getFamilier().getId()));
		}else {
			personnage.setFamilier(null);
		}
	}

	public void creation(Personnage personnage) {
		if (personnage.getNom() == null) {
			throw new PersonnageException();
		}
		updateRelation(personnage);
		personnageRepo.save(personnage);
	}

	public Personnage update(Personnage personnage) {
		Check.checkLong(personnage.getId());
		updateRelation(personnage);
		Personnage personnageEnBase = getById(personnage.getId());
		personnage.setVersion(personnageEnBase.getVersion());
		return personnageRepo.save(personnage);
	}

	public void suppression(Personnage personnage) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Personnage personnageEnBase = personnageRepo.findById(personnage.getId()).orElseThrow(PersonnageException::new);
		compagnonRepo.deleteByMaitre(personnageEnBase);
		personnageRepo.delete(personnageEnBase);
	}

	public void suppression(Long id) {
		suppression(getById(id));
	}

	public Personnage getById(Long id) {
		Check.checkLong(id);
		return personnageRepo.findById(id).orElseThrow(PersonnageException::new);
	}

	public Personnage getByIdWithQuetes(Long id) {
		Check.checkLong(id);
		return personnageRepo.findByIdWithQuetes(id).orElseThrow(PersonnageException::new);
	}

	public Personnage getByIdWithInventaire(Long id) {
		Check.checkLong(id);
		return personnageRepo.findByIdWithInventaire(id).orElseThrow(PersonnageException::new);
	}

	public Personnage getByIdWithInventaireAndQuetes(Long id) {
		Check.checkLong(id);
		return personnageRepo.findByIdWithInventaireAndQuetes(id).orElseThrow(PersonnageException::new);
	}

	public List<Personnage> getAll() {
		return personnageRepo.findAll();
	}
}
