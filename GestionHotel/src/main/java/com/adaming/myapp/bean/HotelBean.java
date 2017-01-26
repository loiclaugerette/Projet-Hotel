package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import com.adaming.myapp.abstractfactory.ChambreFactoryImpl;
import com.adaming.myapp.abstractfactory.IChambreFactory;
import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.ChambreDouble;
import com.adaming.myapp.entities.ChambreSimple;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Suite;
import com.adaming.myapp.exceptions.NonValidClassTypeException;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IChambreService;
import com.adaming.myapp.service.IHotelService;

@Component("hotelBean")
@ViewScoped
public class HotelBean {
	
	private Logger LOGGER = Logger.getLogger("HotelBean");

	@Inject
	private IHotelService service;
	
	@Inject
	private IChambreService serviceChambre;
	
	private IChambreFactory chambreFactory = new ChambreFactoryImpl();
	
	//	Hotel attributes
	private Long idHotel;
	private Hotel hotel;
	private String nom;
	private Adresse adresse;
	private int nombreEtoiles;	
	private Hotel selectedHotel;
	private Double benefice;
	
	//chambre attributes
	protected Long idChambre;
	protected int numeroChambre;
	protected String description;
	protected Double prix;
	protected String type;
	
	
	//	Adresse attributes
	private String rue;
	private int codePostal;
	private String ville;
	private String pays;
	
	//Listes
	private List<Hotel> hotels;
	private List<Hotel> selectedHotels;
	private List<Chambre> selectedChambres;
	private Set<Chambre> chambres;			//Toutes les chambres
	private Set<Chambre> chambresTBA; 		//To Be Added, les chambres qu'on veut ajouter à l'hotel qu'on créer.
	private Set<Chambre> chambresByHotel;  //Stock la liste des chambres pour un hotel donné
	private List<Facture> facturesByHotel;  //Stock la liste des facture pour un hotel donné
	
	@PostConstruct
	public void getAllChambre() throws NullListException{
		//setChambres(serviceChambre.getAll());
		setChambres(new HashSet<Chambre>());
		setChambresTBA(new HashSet<Chambre>());
		setChambresByHotel(new HashSet<Chambre>());
	}
	
	@PostConstruct
	public void getAll(){
		setHotels(service.getHotels());
	}
	
	public void initFieldsHotel() {
		nom = "";
		adresse = null;
		nombreEtoiles = 0;
	}
	
	
	public void initFieldsChambre() {
		numeroChambre = 0;
		description = "";
		prix = 0.0;
	}
	
	public String forward(){
		return "hotel";
	}
	
	public String redirect(){
		getAll();
		chambresByHotel=null;
		chambresTBA=null;
		return "hotel?faces-redirect=true";
	}
	
	public void emptyChambres(){
		setChambresTBA(new HashSet<Chambre>());
	}
	
	public Set<Chambre> getChambreByHotel(){
		for (Hotel h:selectedHotels){
			setChambresByHotel(h.getChambres());
		}
		return chambresByHotel;
	}
	
	public List<Facture> getFactureByHotel(){
		for (Hotel h:selectedHotels){
			setFacturesByHotel(h.getFactures());
		}
		return facturesByHotel;
	}
	
	public void benef(){
		for (Hotel h:selectedHotels){
			benefice = service.beneficeAnnee(h);
			setFacturesByHotel(h.getFactures());
		}
		/*
		service.addFacture(h.get, idFacture)
		hotel.setFactures(facturesByHotel);
		benefice = service.beneficeAnnee(hotel);*/
	}
	
	public void update(){
		for (Hotel h:selectedHotels){
			service.update(h);
		}
		initFieldsHotel();
	}
	
	public void updateChambres(){
		for (Chambre c:selectedChambres){
			serviceChambre.update(c);
		}
		initFieldsChambre();
	}
	
	
	public void addHotel() {
		Adresse a = new Adresse(rue,codePostal,ville,pays);
		setAdresse(a);
		Hotel h = new Hotel(nom, adresse, nombreEtoiles);
		service.save(h, chambresTBA);
        initFieldsHotel();
        setChambresTBA(new HashSet<Chambre>());
        getAll();
	}
	
	public void addChambreSimple() throws NonValidClassTypeException{
		//System.out.println(numeroChambre);
		//System.out.println(description);
		ChambreSimple chSimple = (ChambreSimple)chambreFactory.createChambre("ChambreSimple", numeroChambre, description);
		serviceChambre.add(chSimple);
		initFieldsChambre();
		/*
		ChambreDouble chDouble = (ChambreDouble)chambreFactory.createChambre("ChambreDouble",numeroChambre, description);
		Suite suite = (Suite)chambreFactory.createChambre("Suite", numeroChambre, description);
		serviceChambre.add(chDouble);
		serviceChambre.add(suite);*/
	}
	
	public void addChambre() throws NonValidClassTypeException, NullListException{
		chambresTBA= new HashSet<Chambre>();
		Chambre ch = chambreFactory.createChambre(type, numeroChambre, description);
		serviceChambre.add(ch);
		chambresTBA.add(ch);
		initFieldsChambre();
	}
	
	public void addChambreToHotel() throws NonValidClassTypeException{
		Chambre ch = chambreFactory.createChambre(type, numeroChambre, description);
		serviceChambre.add(ch);
		
		for (Hotel h:selectedHotels){
			h.getChambres().add(ch);
			service.update(h);
		}
		initFieldsChambre();
	}
	
	
	//----GETTERS & SETTERS -------------------------------
	
	
	
	public IHotelService getService() {
		return service;
	}


	public List<Facture> getFacturesByHotel() {
		return facturesByHotel;
	}

	public void setFacturesByHotel(List<Facture> facturesByHotel) {
		this.facturesByHotel = facturesByHotel;
	}

	public Double getBenefice() {
		return benefice;
	}

	public void setBenefice(Double benefice) {
		this.benefice = benefice;
	}

	public List<Chambre> getSelectedChambres() {
		return selectedChambres;
	}

	public void setSelectedChambres(List<Chambre> selectedChambres) {
		this.selectedChambres = selectedChambres;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Hotel getSelectedHotel() {
		return selectedHotel;
	}

	public void setSelectedHotel(Hotel selectedHotel) {
		this.selectedHotel = selectedHotel;
	}

	public List<Hotel> getSelectedHotels() {
		return selectedHotels;
	}

	public void setSelectedHotels(List<Hotel> selectedHotels) {
		this.selectedHotels = selectedHotels;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}


	public Set<Chambre> getChambres() {
		return chambres;
	}

	public void setChambres(Set<Chambre> chambres) {
		this.chambres = chambres;
	}

	public Set<Chambre> getChambresTBA() {
		return chambresTBA;
	}

	public void setChambresTBA(Set<Chambre> chambresTBA) {
		this.chambresTBA = chambresTBA;
	}

	public Set<Chambre> getChambresByHotel() {
		return chambresByHotel;
	}

	public void setChambresByHotel(Set<Chambre> chambresByHotel) {
		this.chambresByHotel = chambresByHotel;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Long getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(Long idChambre) {
		this.idChambre = idChambre;
	}

	public int getNumeroChambre() {
		return numeroChambre;
	}

	public void setNumeroChambre(int numeroChambre) {
		this.numeroChambre = numeroChambre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public void setService(IHotelService service) {
		this.service = service;
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

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}



	
	
	
	
}
