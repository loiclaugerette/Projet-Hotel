/*
 * ConsommationBean
 * Version: 1.0.0
 * Date: 10-12-2016
 * Author: Brice Touchard
 */

package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.abstractfactory.IPersonneFactory;
import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Consommation;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.exceptions.NonValidClassTypeException;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IConsommationService;
import com.adaming.myapp.service.IFactureService;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IPersonneService;
import com.adaming.myapp.service.IProduitService;

@Component("consommationBean")
@ViewScoped
public class ConsommationBean {

	//=========================
	// Attributes
	//=========================
	
		private final Logger LOGGER = Logger.getLogger("EmployeBean");
	
	// Service
	@Inject
	private IConsommationService serviceConso;
	@Inject
	private IHotelService serviceHotel;
	@Inject
	private IPersonneService servicePersonne;
	@Inject
	private IProduitService serviceProduit;
	@Inject
	private IFactureService serviceFacture;
	
	
	
	private IPersonneFactory personneFactory;

	//Hotel Attributess
	private Hotel hotel;
	private List<Hotel> hotels;
	private Long idHotel;
	private String nom;
	private Adresse adresse;
	private int nombreEtoiles;	
	
	//client attributes
	private Client client;
	private List<Client> clients;
	private List<Client> clientsByHotel;
	private Long idPersonne;
	private Long idClient;
	private String nomClient;
	private String prenomClient;
	private int reduction;
	private Date dateNaissance;
	private Adresse adresseClient;
	private String rue;
	

	private int codePostal;
	private String ville;
	private String pays;
	
	//Produit Attributes
	private Produit produit;
	private String nomProduit;
	private int quantite;
	private Double coutAchat;
	private Double coutVente;
	private List<Produit> produits;
	private int quantityAdded;
	
	private Set<Facture> factures;
	private Long idFacture;
	
	
	
	@PostConstruct
	public void getAll() {
		setHotels(serviceHotel.getHotels());
		try {
			setProduits(serviceProduit.getAll());
		} catch (NullListException e) {
			// TODO Auto-generated catch block
			setProduits(null);
		}
	}
	
	public String redirect(){
		getAll();
		return "consommation?faces-redirect=true";
	}
	public void getClientsByHotel2() {
		clientsByHotel = new ArrayList<Client>();
		Set<Client> clientSet = serviceHotel.getClients(idHotel);
		for (Client cli:clientSet) {
			clientsByHotel.add(cli);
		}
		System.out.println("Voici les clients par hotel : "+clientsByHotel);
		/*
        if(hotel !=null && !hotel.equals("")){
        	setClients(serviceHotel.getClients(hotel.getId()));
        }else{
        	clients = new ArrayList<Client>();
        }
        return clients;*/
	}
	

	public void addClient() throws NonValidClassTypeException {
		System.out.println("blabla");
		Adresse a = new Adresse(rue,codePostal,ville,pays);
		Client c = (Client) personneFactory.createPersonne("Client", nomClient, prenomClient, new Date(), a);
		servicePersonne.create(c);
        initFieldsClient();
	}
	
	public void addProduitToBasket(Produit produit){
		setProduit(produit);
		
		nomProduit = "";
		quantite = 0;
		coutAchat = 0.0;
		coutVente = 0.0;
	}
	
	public void initFieldsHotel() {
		nom = "";
		adresse = null;
		nombreEtoiles = 0;
	}
	
	
	public void initFieldsClient() {
		
		nomClient = "";
		prenomClient = "";
		reduction = 0;
		dateNaissance = null;
		//adresseClient = null;
	}
	
	public void Consommer(){
			
			try {
				System.out.println(quantityAdded+"---"+idPersonne+"----"+produit);
				Consommation conso = new Consommation(quantityAdded);
				serviceConso.add(conso, idPersonne, produit.getIdProduit());
				serviceFacture.remplirConsommation(idFacture, conso.getId());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "La consommation a bien été effectuée."));
				redirect();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention !", "Il n'y pas assez de "+produit.getNom()+" en stock."));
			}
			
				
			
			
	}
	
	public void selectHotel(){
		setIdHotel(idHotel);
		getClientsByHotel2();
		Set<Produit> produitSet = serviceHotel.getProduits(idHotel);
		produits = new ArrayList<Produit>();
		for (Produit prod:produitSet) {
			produits.add(prod);
		}
		clients= clientsByHotel;
	}
	
	public void selectClient(){
		setIdPersonne(idClient);
		try {
			factures=servicePersonne.getFacturesByClient(idClient);
		} catch (NullListException e) {
			// TODO Auto-generated catch block
			factures=null;
		}
		System.out.println(factures);
	}
	
	public void selectFacture(){
		setIdFacture(idFacture);
	}
	
	

	//=========================
	// Getter / Setter
	//=========================
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public int getNombreEtoiles() {
		return nombreEtoiles;
	}

	public void setNombreEtoiles(int nombreEtoiles) {
		this.nombreEtoiles = nombreEtoiles;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public int getReduction() {
		return reduction;
	}

	public void setReduction(int reduction) {
		this.reduction = reduction;
	}



	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresseClient() {
		return adresseClient;
	}

	public void setAdresseClient(Adresse adresseClient) {
		this.adresseClient = adresseClient;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public void setClientsByHotel(List<Client> clientsByHotel) {
		this.clientsByHotel = clientsByHotel;
	}

	public List<Client> getClientsByHotel() {
		return clientsByHotel;
	}

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

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
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

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantityAdded() {
		return quantityAdded;
	}

	public void setQuantityAdded(int quantityAdded) {
		this.quantityAdded = quantityAdded;
	}

	public IConsommationService getServiceConso() {
		return serviceConso;
	}

	public void setServiceConso(IConsommationService serviceConso) {
		this.serviceConso = serviceConso;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public Set<Facture> getFactures() {
		return factures;
	}

	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}

	public Long getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(Long idFacture) {
		this.idFacture = idFacture;
	}

}