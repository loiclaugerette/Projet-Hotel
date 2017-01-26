package com.adaming.myapp.abstractfactory;

import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.exceptions.NonValidClassTypeException;

public interface IChambreFactory {
	
	public Chambre createChambre(final String typeChambre, final int numeroChambre, final String description) throws NonValidClassTypeException;

}
