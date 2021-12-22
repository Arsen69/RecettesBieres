package test;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.AppConfig;
import exception.PersonnageException;
import model.Personnage;
import model.Race;
import repository.PersonnageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TestPersonnageRepoAvecSpringTest {

	@Autowired
	PersonnageRepository personnageRepo;

	@Test
	@Transactional
	@Rollback(true)
	public void testInsert() {
		Personnage p = new Personnage("perso test", 0, Race.Elfe, false);
		personnageRepo.save(p);
		assertNotNull(p.getId());
		assertTrue(personnageRepo.findById(p.getId()).isPresent());
	}
	
	@Test(expected = PersonnageException.class)
	public void testPersonnageException() {
		personnageRepo.findById(115L).orElseThrow(PersonnageException::new);
	}

}
