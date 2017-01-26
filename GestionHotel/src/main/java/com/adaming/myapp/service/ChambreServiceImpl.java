/*
 * IChambreFactory
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IChambreDao;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.exceptions.NullListException;


@Transactional
public class ChambreServiceImpl implements IChambreService{

	//=========================
	// Attributes
	//=========================
	
	private IChambreDao dao;

	//=========================
	// Setter
	//=========================
	
	public void setDao(IChambreDao dao) {
		this.dao = dao;
	}
	
	//=========================
	// Methods
	//=========================

	@Override
	public Chambre add(Chambre chambre) {
		return dao.add(chambre);
	}

	@Override
	public Chambre getOne(Long idChambre) {
		return dao.getOne(idChambre);
	}

	@Override
	public List<Chambre> getAll() throws NullListException {
		List<Chambre> chambres = dao.getAll();
		if (chambres.size() <= 0) {
			throw new NullListException("No Chambre recoverd");
		}
		return chambres;
	}

	@Override
	public Chambre update(Chambre chambre) {
		return dao.update(chambre);
	}
	
}
