/*
 * IChambreFactory
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Chambre;

public interface IChambreDao {
	
	public Chambre add(final Chambre chambre);
	
	public Chambre getOne(final Long idChambre);
	
	public List<Chambre> getAll();
	
	public Chambre update(final Chambre chambre);

}
