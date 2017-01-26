package com.adaming.myapp.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Consommation;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.exceptions.NoProduitInStock;
import com.adaming.myapp.exceptions.NoStockException;

public class FactureDaoImpl implements IFactureDao{
	
	//=========================
	// Attributes
	//=========================

	private final Logger LOGGER = Logger.getLogger("FactureDaoImpl");

	@PersistenceContext
	private EntityManager em;


	//=========================
	// Methods
	//=========================
	
	@Override
	public Facture create(Facture f, Long idHotel) {
		Hotel h = em.find(Hotel.class, idHotel);
		f.setHotel(h);
		em.persist(f);
		LOGGER.info("La facture a été crée pour l'hôtel "+idHotel);
		return f;
	}
	
	@Override
	public Facture remplirReservation(Long idFacture, Long idReservation) {
		Facture f = em.find(Facture.class, idFacture);
		Reservation r = em.find(Reservation.class, idReservation);
		f.getReservations().add(r);
		Date sortie = r.getDateSortie();
		Date entree = r.getDateArrivee();
		Long sortieMilli = sortie.getTime();
		Long entreeMilli = entree.getTime();
		
		f.setCoutReservation(f.getCoutReservation() + r.getChambre().getPrix()*Math.abs((sortieMilli-entreeMilli)/(1000*60*60*24)));
		r.setFacture(f);
		em.merge(f);
		em.merge(r);
		LOGGER.info("La facture a été remplie avec la réservation "+idReservation);
		return f;	
	}
	
	@Override
	public Facture remplirConsommation(Long idFacture, Long idConsommation) throws Exception {
		Facture f = em.find(Facture.class, idFacture);
		Consommation c = em.find(Consommation.class, idConsommation);
		Set<Produit> produitsHotel = f.getHotel().getProduits();
		boolean foundProduit = false;
		for (Produit produitHotel:produitsHotel) {
			if (produitHotel.getIdProduit() == c.getProduit().getIdProduit()) {
				foundProduit = true;
				if (produitHotel.getQuantite() >= c.getQuantite()) {
					produitHotel.setQuantite(produitHotel.getQuantite() - c.getQuantite());
				}
				else {
					throw new NoStockException("Not enough " + produitHotel.getNom());
				}
			}
		}
		if (!foundProduit) {
			throw new NoProduitInStock("No " + c.getProduit().getNom() + " in hotel " + f.getHotel().getNom() + " stock");
		}
		f.getConsommations().add(c);
		f.setCoutConsommation(f.getCoutConsommation() + c.getQuantite() * c.getProduit().getCoutVente());
		em.merge(f);
		LOGGER.info("La facture a été remplie avec la consommation "+idConsommation);
		return f;
	}

	@Override
	public Facture imprimer(Long idFacture) {
		Facture f = em.find(Facture.class, idFacture);
		LOGGER.info("La facture a été trouvée");
		return f;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Facture> getAll() {
		List<Facture> factures = em.createQuery("from Facture f").getResultList();
		return factures;
	}



	

	
	
}
