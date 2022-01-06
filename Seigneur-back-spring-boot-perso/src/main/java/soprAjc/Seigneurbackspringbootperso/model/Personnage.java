package soprAjc.Seigneurbackspringbootperso.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;



@Entity // Obligatoire
@Table(name = "player")
public class Personnage {

	@Id // Obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Obligatoire*
	@JsonView(JsonViews.Common.class)
	private Long id;

	@Column(name = "name", nullable = false, columnDefinition = "VARCHAR(25)")
	@NotEmpty
	@JsonView(JsonViews.Common.class)
	private String nom;

	@Column(name = "hp", nullable = false)
	@Min(0)
	@JsonView(JsonViews.Common.class)
	private int pv;

	@JsonView(JsonViews.Common.class)
	@Enumerated(EnumType.STRING)
	private Race race;

	@Column(name = "alive", nullable = false)
	@JsonView(JsonViews.Common.class)
	private boolean vivant;

	@JsonView(JsonViews.Personnage.class)
	@ManyToOne(cascade = CascadeType.MERGE) // Obligatoire
	@JoinColumn(name = "monture_equipee") // Seulement si l'on veut rename le col de jointure
	private Monture monture;

	@JsonView(JsonViews.Personnage.class)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "arme_equipee")
	private Arme arme;

	@JsonView(JsonViews.Personnage.class)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "armure_equipee")
	private Armure armure;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "inventaire", // Rename la table
			joinColumns = @JoinColumn(name = "id_du_personnage"), // rename la cl� principale (Personnage car on est
																	// dans la classe Personnage)
			inverseJoinColumns = @JoinColumn(name = "id_equipement") // rename l'autre cl�, celle de l'attribut donc
																		// Equipement ici

	)
	@JsonView(JsonViews.PersonnageAvecInventaire.class)
	private Set<Equipement> inventaire;

	@JsonView(JsonViews.Personnage.class)
	@ManyToOne(cascade = CascadeType.MERGE)
	private Equipe team;

	@OneToOne(cascade = CascadeType.MERGE)
	@JsonView(JsonViews.PersonnageAvecFamilier.class)
	private Compagnon familier;

	@JsonView(JsonViews.PersonnageAvecQuetes.class)
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "challenge_accepted")
	private Set<Quete> quetes;

	@JsonView(JsonViews.Common.class)
	@Version
	private int version;

	// Obligatoire
	public Personnage() {
	}

	public Personnage(String nom, int pv, Race race, boolean vivant) {
		this.nom = nom;
		this.pv = pv;
		this.race = race;
		this.vivant = vivant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public boolean isVivant() {
		return vivant;
	}

	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Monture getMonture() {
		return monture;
	}

	public void setMonture(Monture monture) {
		this.monture = monture;
	}

	public Arme getArme() {
		return arme;
	}

	public void setArme(Arme arme) {
		this.arme = arme;
	}

	public Armure getArmure() {
		return armure;
	}

	public void setArmure(Armure armure) {
		this.armure = armure;
	}

	public Set<Equipement> getInventaire() {
		return inventaire;
	}

	public void setInventaire(Set<Equipement> inventaire) {
		this.inventaire = inventaire;
	}

	public Equipe getTeam() {
		return team;
	}

	public void setTeam(Equipe team) {
		this.team = team;
	}

	public Compagnon getFamilier() {
		return familier;
	}

	public void setFamilier(Compagnon familier) {
		this.familier = familier;
	}

	public Set<Quete> getQuetes() {
		return quetes;
	}

	public void setQuetes(Set<Quete> quetes) {
		this.quetes = quetes;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Personnage [id=" + id + ", nom=" + nom + ", pv=" + pv + ", race=" + race + ", vivant=" + vivant
				+ ", monture=" + monture + ", arme=" + arme + ", armure=" + armure + ", team=" + team + ", familier="
				+ familier + "]";
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
		Personnage other = (Personnage) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
