/*
 * PersonneServiceImpl
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IPersonneDao;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class PersonneServiceImpl implements IPersonneService {

	//=========================
	// Attributes
	//=========================
	
	private IPersonneDao dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IPersonneDao dao) {
		this.dao = dao;
	}

	//=========================
	// Methods
	//=========================
	
	@Override
	public Personne create(Personne p) {
		// TODO Auto-generated method stub
		return dao.create(p);
	}

	@Override
	public Personne getOne(Long idPersonne) {
		// TODO Auto-generated method stub
		return dao.getOne(idPersonne);
	}

	@Override
	public List<Personne> getAll() throws NullListException {
		// TODO Auto-generated method stub
		List<Personne> personnes = dao.getAll();
		if (personnes.size()==0){
			throw new NullListException("Aucune personne");
		}
		else{
			return personnes;
		}
	}

	@Override
	public Personne update(Personne p) {
		return dao.update(p);
	}

	@Override
	public Set<Facture> getFacturesByClient(Long idPersonne) throws NullListException {
		Personne p = getOne(idPersonne);
		Set<Facture> factures = new HashSet<Facture>();
		List<Reservation> reservations = p.getReservations();
		if(reservations.size()==0){
			throw new NullListException("Pas de réservation pour ce client");
		}
		else{
			for (Reservation rTest: reservations){
				factures.add(rTest.getFacture());
			}
			return factures;
		}
	}
	
	


}
