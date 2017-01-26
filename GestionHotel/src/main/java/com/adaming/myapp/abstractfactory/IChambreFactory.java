/*
 * IChambreFactory
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.abstractfactory;

import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.exceptions.NonValidClassTypeException;

public interface IChambreFactory {
	
	public Chambre createChambre(final String typeChambre, final int numeroChambre, final String description) throws NonValidClassTypeException;

}
