package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.exceptions.NullListException;

public interface IChambreService {
	
	public Chambre add(final Chambre chambre);
	
	public Chambre getOne(final Long idChambre);
	
	public List<Chambre> getAll() throws NullListException;
	
	public Chambre update(final Chambre chambre);

}
