/*
 * Stagiaire
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Stagiaire")
public class Stagiaire extends Employe {

	//=========================
	// Attributes
	//=========================
	
	private Date dateSortie;

	//=========================
	// Constructor
	//=========================
	
	public Stagiaire() {
		super();
	}

	public Stagiaire(String nom, String prenom, Date dateNaissance,
			Adresse adresse) {
		super(nom, prenom, dateNaissance, adresse);
	}

	//=========================
	// Getter / Setter
	//=========================

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Stagiaire [dateSortie=" + dateSortie + ", dateEmbauche="
				+ dateEmbauche + ", role=" + role + ", salaire=" + salaire
				+ ", idPersonne=" + idPersonne + ", nom=" + nom + ", prenom="
				+ prenom + ", dateNaissance=" + dateNaissance + ", adresse="
				+ adresse + "]";
	}
	
}
