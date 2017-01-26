/*
 * Client
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Client")
public class Client extends Personne {

	//=========================
	// Attributes
	//=========================
	
	private int reduction;
	
	
	
	//=========================
	// Constructor
	//=========================

	public Client() {
		super();
		reduction = 0;
	}

	public Client(String nom, String prenom, Date dateNaissance, Adresse adresse) {
		super(nom, prenom, dateNaissance, adresse);
		reduction = 0;
	}

	//=========================
	// Getter / Setter
	//=========================

	public int getReduction() {
		return reduction;
	}

	public void setReduction(int reduction) {
		this.reduction = reduction;
	}




	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Client [reduction=" + reduction + ", idPersonne=" + idPersonne + ", nom=" + nom + ", prenom="
				+ prenom + ", dateNaissance=" + dateNaissance + ", adresse="
				+ adresse + "]";
	}

}
