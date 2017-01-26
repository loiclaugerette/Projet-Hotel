/*
 * IPersonneService
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service;

import java.util.List;
import java.util.Set;

import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.exceptions.NullListException;

public interface IPersonneService {
	
	Personne create(Personne p);
	Personne getOne(Long idPersonne);
	List<Personne> getAll() throws NullListException;
	Personne update(Personne p);
	Set<Facture> getFacturesByClient(Long idPersonne) throws NullListException;

}
