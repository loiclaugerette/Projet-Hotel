package com.adaming.myapp.entities;
/* Paiement
*  Version: 1.0.0
*  Date: 05-12-2016
*  Author: Brice Touchard
*/	
import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_Paiement", discriminatorType = DiscriminatorType.STRING)
public class Paiement {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected Double coutTotal;
	protected Date date;
	
    @OneToOne
    protected Facture facture;
    
	   
	//=========================
	// Constructor
	//=========================
    
	public Paiement() {
		super();
	}
	
	public Paiement(Date date) {
		super();
		this.date = date;
	}
	   
	//=========================
	// Getter / Setter
	//=========================
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Double getCoutTotal() {
		return coutTotal;
	}
	
	public void setCoutTotal(Double coutTotal) {
		this.coutTotal = coutTotal;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}   

	public Facture getFacture() {
		return facture;
	}
	
	public void setFacture(Facture facture) {
		this.facture = facture;
	}

}
