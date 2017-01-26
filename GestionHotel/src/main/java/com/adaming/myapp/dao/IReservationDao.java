/* IReservationDao
 *Version: 1.0.0
 *Date: 06-12-2016
 *Author: Guillaume Campo
 */

package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Reservation;

public interface IReservationDao {
	
	public Reservation create(Reservation r, Long idHotel, Long idChambre, Long idPersonne);
	public Reservation getOne(Long idReservation);
	public List<Reservation> getAll();
	public Reservation annuler(Long idReservation);
}
