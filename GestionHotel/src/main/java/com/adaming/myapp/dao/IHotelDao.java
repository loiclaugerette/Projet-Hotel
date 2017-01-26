package com.adaming.myapp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Employe;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.entities.Reservation;

public interface IHotelDao {

	/*--------------CRUD-----------------*/
	Hotel save(Hotel h, Set<Chambre> chambres);
	// Adresse, List<Chambre>, List<Personne>, List<Reservation>, List<Facture>, List<Produit>,
	Hotel getOne(Long id);
	List<Hotel> getHotels();
	Hotel update(Hotel h);
	
	Hotel addPersonne(Long idHotel, Long idPersonne);
	Hotel addReservation(Long idHotel, Long idReservation);
	Hotel addFacture(Long idHotel, Long idFacture);
	Hotel addProduit(Long idHotel, Long idProduit);
	
	Set<Employe> getEmployes(Long idHotel);
	Set<Client> getClients(Long idHotel);
	Set<Produit> getProduits(Long idHotel);
}
