package soprAjc.Seigneurbackspringbootperso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soprAjc.Seigneurbackspringbootperso.exception.EquipementException;
import soprAjc.Seigneurbackspringbootperso.model.Equipement;
import soprAjc.Seigneurbackspringbootperso.repository.EquipementRepository;

@Service
public class EquipementService {

	@Autowired
	private EquipementRepository equipementRepo;

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

}
