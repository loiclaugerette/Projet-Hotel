/*
 * PersonneDaoImpl
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
import com.adaming.myapp.entities.Personne;

public class PersonneDaoImpl implements IPersonneDao {

	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("PersonneDaoImpl");



	//=========================
	// Methods
	//=========================

	@Override
	public Personne create(Personne p) {
		em.persist(p);
		LOGGER.info("La personne a été crée");
		return p;
	}

	@Override
	public Personne getOne(Long idPersonne) {
		Personne p = em.find(Personne.class,idPersonne);
		LOGGER.info("La personne "+idPersonne+" a été trouvée");
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personne> getAll() {
		List<Personne> personnes= em.createQuery("from Personne p").getResultList();
		return personnes;
	}

	@Override
	public Personne update(Personne p) {
		em.merge(p);
		return p;
	}


}
