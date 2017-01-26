package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Reservation;

public interface IReservationDao {
	
	public Reservation create(Reservation r, Long idHotel, Long idChambre, Long idPersonne);
	public Reservation getOne(Long idReservation);
	public List<Reservation> getAll();
	public Reservation annuler(Long idReservation);
}
