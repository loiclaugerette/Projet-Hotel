/*
 * PaiementServiceImpl
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IPaiementDao;
import com.adaming.myapp.entities.Paiement;
import com.adaming.myapp.exceptions.FactureDejaPayeeException;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class PaiementServiceImpl implements IPaiementService {

	//=========================
	// Attributes
	//=========================
	
	private IPaiementDao dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IPaiementDao dao) {
		this.dao = dao;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public Paiement add(Paiement paiement, Long idFacture) throws FactureDejaPayeeException {
		return dao.add(paiement, idFacture);
	}

	@Override
	public Paiement getOne(Long idPaiement) {
		return dao.getOne(idPaiement);
	}

	@Override
	public List<Paiement> getAll() throws NullListException {
		List<Paiement> paiements = dao.getAll();
		if (paiements.size() <= 0) {
			throw new NullListException("No Paiement recoverd");
		}
		return paiements;
	}

	@Override
	public Paiement update(Paiement paiement) {
		return dao.update(paiement);
	}
	
}
