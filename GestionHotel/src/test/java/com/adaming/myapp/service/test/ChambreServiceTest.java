/*
 * ChambreServiceTest
 * Version: 1.0.0
 * Date: 06-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.abstractfactory.ChambreFactoryImpl;
import com.adaming.myapp.abstractfactory.IChambreFactory;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.ChambreDouble;
import com.adaming.myapp.entities.ChambreSimple;
import com.adaming.myapp.entities.Suite;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IChambreService;

public class ChambreServiceTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	private static IChambreFactory chambreFactory;
	private static IChambreService serviceChambre;
	
	//=========================
	// Before / After
	//=========================
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		chambreFactory = new ChambreFactoryImpl();
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
	//@Ignore
	public void testAdd() {
		try {
			ChambreSimple chambreSimple = (ChambreSimple)chambreFactory.createChambre("ChambreSimple", 11, "vue sur la Tour Eiffel");
			ChambreDouble chambreDouble = (ChambreDouble)chambreFactory.createChambre("ChambreDouble", 22, "rats a l'hygiene douteuse");
			Suite suite = (Suite)chambreFactory.createChambre("Suite", 7, "taches de cafe sur la moquette");
			serviceChambre.add(chambreSimple);
			serviceChambre.add(chambreDouble);
			serviceChambre.add(suite);
			assertNotNull(chambreSimple.getId());
			assertNotNull(chambreDouble.getId());
			assertNotNull(suite.getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Chambre> chambres = new ArrayList<Chambre>();
		try {
			chambres = serviceChambre.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		Chambre chambre = serviceChambre.getOne(chambres.get(0).getId());
		assertNotNull(chambre);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Chambre> chambres = new ArrayList<Chambre>();
		try {
			chambres = serviceChambre.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		assertThat(chambres.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Chambre> chambres = new ArrayList<Chambre>();
		try {
			chambres = serviceChambre.getAll();
		} catch (NullListException e) {
			e.printStackTrace();
		}
		Chambre chambre = serviceChambre.getOne(chambres.get(0).getId());
		chambre.setDescription("nouvelle description");
		serviceChambre.update(chambre);
		assertThat(chambre.getDescription(), IsEqual.equalTo(serviceChambre.getOne(chambres.get(0).getId()).getDescription()));
	}

}
