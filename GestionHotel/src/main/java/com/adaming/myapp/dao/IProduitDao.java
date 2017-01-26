package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Produit;

public interface IProduitDao {
	
	public Produit add(final Produit produit, final Long idHotel);
	
	public Produit getOne(final Long idProduit);
	
	public List<Produit> getAll();
	
	public Produit update(final Produit produit);

}
