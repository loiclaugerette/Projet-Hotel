package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IProduitService;

@Component("stockBean")
@ViewScoped
public class StockBean {
	
	private Long id;
	private String nom;
	private int quantite;
	private int quantiteAchetee;
	private double coutAchat;
	private double coutVente;
	private Long idProduit;
	
	
	

	

	@Inject 
	private IHotelService hotelService;
	
	@Inject 
	private IProduitService produitService;
	
	


	private Set<Produit> produits = new HashSet<Produit>();
	private Produit produit;
	
	private List<Hotel> hotels = new ArrayList<Hotel>();
	
	@PostConstruct
	public void getAll(){
		setHotels(hotelService.getHotels());
	}

	public void initFields(){
		setNom(null);
		setQuantite(0);
		setCoutAchat(0.0);
		setCoutVente(0.0);
	}
	public void saveProduit() {
		produitService.add(new Produit(nom, quantite, coutAchat, coutVente), id);
		setProduits(hotelService.getProduits(id));
		initFields();
	}
	
	public void showProduits(){
		setProduits(hotelService.getProduits(id));
	}
	
	public void updateQuantity() {
		System.out.println("update id produit"+ idProduit);
		Produit produit = produitService.getOne(idProduit);
		produit.setQuantite(produit.getQuantite()+quantiteAchetee);
		produitService.update(produit);
		setProduits(hotelService.getProduits(id));
		setQuantiteAchetee(0);
	}
	
	public String redirect(){
		getAll();
		return "stock";
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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


	public double getCoutAchat() {
		return coutAchat;
	}


	public void setCoutAchat(double coutAchat) {
		this.coutAchat = coutAchat;
	}


	public double getCoutVente() {
		return coutVente;
	}


	public void setCoutVente(double coutVente) {
		this.coutVente = coutVente;
	}


	public Set<Produit> getProduits() {
		return produits;
	}


	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}


	public IProduitService getProduitService() {
		return produitService;
	}


	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}


	public List<Hotel> getHotels() {
		return hotels;
	}


	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public int getQuantiteAchetee() {
		return quantiteAchetee;
	}

	public void setQuantiteAchetee(int quantiteAchetee) {
		this.quantiteAchetee = quantiteAchetee;
	}
	
}

