/*
 * ConsommationServiceImpl
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IConsommationDao;
import com.adaming.myapp.entities.Consommation;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ConsommationServiceImpl implements IConsommationService {

	//=========================
	// Attributes
	//=========================
	
	private IConsommationDao dao;
	
	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IConsommationDao dao) {
		this.dao = dao;
	}
	
	//=========================
	// Methods
	//=========================

	@Override
	public Consommation add(Consommation consommation, Long idClient,
			Long idProduit) {
		return dao.add(consommation, idClient, idProduit);
	}

	@Override
	public Consommation getOne(Long idConsommation) {
		return dao.getOne(idConsommation);
	}

	@Override
	public List<Consommation> getAll() throws NullListException {
		List<Consommation> consommations = dao.getAll();
		if (consommations.size() <= 0) {
			throw new NullListException("No Consommation recoverd");
		}
		return consommations;
	}

	@Override
	public Consommation update(Consommation consommation) {
		return dao.update(consommation);
	}

}
