/* 
 * PaiementFactoryImpl
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Brice Touchard
 */

package com.adaming.myapp.abstractfactory;

import java.util.Date;

import com.adaming.myapp.entities.Paiement;
import com.adaming.myapp.entities.PaiementCb;
import com.adaming.myapp.entities.PaiementCheque;
import com.adaming.myapp.entities.PaiementEspece;
import com.adaming.myapp.exceptions.NonValidClassTypeException;

public class PaiementFactoryImpl implements IPaiementFactory {

	@Override
	public Paiement createPaiement(String typePaiement, Date date) throws NonValidClassTypeException {
		if (typePaiement.equalsIgnoreCase("PaiementCb")) {
			return new PaiementCb(date);
		}
		else if (typePaiement.equalsIgnoreCase("PaiementCheque")) {
			return new PaiementCheque(date);
		}
		else if (typePaiement.equalsIgnoreCase("PaiementEspece")) {
			return new PaiementEspece(date);
		}
		else {
			throw new NonValidClassTypeException("typePaiement " + typePaiement + "is not a valid type");
		}
	}

}
