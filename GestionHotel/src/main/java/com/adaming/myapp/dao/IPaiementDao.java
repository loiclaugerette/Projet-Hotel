package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Paiement;
import com.adaming.myapp.exceptions.FactureDejaPayeeException;

public interface IPaiementDao {
	
	public Paiement add(final Paiement paiement, final Long idFacture) throws FactureDejaPayeeException;
	
	public Paiement getOne(final Long idPaiement);
	
	public List<Paiement> getAll();
	
	public Paiement update(final Paiement paiement);

}
