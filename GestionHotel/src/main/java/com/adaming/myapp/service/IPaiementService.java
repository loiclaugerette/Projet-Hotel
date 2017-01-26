/*
 * IPaiementService
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Paiement;
import com.adaming.myapp.exceptions.FactureDejaPayeeException;
import com.adaming.myapp.exceptions.NullListException;

public interface IPaiementService {
	
	public Paiement add(final Paiement paiement, final Long idFacture) throws FactureDejaPayeeException;
	
	public Paiement getOne(final Long idPaiement);
	
	public List<Paiement> getAll() throws NullListException;
	
	public Paiement update(final Paiement paiement);

}
