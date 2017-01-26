/*
 * Employe
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Employe")
public abstract class Employe extends Personne {

	//=========================
	// Attributes
	//=========================
	
	protected Date dateEmbauche;
	protected String role;
	protected Double salaire;
	
	//=========================
	// Constructor
	//=========================
	
	public Employe() {
		super();
	}

	public Employe(String nom, String prenom, Date dateNaissance,
			Adresse adresse) {
		super(nom, prenom, dateNaissance, adresse);
	}	

	
	//=========================
	// Getter / Setter
	//=========================

	public Date getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getSalaire() {
		return salaire;
	}

	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Employe [dateEmbauche=" + dateEmbauche + ", role=" + role
				+ ", salaire=" + salaire + ", idPersonne=" + idPersonne
				+ ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
				+ dateNaissance + ", adresse=" + adresse + "]";
	}

}
