/*
 * CDI
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Employe_en_CDI")
public class CDI extends Employe {

	//=========================
	// Attributes
	//=========================
	
	//=========================
	// Constructor
	//=========================
	
	public CDI() {
		super();
	}

	public CDI(String nom, String prenom, Date dateNaissance, Adresse adresse) {
		super(nom, prenom, dateNaissance, adresse);
	}

	//=========================
	// Getter / Setter
	//=========================

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "CDI [dateEmbauche=" + dateEmbauche + ", role=" + role
				+ ", salaire=" + salaire + ", idPersonne=" + idPersonne
				+ ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
				+ dateNaissance + ", adresse=" + adresse + "]";
	}

}
