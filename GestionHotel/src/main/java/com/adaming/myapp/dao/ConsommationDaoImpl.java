/*
 * ConsommationDaoImpl
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Consommation;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Produit;

public class ConsommationDaoImpl implements IConsommationDao {

	//=========================
	// Attributes
	//=========================
	
	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("ConsommationDaoImpl");

	//=========================
	// Methods
	//=========================

	@Override
	public Consommation add(Consommation consommation, Long idClient, Long idProduit) {
		consommation.setPersonne(em.find(Personne.class, idClient));
		consommation.setProduit(em.find(Produit.class, idProduit));
		em.persist(consommation);
		LOGGER.info("<--------------- " + consommation + " added --------------->");
		return consommation;
	}

	@Override
	public Consommation getOne(Long idConsommation) {
		Consommation consommation = em.find(Consommation.class, idConsommation);
		LOGGER.info("<--------------- " + consommation + " found --------------->");
		return consommation;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Consommation> getAll() {
		List<Consommation> consommations = em.createQuery("from Consommation co").getResultList();
		LOGGER.info("<--------------- List of Consommation recoverd ---------------->");
		return consommations;
	}

	@Override
	public Consommation update(Consommation consommation) {
		em.merge(consommation);
		LOGGER.info("<--------------- " + consommation + " updated --------------->");
		return consommation;
	}

}
