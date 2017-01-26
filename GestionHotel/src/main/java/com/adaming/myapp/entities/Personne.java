/*
 * Personne
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_PERSONNE", discriminatorType = DiscriminatorType.STRING)
public abstract class Personne {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idPersonne;
	protected String nom;
	protected String prenom;
	protected Date dateNaissance;
	
	@Embedded
	protected Adresse adresse;
	
	//=========================
	// Associations
	//=========================
	
	@OneToMany(mappedBy="personne")
	protected List<Reservation> reservations;
	
	@OneToMany(mappedBy = "personne")
	protected List<Consommation> consommation;
	
	//=========================
	// Constructor
	//=========================
	
	public Personne() {
	}

	public Personne(String nom, String prenom, Date dateNaissance, Adresse adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
	}
	
	//=========================
	// Getter / Setter
	//=========================

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Consommation> getConsommation() {
		return consommation;
	}

	public void setConsommation(List<Consommation> consommation) {
		this.consommation = consommation;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", nom=" + nom
				+ ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ "]";
	}

}
