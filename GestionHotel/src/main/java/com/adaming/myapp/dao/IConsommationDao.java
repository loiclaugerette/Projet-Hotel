package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Consommation;

public interface IConsommationDao {
	
	public Consommation add(final Consommation consommation, final Long idClient, final Long idProduit);
	
	public Consommation getOne(final Long idConsommation);
	
	public List<Consommation> getAll();
	
	public Consommation update(final Consommation consommation);

}
