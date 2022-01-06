package soprAjc.Seigneurbackspringbootperso;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import soprAjc.Seigneurbackspringbootperso.exception.PersonnageException;
import soprAjc.Seigneurbackspringbootperso.model.Personnage;
import soprAjc.Seigneurbackspringbootperso.model.Race;
import soprAjc.Seigneurbackspringbootperso.repository.PersonnageRepository;


@Rollback(true)
@Transactional
@SpringBootTest
class PersonnageRepoTest {

	@Autowired
	private PersonnageRepository personnageRepo;
	
	@Test
	public void testContextSpring() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		assertNotNull(ctx);
	}
	
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
