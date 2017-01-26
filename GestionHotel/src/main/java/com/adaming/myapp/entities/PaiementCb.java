package com.adaming.myapp.entities;
/* PaiementCb
*  Version: 1.0.0
*  Date: 05-12-2016
*  Author: Brice Touchard
*/	
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Carte")
public class PaiementCb extends Paiement{

	//=========================
	// Attributes
	//=========================
	
	private Long numCarte;
	private String typeCarte;
	
	//=========================
	// Constructor
	//=========================
	
	public PaiementCb() {
		super();
	}

	public PaiementCb(Date date) {
		super(date);
	}

	public PaiementCb(Date date, Long numCarte, String typeCarte) {
		super(date);
		this.numCarte = numCarte;
		this.typeCarte = typeCarte;
	}
	
	//=========================
	// Getter / Setter
	//=========================
	
	public Long getNumCarte() {
		return numCarte;
	}
	public void setNumCarte(Long numCarte) {
		this.numCarte = numCarte;
	}
	public String getTypeCarte() {
		return typeCarte;
	}
	public void setTypeCarte(String typeCarte) {
		this.typeCarte = typeCarte;
	}	
}
