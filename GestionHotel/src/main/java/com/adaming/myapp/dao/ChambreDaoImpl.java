/*
 * IChambreFactory
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Chambre;

public class ChambreDaoImpl implements IChambreDao{

	//=========================
	// Attributes
	//=========================
	
	@PersistenceContext
	private EntityManager em;
	
	private final Logger LOGGER = Logger.getLogger("ChambreDaoImpl");

	//=========================
	// Methods
	//=========================
	
	@Override
	public Chambre add(Chambre chambre) {
		em.persist(chambre);
		LOGGER.info("<--------------- " + chambre + " added --------------->");
		return chambre;
	}

	@Override
	public Chambre getOne(Long idChambre) {
		Chambre chambre = em.find(Chambre.class, idChambre);
		LOGGER.info("<--------------- " + chambre + " found --------------->");
		return chambre;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Chambre> getAll() {
		List<Chambre> chambres = em.createQuery("from Chambre ch").getResultList();
		LOGGER.info("<--------------- List of Chambre recoverd --------------->");
		return chambres;
	}

	@Override
	public Chambre update(Chambre chambre) {
		em.merge(chambre);
		LOGGER.info("<--------------- " + chambre + " updated --------------->");
		return chambre;
	}
	
}
