package com.adaming.myapp.service.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.abstractfactory.IPersonneFactory;
import com.adaming.myapp.abstractfactory.PersonneFactoryImpl;
import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.CDD;
import com.adaming.myapp.entities.CDI;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.ChambreSimple;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.entities.Stagiaire;
import com.adaming.myapp.exceptions.NonValidClassTypeException;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IChambreService;
import com.adaming.myapp.service.IFactureService;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IPersonneService;
import com.adaming.myapp.service.IReservationService;

public class PersonneServiceTest {
	
	private final Logger LOGGER = Logger.getLogger("ReservationServiceTestU");
    private static ClassPathXmlApplicationContext context;
	private static IPersonneService service;
	private static IReservationService  serviceB;
	private static IHotelService  serviceC;
	private static IChambreService  serviceD;
	private static IFactureService serviceE;
	
	private static IPersonneFactory factory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	  context= new ClassPathXmlApplicationContext("app.xml");
	  service = (IPersonneService) context.getBean("PersonneServiceImpl");
	  serviceB = (IReservationService) context.getBean("ReservationServiceImpl");
	  serviceC = (IHotelService) context.getBean("HotelServiceImpl");
	  serviceD = (IChambreService) context.getBean("ChambreServiceImpl");
	  serviceE = (IFactureService) context.getBean("FactureServiceImpl");
	  factory = new PersonneFactoryImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	  context.close();
	}
	
	
	@Test
	//@Ignore
	public void testCreate() throws NonValidClassTypeException{
		Adresse a = new Adresse("rue", 13127, "ville", "pays");
		Client c = (Client) factory.createPersonne("client", "nom", "prenom", new Date(), a);
		service.create(c);
		CDI e1 = (CDI) factory.createPersonne("CDI", "nom", "prenom", new Date(), a);
		CDD e2 = (CDD) factory.createPersonne("CDD", "nom", "prenom", new Date(), a);
		Stagiaire e3 = (Stagiaire) factory.createPersonne("Stagiaire", "nom", "prenom", new Date(), a);
		service.create(e1);
		service.create(e2);
		service.create(e3);
		assertNotNull(c.getIdPersonne());	
		assertNotNull(e1.getIdPersonne());	
		assertNotNull(e2.getIdPersonne());
		assertNotNull(e3.getIdPersonne());	
		}
	
	@Ignore
	@Test
	public void testGetOne(){
		Personne p = service.getOne(1L);
		assertTrue(p.getIdPersonne().equals(1L));
	}
	
	@Ignore
	@Test
	public void testGetAll() throws NullListException{
		List<Personne> personnes = service.getAll();
		assertNotNull(personnes.size());
	}
	
	@Ignore
	@Test
	public void testGetFactures() throws NullListException{
		Adresse a = new Adresse("rue", 13127, "ville", "pays");
		Reservation r=new Reservation(new Date(), new Date());
		Hotel h = new Hotel("nom", a, 1);
		Chambre c = new ChambreSimple(0, "description");
		Set<Chambre> chambres = new HashSet<Chambre>();
		chambres.add(c);
		serviceD.add(c);
		Personne p = new Client("nom", "prenom", new Date(), a);
		serviceC.save(h, chambres);
		service.create(p);
		serviceB.create(r, h.getId(), c.getId(), p.getIdPersonne());
		Facture f = new Facture();
		serviceE.create(f, h.getId());
		serviceE.remplirReservation(f.getId(), r.getId());
		Set<Facture> factures = service.getFacturesByClient(p.getIdPersonne());
		LOGGER.info(factures);
		assertNotNull(factures.size());
	}
	
	@Ignore
	@Test
	public void testUpdate(){
		CDI cdi = (CDI) service.getOne(10L);
		cdi.setSalaire(2000.0);
		service.update(cdi);
		assertTrue(cdi.getSalaire()==2000.0);
	}
	
	


}
