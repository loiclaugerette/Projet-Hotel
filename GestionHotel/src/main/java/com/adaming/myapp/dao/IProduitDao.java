/*
 * IProduitDao
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Produit;

public interface IProduitDao {
	
	public Produit add(final Produit produit, final Long idHotel);
	
	public Produit getOne(final Long idProduit);
	
	public List<Produit> getAll();
	
	public Produit update(final Produit produit);

}
