/*
 * CDD
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Employe_en_CDD")
public class CDD extends Employe {

	//=========================
	// Attributes
	//=========================
	
	private Date dateSortie;
	private Double primeDePrecarite;
	
	//=========================
	// Constructor
	//=========================
	
	public CDD() {
		super();
	}

	public CDD(String nom, String prenom, Date dateNaissance, Adresse adresse) {
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

	public Double getPrimeDePrecarite() {
		return primeDePrecarite;
	}

	public void setPrimeDePrecarite(Double primeDePrecarite) {
		this.primeDePrecarite = primeDePrecarite;
	}
	
	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "CDD [dateSortie=" + dateSortie + ", primeDePrecarite="
				+ primeDePrecarite + ", dateEmbauche=" + dateEmbauche
				+ ", role=" + role + ", salaire=" + salaire + ", idPersonne="
				+ idPersonne + ", nom=" + nom + ", prenom=" + prenom
				+ ", dateNaissance=" + dateNaissance + ", adresse=" + adresse
				+ "]";
	}

}
