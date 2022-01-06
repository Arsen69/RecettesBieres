package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Stats {

	@JsonView(JsonViews.Common.class)
	@Column(nullable = true)
	private int attaque;
	@JsonView(JsonViews.Common.class)
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
