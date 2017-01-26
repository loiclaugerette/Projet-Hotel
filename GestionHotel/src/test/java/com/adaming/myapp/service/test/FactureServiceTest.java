package com.adaming.myapp.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.ChambreDouble;
import com.adaming.myapp.entities.ChambreSimple;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Consommation;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.entities.Suite;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IChambreService;
import com.adaming.myapp.service.IConsommationService;
import com.adaming.myapp.service.IFactureService;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IPersonneService;
import com.adaming.myapp.service.IProduitService;
import com.adaming.myapp.service.IReservationService;

public class FactureServiceTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	private static IFactureService serviceFacture;
	private static IHotelService serviceHotel;
	private static IChambreService serviceChambre;
	private static IReservationService serviceReservation;
	private static IConsommationService serviceConsommation;
	private static IPersonneService servicePersonne;
	private static IProduitService serviceProduit;
	
	//=========================
	// Before / After
	//=========================
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceFacture = (IFactureService)context.getBean("FactureServiceImpl");
		serviceHotel = (IHotelService)context.getBean("HotelServiceImpl");
		serviceChambre = (IChambreService)context.getBean("ChambreServiceImpl");
		serviceReservation = (IReservationService)context.getBean("ReservationServiceImpl");
		serviceConsommation = (IConsommationService)context.getBean("ConsommationServiceImpl");
		servicePersonne = (IPersonneService)context.getBean("PersonneServiceImpl");
		serviceProduit = (IProduitService)context.getBean("ProduitServiceImpl");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	//=========================
	// Tests
	//=========================

	@Test
	@Ignore
	public void testCreate() {
		Facture facture = new Facture();
		Hotel hotel = new Hotel("hotelFacture", new Adresse("rue de la Facture", 75000, "ParisCouteCher", "Dette"), 9);
		Set<Chambre> chambres = new HashSet<Chambre>();
		chambres.add(new ChambreSimple(1, "a"));
		chambres.add(new ChambreDouble(2, "b"));
		chambres.add(new ChambreDouble(3, "c"));
		chambres.add(new Suite(4, "d"));
		for (Chambre chambre:chambres) {
			serviceChambre.add(chambre);
		}
		serviceHotel.save(hotel, chambres);
		serviceFacture.create(facture, hotel.getId());
		assertNotNull(facture.getId());
	}

	@Test
	@Ignore
	public void testRemplirReservation() {
		Reservation reservation = new Reservation(new Date(), new Date());
		Hotel hotel = new Hotel("hotelFacture", new Adresse("rue de la Facture", 75000, "ParisCouteCher", "Dette"), 9);
		Set<Chambre> chambres = new HashSet<Chambre>();
		Chambre ch = new ChambreSimple(1, "a");
		chambres.add(ch);
		chambres.add(new ChambreDouble(2, "b"));
		chambres.add(new ChambreDouble(3, "c"));
		chambres.add(new Suite(4, "d"));
		for (Chambre chambre:chambres) {
			serviceChambre.add(chambre);
		}
		serviceHotel.save(hotel, chambres);
		Personne client = new Client("nomClient", "prenomClient", new Date(), new Adresse("rue de la Consommation", 75000, "Paris", "France"));
		servicePersonne.create(client);
		serviceReservation.create(reservation, hotel.getId(), ch.getId(), client.getIdPersonne());
		try {
			Facture facture = serviceFacture.getAll().get(0);
			Double cout = facture.getCoutReservation();
			serviceFacture.remplirReservation(facture.getId(), reservation.getId());
			assertThat(cout + ch.getPrix(), IsEqual.equalTo(serviceFacture.getAll().get(0).getCoutReservation()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	@Ignore
	public void testRemplirConsommation() {
		Consommation consommation = new Consommation(1);
		Produit produit = new Produit("produitConsommation", 14, 20.0, 25.0);
		Hotel hotel = new Hotel("hotelFacture", new Adresse("rue de la Facture", 75000, "ParisCouteCher", "Dette"), 9);
		Set<Chambre> chambres = new HashSet<Chambre>();
		chambres.add(new ChambreSimple(1, "a"));
		chambres.add(new ChambreDouble(2, "b"));
		for (Chambre chambre:chambres) {
			serviceChambre.add(chambre);
		}
		serviceHotel.save(hotel, chambres);
		serviceProduit.add(produit, hotel.getId());
		Personne client = new Client("nomClient", "prenomClient", new Date(), new Adresse("rue de la Consommation", 75000, "Paris", "France"));
		servicePersonne.create(client);
		serviceConsommation.add(consommation, client.getIdPersonne(), produit.getIdProduit());
		try {
			Facture facture = serviceFacture.create(new Facture(), hotel.getId());
			Double cout = facture.getCoutConsommation();
			serviceFacture.remplirConsommation(facture.getId(), consommation.getId());
			assertThat(cout + produit.getCoutVente(), IsEqual.equalTo(serviceFacture.imprimer(facture.getId()).getCoutConsommation()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	@Ignore
	public void testImprimer() {
		List<Facture> factures = new ArrayList<Facture>();
		try {
			factures = serviceFacture.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		Facture facture = serviceFacture.imprimer(factures.get(0).getId());
		assertNotNull(facture);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Facture> factures = new ArrayList<Facture>();
		try {
			factures = serviceFacture.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		assertThat(factures.isEmpty(), IsEqual.equalTo(false));
	}

}
