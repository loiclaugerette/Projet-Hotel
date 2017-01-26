package com.adaming.myapp.abstractfactory;

import java.util.Date;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.exceptions.NonValidClassTypeException;

public interface IPersonneFactory {
	public Personne createPersonne(final String typePersonne, final String nom, final String prenom, final Date dateNaissance, final Adresse adresse) throws NonValidClassTypeException;

}
