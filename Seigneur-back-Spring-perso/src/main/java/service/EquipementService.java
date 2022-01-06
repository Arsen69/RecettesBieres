package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.EquipementException;
import model.Arme;
import model.Armure;
import model.Equipement;
import model.Monture;
import repository.ArmeRepository;
import repository.ArmureRepository;
import repository.EquipementRepository;
import repository.MontureRepository;

@Service
public class EquipementService {

	@Autowired
	private EquipementRepository equipementRepo;
	
	@Autowired
	private ArmeRepository armeRepo;
	
	@Autowired
	private ArmureRepository armureRepo;
	
	@Autowired
	private MontureRepository montureRepo;

	public void creation(Equipement equipement) {
		if (equipement.getNom() == null) {
			throw new EquipementException();
		}
		equipement.setCreation(Check.checkDateTimeNow(equipement.getCreation()));
		equipementRepo.save(equipement);
	}
	
	public Equipement update(Equipement equipement) {
		Check.checkLong(equipement.getId());
		Equipement equipementEnBase = equipementRepo.findById(equipement.getId()).orElseThrow(EquipementException::new);
		equipement.setCreation(equipementEnBase.getCreation());
		return equipementRepo.save(equipement);
	}
	
	public Equipement getById(Long id) {
		Check.checkLong(id);
		return equipementRepo.findById(id).orElseThrow(EquipementException::new);

	}

	public void suppression(Equipement equipement) {
		Equipement equipementEnBase = null;
		Check.checkLong(equipement.getId());
		equipementEnBase = equipementRepo.findById(equipement.getId()).orElseThrow(EquipementException::new);
		equipementRepo.delete(equipementEnBase);
	}

	public void suppression(Long id) {
		suppression(getById(id));
	}
	
	public List<Equipement> getAll() {
		return equipementRepo.findAll();
	}
	
	public List<Arme> getAllArme() {
		return armeRepo.findAll();
	}
	public List<Armure> getAllArmure() {
		return armureRepo.findAll();
	}
	public List<Monture> getAllMonture() {
		return montureRepo.findAll();
	}

}
