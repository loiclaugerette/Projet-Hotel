package com.adaming.myapp.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Employe;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.entities.Reservation;

public class HotelDaoImpl implements IHotelDao{

	@PersistenceContext
	protected EntityManager em;
	
	//=========================
	// Attributes
	//=========================

	private final Logger LOGGER = Logger.getLogger("HotelDaoImpl");

	//private List<Employe> employes;


	//=========================
	// Methods
	//=========================
	
	@Override
	public Hotel save(Hotel h, Set<Chambre> chambres) {
		h.setChambres(chambres);
		em.persist(h);
    	return h;
	}

	@Override
	public Hotel getOne(Long id) {
		Hotel h = em.find(Hotel.class,id);
		return h;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> getHotels() {
		return em.createQuery("from Hotel h ORDER BY h.nombreEtoiles DESC").getResultList();
	}

	@Override
	public Hotel update(Hotel h) {
		em.merge(h);
		return h;
	}

	@Override
	public Hotel addPersonne(Long idHotel, Long idPersonne) {
		Hotel h = em.find(Hotel.class,idHotel);
		Personne p = em.find(Personne.class,idPersonne);
		h.getPersonnes().add(p);
		return h;
	}

	@Override
	public Hotel addReservation(Long idHotel, Long idReservation) {
		Hotel h = em.find(Hotel.class,idHotel);
		Reservation r = em.find(Reservation.class,idReservation);
		h.getReservations().add(r);
		return h;
	}

	@Override
	public Hotel addFacture(Long idHotel, Long idFacture) {
		Hotel h = em.find(Hotel.class,idHotel);
		Facture f = em.find(Facture.class,idFacture);
		h.getFactures().add(f);
		return h;
	}

	@Override
	public Hotel addProduit(Long idHotel, Long idProduit) {
		Hotel h = em.find(Hotel.class,idHotel);
		Produit p = em.find(Produit.class,idProduit);
		h.getProduits().add(p);
		return h;
	}

	@Override
	public Set<Employe> getEmployes(Long idHotel) {
		Hotel h = em.find(Hotel.class,idHotel);
		List<Personne> personnes = h.getPersonnes();
		Set<Employe> employes = new HashSet<Employe>();
		for (Personne p:personnes){
			if(p.getClass().getSuperclass() == Employe.class){
				employes.add((Employe) p);
			}
		}
		return employes;
	}

	@Override
	public Set<Client> getClients(Long idHotel) {
		Hotel h = em.find(Hotel.class,idHotel);
		List<Personne> personnes = h.getPersonnes();
		Set <Client> clients = new HashSet<Client>();
		for (Personne p:personnes){
			if(p.getClass().equals(Client.class)){
				clients.add((Client) p);
			}
		}
		return clients;
	}

	@Override
	public Set<Produit> getProduits(Long idHotel) {
		Hotel h = getOne(idHotel);
		Set<Produit> produits = h.getProduits();
		return produits;
	}	
	
}
