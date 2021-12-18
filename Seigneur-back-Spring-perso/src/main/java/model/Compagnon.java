package model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Compagnon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	
	
	@OneToOne(mappedBy = "familier")
	private Personnage maitre;
	
	
	public Compagnon() {
	}


	public Compagnon(String nom) {
		this.nom = nom;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Personnage getMaitre() {
		return maitre;
	}


	public void setMaitre(Personnage maitre) {
		this.maitre = maitre;
	}


	@Override
	public String toString() {
		return "Compagnon [id=" + id + ", nom=" + nom + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compagnon other = (Compagnon) obj;
		return Objects.equals(id, other.id);
	}



	
	
	
	
}
