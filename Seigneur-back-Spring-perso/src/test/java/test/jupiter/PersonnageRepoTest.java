package test.jupiter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import Exception.PersonnageException;
import config.AppConfig;
import model.Personnage;
import model.Race;
import repository.PersonnageRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
@Rollback(true)
@Transactional
class PersonnageRepoTest {

	@Autowired
	private PersonnageRepository personnageRepo;

	@Test
	void test() {
		assertNotNull(personnageRepo);
	}

	@Test
	public void testInsert() {
		Personnage p = new Personnage("perso test", 0, Race.Elfe, false);
		personnageRepo.save(p);
		assertNotNull(p.getId());
		assertTrue(personnageRepo.findById(p.getId()).isPresent());
	}

	@Test
	public void testPersonnageException() {
		assertThrows(PersonnageException.class,
				() -> personnageRepo.findById(115L).orElseThrow(PersonnageException::new));
	}

	@Test
	public void testDataPersonnage() {
		Personnage p = new Personnage("perso test", 0, Race.Elfe, false);
		personnageRepo.save(p);
		Personnage perso = personnageRepo.findById(p.getId()).get();
		// @formatter:off
		assertAll("Contrôle des données du personnage inséré", 
				() -> assertEquals("perso test", perso.getNom()),
				() -> assertEquals(0, perso.getPv()), 
				() -> assertEquals(Race.Elfe, perso.getRace()),
				() -> assertFalse(perso.isVivant()));
		// @formatter:on
	}
	
	@BeforeAll
	public static void setup() {
		System.out.println("beforeAll");
	}
	
	@BeforeEach
	public void init() {
		System.out.println("test");
	}
	
	@AfterAll
	public static void end() {
		System.out.println("afterAll");
	}
	
	@AfterEach
	public void endTest() {
		System.out.println("endTest");
	}

}
