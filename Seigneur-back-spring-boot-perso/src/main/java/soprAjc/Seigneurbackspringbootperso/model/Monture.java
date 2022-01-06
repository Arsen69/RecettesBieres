package soprAjc.Seigneurbackspringbootperso.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("mount")
public class Monture extends Equipement{

	@Enumerated(EnumType.STRING)
	private TypeMonture type;

	public Monture() {
	}
	
	public Monture(String nom, LocalDateTime creation, Stats stats, TypeMonture type) {
		super(nom, creation, stats);
		this.type = type;
	}

	public TypeMonture getType() {
		return type;
	}

	public void setType(TypeMonture type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Monture [type=" + type + ", id=" + id + ", nom=" + nom + ", creation=" + creation + ", stats=" + stats
				+ "]";
	}
	
	
	
}
