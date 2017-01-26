package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.exceptions.NullListException;

public interface IFactureService {
	
	public Facture create (final Facture f, final Long idHotel);
	public Facture remplirReservation(final Long idFacture, final Long idReservation);
	public Facture remplirConsommation(final Long idFacture, final Long idConsommation) throws Exception;
	public Facture imprimer(final Long idFacture);
	public List<Facture> getAll() throws NullListException;

}
