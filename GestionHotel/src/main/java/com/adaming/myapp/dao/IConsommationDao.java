/*
 * IConsommationDao
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Consommation;

public interface IConsommationDao {
	
	public Consommation add(final Consommation consommation, final Long idClient, final Long idProduit);
	
	public Consommation getOne(final Long idConsommation);
	
	public List<Consommation> getAll();
	
	public Consommation update(final Consommation consommation);

}
