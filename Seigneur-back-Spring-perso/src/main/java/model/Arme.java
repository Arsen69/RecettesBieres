package model;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("weapon")
public class Arme extends Equipement {

	@JsonView(JsonViews.Common.class)
	private double portee;
	@JsonView(JsonViews.Common.class)
	private int hand;
	
	public Arme() {
	}

	public Arme(String nom, LocalDateTime creation, Stats stats, double portee, int hand) {
		super(nom, creation, stats);
		this.portee = portee;
		this.hand = hand;
	}

	public double getPortee() {
		return portee;
	}

	public void setPortee(double portee) {
		this.portee = portee;
	}

	public int getHand() {
		return hand;
	}

	public void setHand(int hand) {
		this.hand = hand;
	}

	@Override
	public String toString() {
		return "Arme [portee=" + portee + ", hand=" + hand + ", id=" + id + ", nom=" + nom + ", creation=" + creation
				+ ", stats=" + stats + "]";
	}
	
	
}
