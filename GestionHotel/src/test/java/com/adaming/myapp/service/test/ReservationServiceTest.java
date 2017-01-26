package com.adaming.myapp.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.ChambreSimple;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IChambreService;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IPersonneService;
import com.adaming.myapp.service.IReservationService;

public class ReservationServiceTest {
		
    private static ClassPathXmlApplicationContext context;
	
	private static IReservationService  service;
	private static IHotelService  serviceB;
	private static IChambreService  serviceC;
	private static IPersonneService  serviceD;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	  context= new ClassPathXmlApplicationContext("app.xml");
	  service = (IReservationService) context.getBean("ReservationServiceImpl");
	  serviceB = (IHotelService) context.getBean("HotelServiceImpl");
	  serviceC = (IChambreService) context.getBean("ChambreServiceImpl");
	  serviceD = (IPersonneService) context.getBean("PersonneServiceImpl");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	  context.close();
	}
	
	@Test
	public void CreateTest(){
		Adresse a = new Adresse("rue", 13127, "ville", "pays");
		Reservation r=new Reservation(new Date(), new Date());
		Hotel h = new Hotel("nom", a, 1);
		Chambre c = new ChambreSimple(0, "description");
		Set<Chambre> chambres = new HashSet<Chambre>();
		chambres.add(c);
		Personne p = new Client("nom", "prenom", new Date(), a);
		serviceC.add(c);
		serviceB.save(h, chambres);
		serviceD.create(p);
		service.create(r, h.getId(), c.getId(), p.getIdPersonne());
		assertNotNull(r.getId());
	}


	@Ignore
	@Test
	public void GetOneTest(){
		Reservation r = service.getOne(1L);
		assertTrue(r.getId().equals(1L));
	}
	
	@Ignore
	@Test
	public void getAllTest() throws NullListException{
		List<Reservation> reservations = service.getAll();
		assertNotNull(reservations.size());
	}
	
	@Ignore
	@Test
	public void annulerTest(){
		service.annuler(1L);
		assertNull(service.getOne(1L).getChambre());
	}
}
