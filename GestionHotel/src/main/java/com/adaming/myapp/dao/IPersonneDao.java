/*
 * IPersonneDao
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Personne;

public interface IPersonneDao {
	
	Personne create(Personne p);
	Personne getOne(Long idPersonne);
	List<Personne> getAll();
	Personne update(Personne p);


}
