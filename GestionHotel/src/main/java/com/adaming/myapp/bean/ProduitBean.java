/*
 * ProduitBean
 * Version: 1.0.0
 * Date: 07-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.service.IProduitService;

@Component("produitBean")
@ViewScoped
public class ProduitBean {

	//=========================
	// Attributes
	//=========================
	
	@Inject
	private IProduitService serviceProduit;
	
	private Long idProduit;
	private String nom;
	private int quantite;
	private Double coutAchat;
	private Double coutVente;
	
	private Produit produit;
	private List<Produit> produits;
	private Long idHotel;
	
	//=========================
	// Constructor / Service Setter
	//=========================
	
	public void setServiceProduit(IProduitService serviceProduit) {
		this.serviceProduit = serviceProduit;
	}
	
	public ProduitBean() {
		init();
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

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

	//=========================
	// Methods
	//=========================

	private void init() {
		nom = "";
		quantite = 0;
		coutAchat = 0.0;
		coutVente = 0.0;
		idHotel = 0L;
	}
	
	@PostConstruct
	public void initList() {
		getAll();
	}
	
	public void redirect() {
		
	}
	
	public void add() {
		serviceProduit.add(new Produit(nom, quantite, coutAchat, coutVente), idHotel);
		init();
	}
	
	public void getOne() {
		produit = serviceProduit.getOne(idProduit);
	}
	
	public void getAll() {
		try {
			produits = serviceProduit.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			produits = new ArrayList<Produit>();
		}
	}
	
	public void update() {
		serviceProduit.update(produit);
		init();
	}

}
