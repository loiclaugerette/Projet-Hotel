/*
 * Adresse
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

	//=========================
	// Attributes
	//=========================
	
	private String rue;
	private int codePostal;
	private String ville;
	private String pays;
	
	//=========================
	// Constructor
	//=========================
	
	public Adresse() {
	}

	public Adresse(String rue, int codePostal, String ville, String pays) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}
	
	//=========================
	// Getter / Setter
	//=========================

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	//=========================
	// Methods
	//=========================
	
	@Override
	public String toString() {
		return "Adresse [rue=" + rue
				+ ", codePostal=" + codePostal + ", ville=" + ville + ", pays="
				+ pays + "]";
	}
	
}
