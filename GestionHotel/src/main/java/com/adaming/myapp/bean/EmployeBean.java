/*
 * EmployeBean
 * Version: 1.0.0
 * Date: 078-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.abstractfactory.IPersonneFactory;
import com.adaming.myapp.abstractfactory.PersonneFactoryImpl;
import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.CDD;
import com.adaming.myapp.entities.CDI;
import com.adaming.myapp.entities.Employe;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Stagiaire;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IPersonneService;

@Component("employeBean")
@ViewScoped
public class EmployeBean {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("EmployeBean");
	
	// Service
	
	@Inject
	private IPersonneService servicePersonne;
	
	@Inject
	private IHotelService serviceHotel;
	
	// Personne Factory
	private IPersonneFactory personneFactory;
	
	// Personne Attributes
	private String nom;
	private String prenom;
	private Date dateNaissance;
	
	// Employe Attributes
	private Date dateEmbauche;
	private String role;
	private Double salaire;
	
	// CDI / CDD / Stagiaire Attributes
	private Date dateSortie;
	private Double primeDePrecarite;
	
	// Adresse Attributes
	private String rue;
	private int codePostal;
	private String ville;
	private String pays;
	
	// Employe List
	private Set<Employe> employes;
	private Personne employe;
	
	// Hotel List
	private List<Hotel> hotels;
	private Long idHotel;
	
	//=========================
	// Constructor / Service Setter
	//=========================
	
	public EmployeBean() {
		personneFactory = new PersonneFactoryImpl();
		initFields();
		idHotel = 0L;
		LOGGER.info("<=============== EmployeBean Initialization ===============>");
	}
	
	//=========================
	// Methods
	//=========================
	
	public String redirect() {
		idHotel = 0L;
		initFields();
		initHotelList();
		employes = new HashSet<Employe>();
		return "employe?faces-redirect=true";
	}
	
	@PostConstruct
	public void initHotelList() {
		hotels = serviceHotel.getHotels();
	}
	
	public void initFields() {
		nom = null;
		prenom = null;
		dateNaissance = null;
		dateEmbauche = null;
		role = null;
		salaire = null;
		dateSortie = null;
		primeDePrecarite = null;
		rue = null;
		codePostal = 0;
		ville = null;
		pays = null;
		LOGGER.info("<=============== Employe Fields initialized ===============>");
	}
	
	public void selectHotel() {
		try {
			employes = serviceHotel.getEmployes(idHotel);
			LOGGER.info("<=============== List of Employe recoverd for hotel ===============>");
		} catch (Exception e) {
			idHotel = 0L;
			employes = new HashSet<Employe>();
			LOGGER.info("<=============== No Employe for hotel ===============>");
		}
	}
	
	public void getOne(Long idPersonne) {
		employe = servicePersonne.getOne(idPersonne);
		LOGGER.info("<=============== Employe recoverd ===============>");
	}
	
	public void update() {
		servicePersonne.update(employe);
		initFields();
		selectHotel();
		LOGGER.info("<=============== Employe updated ===============>");
	}
	
	public void addCDI() {
		try {
			CDI newCdi = (CDI)personneFactory.createPersonne("CDI", nom, prenom, dateNaissance, new Adresse(rue, codePostal, ville, pays));
			newCdi.setDateEmbauche(dateEmbauche);
			newCdi.setRole(role);
			newCdi.setSalaire(salaire);
			servicePersonne.create(newCdi);
			Hotel h = serviceHotel.getOne(idHotel);
			h.getPersonnes().add(newCdi);
			serviceHotel.update(h);
			LOGGER.info("<=============== CDI Employe created ===============>");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("<=============== CDI Employe not created ===============>");
		}
		initFields();
		selectHotel();
	}
	
	public void addCDD() {
		try {
			CDD newCdd = (CDD)personneFactory.createPersonne("CDD", nom, prenom, dateNaissance, new Adresse(rue, codePostal, ville, pays));
			newCdd.setDateEmbauche(dateEmbauche);
			newCdd.setRole(role);
			newCdd.setSalaire(salaire);
			newCdd.setDateSortie(dateSortie);
			newCdd.setPrimeDePrecarite(primeDePrecarite);
			servicePersonne.create(newCdd);
			Hotel h = serviceHotel.getOne(idHotel);
			h.getPersonnes().add(newCdd);
			serviceHotel.update(h);
			LOGGER.info("<=============== CDD Employe created ===============>");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("<=============== CDD Employe not created ===============>");
		}
		initFields();
		selectHotel();
	}
	
	public void addStagiaire() {
		try {
			Stagiaire newStagiaire = (Stagiaire)personneFactory.createPersonne("Stagiaire", nom, prenom, dateNaissance, new Adresse(rue, codePostal, ville, pays));
			newStagiaire.setDateEmbauche(dateEmbauche);
			newStagiaire.setRole(role);
			newStagiaire.setSalaire(salaire);
			newStagiaire.setDateSortie(dateSortie);
			servicePersonne.create(newStagiaire);
			Hotel h = serviceHotel.getOne(idHotel);
			h.getPersonnes().add(newStagiaire);
			serviceHotel.update(h);
			LOGGER.info("<=============== Stagiaire created ===============>");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("<=============== Stagiaire not created ===============>");
		}
		initFields();
		selectHotel();
	}
	
	//=========================
	// Getter / Setter
	//=========================

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getSalaire() {
		return salaire;
	}

	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Double getPrimeDePrecarite() {
		return primeDePrecarite;
	}

	public void setPrimeDePrecarite(Double primeDePrecarite) {
		this.primeDePrecarite = primeDePrecarite;
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

	public Set<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}

	public Personne getEmploye() {
		return employe;
	}

	public void setEmploye(Personne employe) {
		this.employe = employe;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

}
