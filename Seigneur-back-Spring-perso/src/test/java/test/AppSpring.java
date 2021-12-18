package test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import model.Arme;
import model.Armure;
import model.Compagnon;
import model.Equipement;
import model.Personnage;
import model.Quete;
import model.Race;
import model.Stats;
import repository.CompagnonRepository;
import repository.EquipementRepository;
import repository.PersonnageRepository;
import repository.QueteRepository;

public class AppSpring {

	@Autowired
	CompagnonRepository compagnonRepo;
	@Autowired
	PersonnageRepository personnageRepo;
	@Autowired
	QueteRepository queteRepo;
	@Autowired
	EquipementRepository equipementRepo;

	public void run(String... args) {
		//initDataBase();
		for (int i=1; i<5; i++) {
		System.out.println(personnageRepo.findByIdWithInventaireAndQuetes((long) i).get());
		System.out.println(personnageRepo.findByIdWithInventaireAndQuetes((long) i).get().getInventaire());
		System.out.println(personnageRepo.findByIdWithInventaireAndQuetes((long) i).get().getQuetes());
		System.out.println("-----------------------------");
		}
	}

	public void initDataBase() {

		Quete quete1 = new Quete("Tuer des Loups");
		Quete quete2 = new Quete("Chercher le marteau du forgeron");
		Quete quete3 = new Quete("Sauver la comté");

		queteRepo.save(quete1);
		queteRepo.save(quete2);
		queteRepo.save(quete3);

		Compagnon comp1 = new Compagnon("Jordan");
		Compagnon comp2 = new Compagnon("Lydia");
		Compagnon comp3 = new Compagnon("Jaskier");
		Compagnon comp4 = new Compagnon("Ciri");
		Compagnon comp5 = new Compagnon("L'âne");
		compagnonRepo.saveAll(Arrays.asList(comp1, comp2, comp3, comp4, comp5));

		Equipement marteau = new Arme("Marteau", LocalDateTime.now(), new Stats(5, 1), 2, 2);
		Equipement epee = new Arme("Epée des rois", LocalDateTime.now(), new Stats(4, 3), 2, 2);
		Equipement arc = new Arme("Arc Long", LocalDateTime.now(), new Stats(6, 0), 10, 2);
		Equipement coteGondor = new Armure("Cote du Gondor", LocalDateTime.now(), new Stats(0, 6), "Métal");

		equipementRepo.saveAll(Arrays.asList(marteau, epee, coteGondor, arc));

		Personnage gimli = new Personnage("Gimli", 10, Race.Nain, true);
		Personnage legolas = new Personnage("Legolas", 4, Race.Elfe, true);
		Personnage aragorn = new Personnage("Aragorn", 7, Race.Humain, true);
		Personnage ned = new Personnage("Ned Stark", 0, Race.Humain, false);

		gimli.setArme((Arme) marteau);
		gimli.setQuetes(new HashSet<Quete> (Arrays.asList(quete1, quete2, quete3)));
		gimli.setInventaire(new HashSet<Equipement> (Arrays.asList((Arme) epee)));
		aragorn.setInventaire(Arrays.asList((Armure) coteGondor).stream().collect(Collectors.toSet()));
		aragorn.setArme((Arme) epee);
		aragorn.setArmure((Armure) coteGondor);
		aragorn.setQuetes(Arrays.asList(quete2, quete3).stream().collect(Collectors.toSet()));
		legolas.setArme((Arme)arc);
		legolas.setInventaire(Arrays.asList(arc, coteGondor).stream().collect(Collectors.toSet()));
		legolas.setQuetes(Arrays.asList(quete3).stream().collect(Collectors.toSet()));

		personnageRepo.saveAll(Arrays.asList(gimli, legolas, aragorn, ned));

	}

}
