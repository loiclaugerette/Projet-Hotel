/* 
 * IPaiementFactory
 * Version: 1.0.0
 * Date: 05-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.abstractfactory;

import java.util.Date;

import com.adaming.myapp.entities.Paiement;
import com.adaming.myapp.exceptions.NonValidClassTypeException;

public interface IPaiementFactory {
	
	public Paiement createPaiement(final String typePaiement, final Date date) throws NonValidClassTypeException;

}
