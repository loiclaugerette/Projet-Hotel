package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.exceptions.NullListException;

public interface IProduitService {
	
	public Produit add(final Produit produit, final Long idHotel);
	
	public Produit getOne(final Long idProduit);
	
	public List<Produit> getAll() throws NullListException;
	
	public Produit update(final Produit produit);

}