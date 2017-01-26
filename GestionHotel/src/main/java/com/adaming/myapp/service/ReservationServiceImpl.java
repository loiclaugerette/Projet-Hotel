/* ReservationServiceImpl
 *Version: 1.0.0
 *Date: 06-12-2016
 *Author: Guillaume Campo
 */

package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IReservationDao;
import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ReservationServiceImpl implements IReservationService {

	//injection dépendance//
	private IReservationDao dao;

	public void setDao(IReservationDao dao) {
		this.dao = dao;
	}

	//méthodes//
	@Override
	public Reservation create(Reservation r, Long idHotel, Long idChambre,
			Long idPersonne) {
		// TODO Auto-generated method stub
		return dao.create(r, idHotel, idChambre, idPersonne);
	}

	@Override
	public Reservation getOne(Long idReservation) {
		// TODO Auto-generated method stub
		return dao.getOne(idReservation);
	}

	@Override
	public List<Reservation> getAll() throws NullListException {
		// TODO Auto-generated method stub
		List<Reservation>reservations =dao.getAll();
		if(reservations.size()==0){
			throw new NullListException("aucune réservation");
		}
		else
		{
			return dao.getAll();
		}
	}

	@Override
	public Reservation annuler(Long idReservation) {
		return dao.annuler(idReservation);
	}
	
	
	
	
}
