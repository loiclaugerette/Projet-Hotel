package com.adaming.myapp.entities;
/* Cheque
*  Version: 1.0.0
*  Date: 05-12-2016
*  Author: Brice Touchard
*/	
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cheque")
public class PaiementCheque extends Paiement{

	
	//=========================
	// Attributes
	//=========================
	
	private Long numCheque;
	private String banqueCheque;

	
	//=========================
	// Constructor
	//=========================
	
	public PaiementCheque() {
		super();
	}
	
	public PaiementCheque(Date date) {
		super( date);
	}

	public PaiementCheque(Date date, Long numCheque, String banqueCheque) {
		super(date);
		this.numCheque = numCheque;
		this.banqueCheque = banqueCheque;
	}

	//=========================
	// Getter / Setter
	//=========================
	
	public Long getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(Long numCheque) {
		this.numCheque = numCheque;
	}
	public String getBanqueCheque() {
		return banqueCheque;
	}
	public void setBanqueCheque(String banqueCheque) {
		this.banqueCheque = banqueCheque;
	}
}
