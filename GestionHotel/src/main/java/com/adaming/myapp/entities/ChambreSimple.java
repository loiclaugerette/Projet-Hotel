package com.adaming.myapp.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CS")
public class ChambreSimple extends Chambre{
	
		//=============
		//attributes
		//=============
		
		//=============
		//constructors
		//=============
		public ChambreSimple() {
		}

		public ChambreSimple(int numeroChambre, String description) {
			super(numeroChambre, description);
			prix=70.0;
		}

		//=============
		//Getters and setters
		//=============	

}
