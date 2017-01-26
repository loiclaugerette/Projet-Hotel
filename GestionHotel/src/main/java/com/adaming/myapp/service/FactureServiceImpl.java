package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IFactureDao;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class FactureServiceImpl implements IFactureService{
	
	private IFactureDao dao;

	public void setDao(IFactureDao dao) {
		this.dao = dao;
	}

	@Override
	public Facture create(Facture f, Long idHotel) {
		return dao.create(f, idHotel);
	}

	@Override
	public Facture remplirReservation(final Long idFacture, final Long idReservation) {
		return dao.remplirReservation(idFacture, idReservation);
	}
	public Facture remplirConsommation(final Long idFacture, final Long idConsommation) throws Exception {
		return dao.remplirConsommation(idFacture, idConsommation);
	}


	@Override
	public Facture imprimer(Long idFacture) {
		return dao.imprimer(idFacture);
	}

	@Override
	public List<Facture> getAll() throws NullListException {
		List<Facture> factures = dao.getAll();
		if (factures.size()==0){
			throw new NullListException("Aucune facture");}
		else{
			return dao.getAll();
		}
	}


	
	

	
}
