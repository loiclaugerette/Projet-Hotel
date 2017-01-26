/*
 * ConsommationServiceTest
 * Version: 1.0.0
 * Date: 06-12-2016
 * Author: Etienne Lorteau
 */

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
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IChambreService;
import com.adaming.myapp.service.IConsommationService;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IPersonneService;
import com.adaming.myapp.service.IProduitService;

public class ConsommationServiceTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	private static IConsommationService serviceConsommation;
	private static IPersonneService servicePersonne;
	private static IProduitService serviceProduit;
	private static IHotelService serviceHotel;
	private static IChambreService serviceChambre;
	
	//=========================
	// Before / After
	//=========================
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceConsommation = (IConsommationService)context.getBean("ConsommationServiceImpl");
		servicePersonne = (IPersonneService)context.getBean("PersonneServiceImpl");
		serviceProduit = (IProduitService)context.getBean("ProduitServiceImpl");
		serviceHotel = (IHotelService)context.getBean("HotelServiceImpl");
		serviceChambre = (IChambreService)context.getBean("ChambreServiceImpl");
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
	public void testAdd() {
		Consommation consommation = new Consommation(1);
		Produit produit = new Produit("produitConsommation", 14, 20.0, 25.0);
		Hotel hotel = new Hotel("hotelConsommation", new Adresse("rue de la Consommation", 75000, "ParisMangeEtBoit", "PetitDejeuner"), 0);
		Set<Chambre> chambres = new HashSet<Chambre>();
		chambres.add(new ChambreSimple(1, "gourmet"));
		chambres.add(new ChambreDouble(2, "gourmand"));
		for (Chambre chambre:chambres) {
			serviceChambre.add(chambre);
		}
		serviceHotel.save(hotel, chambres);
		serviceProduit.add(produit, hotel.getId());
		Personne client = new Client("nomClient", "prenomClient", new Date(), new Adresse("rue de la Consommation", 75000, "Paris", "France"));
		servicePersonne.create(client);
		serviceConsommation.add(consommation, client.getIdPersonne(), produit.getIdProduit());
		assertNotNull(consommation.getId());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Consommation> consommations = new ArrayList<Consommation>();
		try {
			consommations = serviceConsommation.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		Consommation consommation = serviceConsommation.getOne(consommations.get(0).getId());
		assertNotNull(consommation);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Consommation> consommations = new ArrayList<Consommation>();
		try {
			consommations = serviceConsommation.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		assertThat(consommations.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Consommation> consommations = new ArrayList<Consommation>();
		try {
			consommations = serviceConsommation.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		Consommation consommation = serviceConsommation.getOne(consommations.get(0).getId());
		consommation.setQuantite(2);;
		serviceConsommation.update(consommation);
		assertThat(consommation.getQuantite(), IsEqual.equalTo(serviceConsommation.getOne(consommations.get(0).getId()).getQuantite()));
	}

}
