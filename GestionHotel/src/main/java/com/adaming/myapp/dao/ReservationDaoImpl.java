/* ReservationDaoImpl
 *Version: 1.0.0
 *Date: 06-12-2016
 *Author: Guillaume Campo
 */


package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Reservation;

public class ReservationDaoImpl implements IReservationDao{
	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("ReservationDaoImpl");

	//============================
	// Methods
	//============================
	

	
	@Override
    public Reservation create(Reservation r, Long idHotel, Long idChambre,
            Long idPersonne) {
        Chambre ch = em.find(Chambre.class, idChambre);
        Hotel h = em.find(Hotel.class, idHotel);
        Personne p = em.find(Personne.class, idPersonne);
        r.setChambre(ch);
        ch.getReservations().add(r);
        r.setHotel(h);
        r.setPersonne(p);
        em.merge(ch);
        em.persist(r);
        LOGGER.info("La réservation a été crée avec l'hotel "+idHotel+" la chambre "+idChambre+" et la personne "+idPersonne);
        return r;        
    }
	
	

	@Override
	public Reservation getOne(Long idReservation) {
		Reservation r = em.find(Reservation.class, idReservation);
		LOGGER.info("La réservation "+idReservation+" a été trouvée");
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getAll() {
		List<Reservation> reservations= em.createQuery("from Reservation r").getResultList();
		LOGGER.info("les réservations ont été récupérées");
		return reservations;
	}

	@Override
	public Reservation annuler(Long idReservation) {
		// TODO Auto-generated method stub
		Reservation r = em.find(Reservation.class, idReservation);
		r.setChambre(null);
		r.setDateArrivee(null);
		r.setDateSortie(null);
		r.setFacture(null);
		r.setHotel(null);
		r.setPersonne(null);
		r.setValide(false);
		em.merge(r);
		LOGGER.info("La réservation a été annulée");
		return r;
	}
	
}
