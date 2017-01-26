package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Facture;

public interface IFactureDao {
	
	public Facture create(final Facture f, final Long idHotel);
	public Facture remplirReservation(final Long idFacture, final Long idReservation);
	public Facture remplirConsommation(final Long idFacture, final Long idConsommation) throws Exception;
	public Facture imprimer(final Long idFacture);
	public List<Facture> getAll();


}
