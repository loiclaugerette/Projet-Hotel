/*
 * PaiementDaoImpl
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Paiement;
import com.adaming.myapp.exceptions.FactureDejaPayeeException;

public class PaiementDaoImpl implements IPaiementDao {

	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("PaiementDaoImpl");

	//=========================
	// Methods
	//=========================

	@Override
	public Paiement add(Paiement paiement, Long idFacture) throws FactureDejaPayeeException {
		Facture facture = em.find(Facture.class, idFacture);
		if(facture.getPaiement()==null){
			Double coutTotal = facture.getCoutConsommation() + facture.getCoutReservation();
			paiement.setCoutTotal(coutTotal);
			paiement.setFacture(facture);
			facture.setPaiement(paiement);
			em.persist(paiement);
			em.merge(facture);
			LOGGER.info("<--------------- " + paiement + " added --------------->");
			return paiement;
		}else{
			throw new FactureDejaPayeeException("La facture a déjà été payée.");
		}
	}
	
	@Override
	public Paiement getOne(Long idPaiement) {
		Paiement paiement = em.find(Paiement.class, idPaiement);
		LOGGER.info("<--------------- " + paiement + " found --------------->");
		return paiement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Paiement> getAll() {
		List<Paiement> paiements = em.createQuery("from Paiement pa").getResultList();
		LOGGER.info("<--------------- List of Paiement recoverd --------------->");
		return paiements;
	}

	@Override
	public Paiement update(Paiement paiement) {
		em.merge(paiement);
		LOGGER.info("<--------------- " + paiement + " updated --------------->");
		return paiement;
	}

}
