package com.adaming.myapp.abstractfactory;

import java.util.Date;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.CDD;
import com.adaming.myapp.entities.CDI;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Stagiaire;
import com.adaming.myapp.exceptions.NonValidClassTypeException;

public class PersonneFactoryImpl implements IPersonneFactory{

	@Override
	public Personne createPersonne(String typePersonne, String nom,
			String prenom, Date dateNaissance, Adresse adresse)
			throws NonValidClassTypeException {
		if(typePersonne.equalsIgnoreCase("Client")){
			return new Client(nom, prenom, dateNaissance, adresse);
		}
		else if(typePersonne.equalsIgnoreCase("CDD")){
			return new CDD(nom, prenom, dateNaissance, adresse);
		}
		else if(typePersonne.equalsIgnoreCase("CDI")){
			return new CDI(nom, prenom, dateNaissance, adresse);
		}
		else if(typePersonne.equalsIgnoreCase("Stagiaire")){
			return new Stagiaire(nom, prenom, dateNaissance, adresse);
		}
		else{
			throw new NonValidClassTypeException("typePersonne " + typePersonne + "is not a valid type");
		}
	
	}
	
	

}
