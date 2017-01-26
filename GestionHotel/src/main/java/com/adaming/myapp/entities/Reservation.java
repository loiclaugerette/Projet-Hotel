/* Reservation
 *Version: 1.0.0
 *Date: 05-12-2016
 *Author: Guillaume Campo
 */

package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
	
	//=============
	//attributes
	//=============
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date dateArrivee;
	private Date dateSortie;
	private boolean valide=true;
	
	//=============
	//associations
	//=============
	@ManyToOne
	@JoinColumn(name="RESERVATIONS_HO")
	private Hotel hotel;
	
	@ManyToOne 
	@JoinColumn(name="RESERVATIONS_CHB")
	private Chambre chambre;
	
	@ManyToOne
	@JoinColumn(name="FACTURES_RESERVATION")
	private Facture facture;
	
	@ManyToOne
	@JoinColumn(name="PERSONNE_RESERVATION")
	private Personne personne;
	
	//=============
	//constructors
	//=============
	public Reservation() {
	}

	public Reservation(Date dateArrivee, Date dateSortie) {
		super();
		this.dateArrivee = dateArrivee;
		this.dateSortie = dateSortie;
	}
	
	//=============
	//getters and setters
	//=============
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}
	
	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}	
	
}
