/*
 * ProduitServiceImpl
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IProduitDao;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ProduitServiceImpl implements IProduitService {

	//=========================
	// Attributes
	//=========================
	
	private IProduitDao dao;

	//=========================
	// Getter / Setter
	//=========================
	
	public void setDao(IProduitDao dao) {
		this.dao = dao;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public Produit add(Produit produit, Long idHotel) {
		return dao.add(produit, idHotel);
	}

	@Override
	public Produit getOne(Long idProduit) {
		return dao.getOne(idProduit);
	}

	@Override
	public List<Produit> getAll() throws NullListException {
		List<Produit> produits = dao.getAll();
		if (produits.size() <= 0) {
			throw new NullListException("No Produits recoverd");
		}
		return produits;
	}

	@Override
	public Produit update(Produit produit) {
		return dao.update(produit);
	}	

}
