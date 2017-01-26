package com.adaming.myapp.service.test;

/* Hotel
 *Version: 1.0.0
 *Date: 06-12-2016
 *Author: Brice Touchard
 */

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Chambre;
import com.adaming.myapp.entities.ChambreSimple;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Employe;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Produit;
import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.service.IChambreService;
import com.adaming.myapp.service.IFactureService;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IPersonneService;
import com.adaming.myapp.service.IProduitService;
import com.adaming.myapp.service.IReservationService;

public class HotelServiceTest {

	 private final Logger LOGGER = Logger.getLogger("HotelServiceTest");
		
	 private static ClassPathXmlApplicationContext context;
		
	 private static IHotelService service;
	 private static IChambreService serviceChambre;
	 private static IPersonneService servicePersonne;
	 private static IReservationService serviceReservation;
	 private static IFactureService serviceFacture;
	 private static IProduitService serviceProduit;
	 

		@BeforeClass 
		public static void setUpBeforeClass() {
		  context= new ClassPathXmlApplicationContext("app.xml");
		  service = (IHotelService) context.getBean("HotelServiceImpl");
		  serviceChambre = (IChambreService) context.getBean("ChambreServiceImpl");
		  servicePersonne =  (IPersonneService) context.getBean("PersonneServiceImpl");
		  serviceReservation =  (IReservationService) context.getBean("ReservationServiceImpl");
		  serviceFacture =  (IFactureService) context.getBean("FactureServiceImpl");
		  serviceProduit =  (IProduitService) context.getBean("ProduitServiceImpl");
		}

		@AfterClass
		public static void tearDownAfterClass() {
		  context.close();
		}
		
		@Test
		@Ignore
		public void testSave() {
			Adresse a1 = new Adresse("1 rue Jean Jaures",75100,"Paris","France");
			LOGGER.info("Instanciation adresse effectuée");
			
			Hotel h1 = new Hotel("Le palace",a1,1);
			LOGGER.info("Instanciation hotel effectuée");
			
			Chambre ch1 = new ChambreSimple(1,"La chambre de rêve");
			LOGGER.info("Instanciation chambre effectuée");
			Set<Chambre> chambres = new HashSet<Chambre>();
			chambres.add(ch1);
			LOGGER.info("Chambre ajoutée à la liste");
			serviceChambre.add(ch1);
			LOGGER.info("Chambre saved !");

			h1.setChambres(chambres);
			LOGGER.info("Liste ajoutée à hotel");
		
			service.save(h1,h1.getChambres());
			LOGGER.info("Hotel saved !");
			
			//assertNotNull(h1.getId());
		}
		
		@Test
		@Ignore
		public void testGetOne(){
			Hotel h  = service.getOne(1L);
			LOGGER.info("Hotel gotten!");
			assertTrue(h != null);
			LOGGER.info("Assertion OK");
		}
		
		@Test
		@Ignore
		public void testGetHotels(){
			List<Hotel> hotels = service.getHotels();
			LOGGER.info("Hotels gotten!");
			assertTrue(hotels.size()>0);
			LOGGER.info("Assertion OK");
		}
		
		@Test
		@Ignore
		public void testUpdate(){
			Hotel h1 = service.getOne(1L);
			h1.setNom("Nouveau nom updated trop stylé");
			service.update(h1);
			Hotel h2 = service.getOne(1L);
			Assert.assertThat("Nouveau nom updated trop stylé", IsEqual.equalTo(h2.getNom()));

		}
		
		@Test
		@Ignore
		public void testAddPersonne(){
			Adresse a1 = new Adresse("3 rue Jean Jaures",75100,"Paris","France");
			Adresse a2 = new Adresse("5 rue Jean Jaures",75100,"Paris","France");
			LOGGER.info("Instanciation adresses effectuée");
			
			Personne p1 = new Client("Martin","Robert",new Date(),a1);
			LOGGER.info("Instanciation personne effectuée");
			servicePersonne.create(p1);
			LOGGER.info("Personne saved !");
		
			service.addPersonne(1L, 1L); //(idHotel,idPersonne)
		}
		
		@Test
		@Ignore
		public void testAddReservation(){
			Date DateArrivee = new Date();
			Date DateSortie = new Date();
			
			Reservation r1 = new Reservation(DateArrivee, DateSortie);
			LOGGER.info("Instanciation Reservation effectuée");
			
			serviceReservation.create(r1, 1L, 1L, 1L); //idHotel, idChambre, idPersonne
			LOGGER.info("Reservation saved !");
			
			service.addReservation(1L, 1L); //(idHotel,idRservation)
			LOGGER.info("Reservation added to Hotel (1L)!");
		}
		
		@Test
		@Ignore
		public void testAddFacture(){
			Facture f1 = new Facture();
			LOGGER.info("Instanciation facture effectuée");
			
			serviceFacture.create(f1, 1L);
			LOGGER.info("Facture saved !");
			
			service.addFacture(1L, 1L);
			LOGGER.info("Facture added to Hotel (1L)!");
		}
		
		@Test
		@Ignore
		public void testAddProduit(){
			Produit p1 = new Produit("Bouteille de vin",1,15.0,30.0);
			LOGGER.info("Instanciation Produit effectuée");
			
			serviceProduit.add(p1,1L);
			LOGGER.info("Produit saved !");
		
			service.addProduit(1L,1L);
			LOGGER.info("Produit added to Hotel (1L)!");
		}
		
		@Test
		@Ignore
		public void testGetEmployes(){
			service.getEmployes(1L);
			LOGGER.info("Employes gotten");
		}
		
		@Test
		@Ignore
		public void testGetClients(){
			service.getClients(1L);
			LOGGER.info("Clients gotten");
		}
		
		@Test
		@Ignore
		public void testBeneficeAnnee() {
			Hotel h1 = service.getOne(12L);
			LOGGER.info("Hotel 12L gotten");
			Double Benef = service.beneficeAnnee(h1);
			LOGGER.info("Benefice calculé !"+ Benef);
		}
		
		@Test 
		@Ignore
		public void testGetProduits(){
			Set<Produit> produits = service.getProduits(2L);
			LOGGER.info("Produits de l'hotel 2L gotten : "+produits);		
		}
		
		@Test  
		//@Ignore
		public void testListeChambreDispos() throws ParseException{
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			
			Date dateReserved1 = new Date();
			Date dateReserved2 = new Date();
			dateReserved1=sf.parse("12-12-2016");
			dateReserved2=sf.parse("25-12-2016");
					
			Date dateSouhaitee1 = new Date();
			Date dateSouhaitee2 = new Date();
			dateSouhaitee1=sf.parse("10-12-2016");
			dateSouhaitee2=sf.parse("14-12-2016");
			
			Reservation r = new Reservation(dateReserved1,dateReserved2);
			serviceReservation.create(r, 1L, 1L, 1L);
			Set <Chambre> chambresDispos = service.getChambreDisponible(1L, dateSouhaitee1, dateSouhaitee2);
			LOGGER.info("Les chambres dispos sont : ");
			for(Chambre c:chambresDispos){
				System.out.println(c.getDescription());
			}
		}
		
		@Test  
		@Ignore
		public void testBeneficeSemestre(){
			//TODOOOO (méthode pas faite)
		}
}
