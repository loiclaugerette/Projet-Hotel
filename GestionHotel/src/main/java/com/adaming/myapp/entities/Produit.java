/*
 * Produit
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Produit {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduit;
	private String nom;
	private int quantite;
	private Double coutAchat;
	private Double coutVente;
	
	@OneToMany(mappedBy = "produit")
	private List<Consommation> consommations;
	
	//=========================
	// Constructor
	//=========================
	
	public Produit() {
	}

	public Produit(String nom, int quantite, Double coutAchat, Double coutVente) {
		this.nom = nom;
		this.quantite = quantite;
		this.coutAchat = coutAchat;
		this.coutVente = coutVente;
	}
	
	//=========================
	// Getter / Setter
	//=========================

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Double getCoutAchat() {
		return coutAchat;
	}

	public void setCoutAchat(Double coutAchat) {
		this.coutAchat = coutAchat;
	}

	public Double getCoutVente() {
		return coutVente;
	}

	public void setCoutVente(Double coutVente) {
		this.coutVente = coutVente;
	}

	public List<Consommation> getConsommations() {
		return consommations;
	}

	public void setConsommations(List<Consommation> consommations) {
		this.consommations = consommations;
	}
	
	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", nom=" + nom
				+ ", quantite=" + quantite + ", coutAchat=" + coutAchat
				+ ", coutVente=" + coutVente + "]";
	}

}
