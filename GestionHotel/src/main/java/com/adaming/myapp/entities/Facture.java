package com.adaming.myapp.entities;

import java.util.Set;

import javax.persistence.*;

/*
* Facture
* Version: 1.0.1
* Date: 05-12-2016
* Author: Brice Touchard
*/	

@Entity
public class Facture {
	
	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private double CoutReservation;
	private double CoutConsommation;
   
	@ManyToOne 
	@JoinColumn(name="FACTURES_HO")
	private Hotel hotel;
    
	@OneToMany(mappedBy="facture", fetch = FetchType.EAGER)
    private Set<Reservation> reservations;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="CONSOS_FACT")
    private Set<Consommation> consommations;
    
    @OneToOne
    @JoinColumn(name="FACTURE_PAIEMENT")
    private Paiement paiement;
	
	//=========================
	// Constructor
	//=========================
	   
   public Facture() {
		super();
		CoutReservation = 0.0;
		CoutConsommation = 0.0;
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
	
	public double getCoutReservation() {
		return CoutReservation;
	}
	public void setCoutReservation(double coutReservation) {
		CoutReservation = coutReservation;
	}
	public double getCoutConsommation() {
		return CoutConsommation;
	}
	public void setCoutConsommation(double coutConsommation) {
		CoutConsommation = coutConsommation;
	}
    public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<Consommation> getConsommations() {
		return consommations;
	}

	public void setConsommations(Set<Consommation> consommations) {
		this.consommations = consommations;
	}

	public Paiement getPaiement() {
		return paiement;
	}

	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}
	
	//=========================
	// Getter / Setter
	//=========================
	
	@Override
	public String toString() {
		return "Facture [id=" + id + ", CoutReservation=" + CoutReservation
				+ ", CoutConsommation=" + CoutConsommation + "]";
	}

}
