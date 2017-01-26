/* IReservationService
 *Version: 1.0.0
 *Date: 06-12-2016
 *Author: Guillaume Campo
 */

package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.exceptions.NullListException;

public interface IReservationService {
	
	public Reservation create(Reservation r, Long idHotel, Long idChambre, Long idPersonne);
	public Reservation getOne(Long idReservation);
	public List<Reservation> getAll() throws NullListException;
	public Reservation annuler(Long idReservation);

}
