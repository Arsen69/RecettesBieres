package soprAjc.Seigneurbackspringbootperso.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Stats {

	@Column(nullable = true)
	private int attaque;
	@Column(nullable = true)
	private int defense;
	
	public Stats() {
	}

	public Stats(int attaque, int defense) {
		this.attaque = attaque;
		this.defense = defense;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	@Override
	public String toString() {
		return "Stats [attaque=" + attaque + ", defense=" + defense + "]";
	}
	
	
	
}
