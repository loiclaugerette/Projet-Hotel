/* 
 * ChambreFactoryImpl
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.abstractfactory;

import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.ChambreDouble;
import com.adaming.myapp.entities.ChambreSimple;
import com.adaming.myapp.entities.Suite;
import com.adaming.myapp.exceptions.NonValidClassTypeException;

public class ChambreFactoryImpl implements IChambreFactory {

	@Override
	public Chambre createChambre(String typeChambre, int numeroChambre, String description) throws NonValidClassTypeException {
		if (typeChambre.equalsIgnoreCase("ChambreSimple")) {
			return new ChambreSimple(numeroChambre, description);
		}
		else if (typeChambre.equalsIgnoreCase("ChambreDouble")) {
			return new ChambreDouble(numeroChambre, description);
		}
		else if (typeChambre.equalsIgnoreCase("Suite")) {
			return new Suite(numeroChambre, description);
		}
		else {
			throw new NonValidClassTypeException("typeChambre " + typeChambre + " is not a valid type");
		}
	}

}
