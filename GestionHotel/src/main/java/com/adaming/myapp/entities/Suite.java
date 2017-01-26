package com.adaming.myapp.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class Suite extends Chambre {
	

	//=============
	//attributes
	//=============
	
	//=============
	//constructors
	//=============
	public Suite() {
	}

	public Suite(int numeroChambre, String description) {
		super(numeroChambre, description);
		prix=200.0;
	}

	//=============
	//Getters and setters
	//=============

}
