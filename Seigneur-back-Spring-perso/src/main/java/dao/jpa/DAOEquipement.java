package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.IDAOEquipement;
import model.Equipement;
import util.Context;

public class DAOEquipement implements IDAOEquipement{

	static EntityManagerFactory emf = Context.getInstance().getEmf();

	@Override
	public Equipement findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Equipement objet = em.find(Equipement.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Equipement> findAll() {


		EntityManager em = emf.createEntityManager();
	
		List<Equipement> objets = em.createQuery("from Equipement").getResultList();
		em.close();
		return objets;
		
	}

	@Override
	public Equipement save(Equipement objet) {
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
		Equipement objet = em.find(Equipement.class, id);

		em.getTransaction().begin();
		
		em.remove(objet);
		
		em.getTransaction().commit();
		em.close();

	}

}
