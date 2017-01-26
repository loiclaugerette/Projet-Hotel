package com.adaming.myapp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IHotelDao;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Employe;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.entities.Reservation;

@Transactional
public class HotelServiceImpl implements IHotelService{

	private IHotelDao dao;

	public void setDao(IHotelDao dao) {
		this.dao = dao;
	}

	@Override
	public Double beneficeSemestre(Hotel h) {
		//TO MODIFY
		return 0.0;
	}

	@Override
	public Double beneficeAnnee(Hotel h) {
		System.out.println("Voici la liste des facture de l'hotel : "+h.getFactures());
		List<Facture> factures = h.getFactures();
		Set<Employe> employes = dao.getEmployes(h.getId());
				
		Date today = new Date();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(today);
		int moisToday = calendar1.get(Calendar.MONTH);
		int yearToday = calendar1.get(Calendar.YEAR);
		
		Double Gain = 0.0;
		Double Perte = 0.0;
		Double Benef = 0.0;
		double workTime = 0.0;
		System.out.println(factures);
		System.out.println(employes);
		
		for (Facture f:factures){
			if(f.getPaiement()!=null){
				System.out.println("Voici le paiement de la facture de l'hotel : "+f.getPaiement());
				Date date = f.getPaiement().getDate();
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(date);
				int yearPayment = calendar2.get(Calendar.YEAR);
			
				if(yearPayment==yearToday){
					Gain = Gain + f.getPaiement().getCoutTotal();
				}
			}
		}
		System.out.println(Gain);
		for (Employe e:employes){
			Date dateEmbauche = e.getDateEmbauche();
			Calendar calendar3 = Calendar.getInstance();
			calendar3.setTime(dateEmbauche);
			int moisEmbauche = calendar3.get(Calendar.MONTH);
			System.out.println("today : "+today.getTime());
			System.out.println("time embauche : "+dateEmbauche.getTime());
			workTime =  0.2*(today.getTime()-dateEmbauche.getTime())/(1000.0*3600.0*720.0);  //milliseconds -> seconds -> heures -> mois 
			System.out.println("workTime: "+workTime);
			Perte = Perte + workTime*e.getSalaire();
		}
		System.out.println(Perte);
		Benef = Gain - Perte;
		return  Benef ;
	}

	@Override
	public Hotel save(Hotel h, Set<Chambre> chambres) {
		// TODO Auto-generated method stub
		return dao.save(h, chambres);
	}

	@Override
	public Hotel getOne(Long id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public List<Hotel> getHotels() {
		// TODO Auto-generated method stub
		return dao.getHotels();
	}

	@Override
	public Hotel update(Hotel h) {
		// TODO Auto-generated method stub
		return dao.update(h);
	}

	@Override
	public Hotel addPersonne(Long idHotel, Long idPersonne) {
		// TODO Auto-generated method stub
		return dao.addPersonne(idHotel, idPersonne);
	}

	@Override
	public Hotel addReservation(Long idHotel, Long idReservation) {
		// TODO Auto-generated method stub
		return dao.addReservation(idHotel, idReservation);
	}

	@Override
	public Hotel addFacture(Long idHotel, Long idFacture) {
		// TODO Auto-generated method stub
		return dao.addFacture(idHotel, idFacture);
	}

	@Override
	public Hotel addProduit(Long idHotel, Long idProduit) {
		// TODO Auto-generated method stub
		return dao.addProduit(idHotel, idProduit);
	}

	@Override
	public Set<Employe> getEmployes(Long idHotel) {
		// TODO Auto-generated method stub
		return dao.getEmployes(idHotel);
	}

	@Override
	public Set<Client> getClients(Long idHotel) {
		// TODO Auto-generated method stub
		return dao.getClients(idHotel);
	}

	@Override
	public Set<Chambre> getChambreDisponible(Long idHotel, Date dateArrivee, Date dateSortie) {
		Hotel h1 = getOne(idHotel);
		Set<Chambre> chambres = h1.getChambres();
		Set<Chambre> chambresDispos = new HashSet<Chambre>();
		for(Chambre c:chambres){
			if(c.getReservations().isEmpty()){
				chambresDispos.add(c);
				System.out.println("Il n'y a pas de réservation pour la chambre "+c.getDescription());
			}else{
				boolean isDispo = true;
				for(Reservation r:c.getReservations()){
					if(    (r.getDateArrivee()).compareTo( dateArrivee )==-1 ) {
						if(   (r.getDateSortie()).compareTo( dateArrivee )==1    ){
							isDispo = false;
							System.out.println("NOPE : cas 1 pour la chambre : "+c.getDescription());
						}else{
//							chambresDispos.add(c);
							System.out.println("cas 2");
						}
					}else{
						if(  (r.getDateArrivee()).compareTo( dateSortie )==-1  ){
							isDispo = false;
							System.out.println("NOPE : cas 4 pour la chambre : "+c.getDescription());
						}else{
//							chambresDispos.add(c);
							System.out.println("cas 3");
						}
					}
				}
				if (isDispo) {
					chambresDispos.add(c);
				}
			}
		}
		return chambresDispos;
	}

	@Override
	public Set<Produit> getProduits(Long idHotel) {
		// TODO Auto-generated method stub
		return dao.getProduits(idHotel);
	}

	
}
