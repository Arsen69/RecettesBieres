package test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import model.Personnage;
import repository.PersonnageRepository;

public class TestPersonnageRepo {

	
	private static AnnotationConfigApplicationContext ctx;
	private PersonnageRepository personnageRepo;
	
	@BeforeClass
	public static void methodeExecutee1FoisAvantLesTest() {
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	}
	
	@AfterClass
	public static void methodeExecutee1FoisApresLesTest() {
		ctx.close();
	}
	
	@Before
	public void execution1FoisAvantChaqueTest() {
		personnageRepo = ctx.getBean(PersonnageRepository.class);
	}
	
	@After
	public void execution1FoisApresChaqueTest() {
	}
	
	@Test
	public void personnageRepositoryOk() {
		assertNotNull(personnageRepo);
	}
	
	@Test
	public void testFindByIdWithInventaireAndQuetes() {
		Personnage p = personnageRepo.findByIdWithInventaireAndQuetes(1L).get();
		assertNotNull(p.getInventaire());
		assertNotNull(p.getQuetes());
	}


}
