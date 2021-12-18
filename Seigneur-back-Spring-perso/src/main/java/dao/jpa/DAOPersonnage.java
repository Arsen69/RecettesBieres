package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.IDAOPersonnage;
import model.Personnage;
import util.Context;

public class DAOPersonnage implements IDAOPersonnage{

	static EntityManagerFactory emf = Context.getInstance().getEmf();

	@Override
	public Personnage findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Personnage objet = em.find(Personnage.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Personnage> findAll() {


		EntityManager em = emf.createEntityManager();
	
		List<Personnage> objets = em.createQuery("from Personnage").getResultList();
		em.close();
		return objets;
		
	}

	@Override
	public Personnage save(Personnage objet) {
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
		Personnage objet = em.find(Personnage.class, id);

		em.getTransaction().begin();
		
		em.remove(objet);
		
		em.getTransaction().commit();
		em.close();

	}

}
