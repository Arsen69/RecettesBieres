package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.IDAOEquipe;
import model.Equipe;
import util.Context;

public class DAOEquipe implements IDAOEquipe{

	static EntityManagerFactory emf = Context.getInstance().getEmf();

	@Override
	public Equipe findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Equipe objet = em.find(Equipe.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Equipe> findAll() {


		EntityManager em = emf.createEntityManager();
	
		List<Equipe> objets = em.createQuery("from Equipe").getResultList();
		em.close();
		return objets;
		
	}

	@Override
	public Equipe save(Equipe objet) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		objet=em.merge(objet);

		em.getTransaction().commit();
		em.close();

		return objet;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		Equipe objet = em.find(Equipe.class, id);

		em.getTransaction().begin();
		
		em.remove(objet);
		
		em.getTransaction().commit();
		em.close();

	}

}
