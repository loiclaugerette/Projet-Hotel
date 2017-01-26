package com.adaming.myapp.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CD")
public class ChambreDouble extends Chambre{
	
	//=============
	//attributes
	//=============
	
	//=============
	//constructors
	//=============
	public ChambreDouble() {
	}

	public ChambreDouble(int numeroChambre, String description) {
		super(numeroChambre, description);
		prix=100.0;
	}

	//=============
	//Getters and setters
	//=============
	
}
