package model;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("armor")
public class Armure extends Equipement {

	private String materiaux;
	
	public Armure() {
	}

	public Armure(String nom, LocalDateTime creation, Stats stats, String materiaux) {
		super(nom, creation, stats);
		this.materiaux = materiaux;
	}

	public String getMateriaux() {
		return materiaux;
	}

	public void setMateriaux(String materiaux) {
		this.materiaux = materiaux;
	}

	@Override
	public String toString() {
		return "Armure [materiaux=" + materiaux + ", id=" + id + ", nom=" + nom + ", creation=" + creation + ", stats="
				+ stats + "]";
	}
	
	
}
