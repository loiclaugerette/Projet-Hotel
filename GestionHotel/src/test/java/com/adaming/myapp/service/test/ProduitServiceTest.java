/*
 * ProduitServiceTest
 * Version: 1.0.0
 * Date: 06-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IChambreService;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IProduitService;

public class ProduitServiceTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	private static IProduitService serviceProduit;
	private static IHotelService serviceHotel;
	private static IChambreService serviceChambre;
	
	//=========================
	// Before / After
	//=========================
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
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
		Produit produit = new Produit("nom", 1, 10.0, 15.0);
		Hotel hotel = new Hotel("hotelProduit", new Adresse("rue de la Production", 75000, "ParisIndustrielle", "Ouvrier"), 0);
		Set<Chambre> chambres = new HashSet<Chambre>();
		chambres.add(new ChambreSimple(1, "travail"));
		chambres.add(new ChambreDouble(2, "boulot"));
		for (Chambre chambre:chambres) {
			serviceChambre.add(chambre);
		}
		serviceHotel.save(hotel, chambres);
		serviceProduit.add(produit, hotel.getId());
		assertNotNull(produit.getIdProduit());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Produit> produits = new ArrayList<Produit>();
		try {
			produits = serviceProduit.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		Produit produit = serviceProduit.getOne(produits.get(0).getIdProduit());
		assertNotNull(produit);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Produit> produits = new ArrayList<Produit>();
		try {
			produits = serviceProduit.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		assertThat(produits.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Produit> produits = new ArrayList<Produit>();
		try {
			produits = serviceProduit.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		Produit produit = serviceProduit.getOne(produits.get(0).getIdProduit());
		produit.setNom("nouveau nom");
		serviceProduit.update(produit);
		assertThat(produit.getNom(), IsEqual.equalTo(serviceProduit.getOne(produits.get(0).getIdProduit()).getNom()));
	}

}
