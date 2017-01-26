/*
 * PaiementBean
 * Version: 1.0.0
 * Date: 08-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.bean;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.abstractfactory.IPaiementFactory;
import com.adaming.myapp.abstractfactory.PaiementFactoryImpl;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Consommation;
import com.adaming.myapp.entities.Facture;
import com.adaming.myapp.entities.Hotel;
import com.adaming.myapp.entities.PaiementCb;
import com.adaming.myapp.entities.PaiementCheque;
import com.adaming.myapp.entities.PaiementEspece;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.entities.Reservation;
import com.adaming.myapp.exceptions.NullListException;
import com.adaming.myapp.service.IFactureService;
import com.adaming.myapp.service.IHotelService;
import com.adaming.myapp.service.IPaiementService;
import com.adaming.myapp.service.IPersonneService;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

@Component("paiementBean")
@ViewScoped
public class PaiementBean {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("PaiementBean");
	
	private IPaiementFactory paiementFactory;
	
	@Inject
	private IPaiementService servicePaiement;
	
	@Inject
	private IHotelService serviceHotel;
	
	@Inject
	private IPersonneService servicePersonne;
	
	@Inject
	private IFactureService serviceFacture;
	
	// Hotel
	private List<Hotel> hotels;
	private Long idHotel;
	
	// Client
	private List<Client> clients;
	private Long idClient;
	
	// Facture
	private List<Facture> factures;
	private Long idFacture;
	private HashMap<String, Long> refFacture;
	private Facture selectedFacture;
	private String nomClient;
	private String prenomClient;
	private Date dateEntree;
	private Date dateSortie;
	private Double montantFacture;
	
	// Paiement
	private Long numCarte;
	private String typeCarte;
	private Long numCheque;
	private String banqueCheque;
	private String devise;
	
	//=========================
	// Constructor / Service Setter
	//=========================
	
	public PaiementBean() {
		paiementFactory = new PaiementFactoryImpl();
		idHotel = 0L;
		idClient = 0L;
		idFacture = 0L;
		initFields();
		LOGGER.info("<=============== PaiementBean initialized ===============>");
	}

	//=========================
	// Methods
	//=========================
	
	public String redirect() {
		idHotel = 0L;
		idClient = 0L;
		idFacture = 0L;
		initFields();
		initList();
		return "paiement?faces-redirect=true";
	}
	
	@PostConstruct
	public void initList() {
		hotels = serviceHotel.getHotels();
		clients = new ArrayList<Client>();
		try {
			List<Personne> personnes = servicePersonne.getAll();
			for (Personne personne:personnes) {
				if (personne.getClass() == Client.class) {
					clients.add((Client)personne);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			factures = serviceFacture.getAll();
		} catch (NullListException e) {
			factures = new ArrayList<Facture>();
			e.printStackTrace();
		}
		verifyFactures();
		initRefFacture();
		LOGGER.info("<=============== Paiement Lists initialized ===============>");
	}
	
	public void initFields() {
		numCarte = null;
		typeCarte = null;
		numCheque = null;
		banqueCheque = null;
		devise = null;
		LOGGER.info("<=============== Paiement Fields initialized ===============>");
	}
	
	private void verifyFactures() {
		List<Facture> facTmp = factures;
		factures = new ArrayList<Facture>();
		if (facTmp.size() != 0)
		for (Facture fac:facTmp) {
			if (fac.getPaiement() == null) {
				factures.add(fac);
			}
		}
	}
	
	private void initRefFacture() {
		refFacture = new HashMap<String, Long>();
		for (Facture facture:factures) {
			try {
				Set<Reservation> reservSet = facture.getReservations();
				List<Reservation> reserv = new ArrayList<Reservation>();
				if (reservSet.size() != 0) {
					for (Reservation res:reservSet) {
						reserv.add(res);
					}
				}
				refFacture.put(reserv.get(0).getDateArrivee().toString(), facture.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void selectHotel() {
		idClient = 0L;
		idFacture = 0L;
		clients = new ArrayList<Client>();
		try {
			Set<Client> clientSet = serviceHotel.getClients(idHotel);
			for (Client cli:clientSet) {
				clients.add(cli);
			}
		} catch (Exception e) {
			LOGGER.info("No clients in this hotel");
		}
		factures = new ArrayList<Facture>();
		for (Client client:clients) {
			Set<Facture> factureClient = new HashSet<Facture>();
			try {
				factureClient = servicePersonne.getFacturesByClient(client.getIdPersonne());
			} catch (NullListException e) {
				e.printStackTrace();
			}
			if (factureClient.size() != 0) {
				for (Facture facClient:factureClient) {
					factures.add(facClient);
				}
			}
		}
		verifyFactures();
		initRefFacture();
		LOGGER.info("<=============== Hotel Selected for Paiement ===============>");
	}
	
	public void selectClient() {
		idFacture = 0L;
		factures = new ArrayList<Facture>();
		try {
			Set<Facture> facturesTmp = servicePersonne.getFacturesByClient(idClient);
			for (Facture facTmp:facturesTmp) {
				factures.add(facTmp);
			}
		} catch (Exception e) {
			LOGGER.info("No Facture for this client");
		}
		verifyFactures();
		initRefFacture();
		LOGGER.info("<=============== Client Selected for Paiement ===============>");
	}
	
	public void selectFacture() {
		selectedFacture = serviceFacture.imprimer(idFacture);
		try {
			Set<Reservation> reservSet = selectedFacture.getReservations();
			List<Reservation> reserv = new ArrayList<Reservation>();
			if (reservSet.size() != 0) {
				for (Reservation res:reservSet) {
					reserv.add(res);
				}
			}
			nomClient = reserv.get(0).getPersonne().getNom();
			prenomClient = reserv.get(0).getPersonne().getPrenom();
			dateEntree = reserv.get(0).getDateArrivee();
			dateSortie = reserv.get(0).getDateSortie();
			montantFacture = selectedFacture.getCoutConsommation() + selectedFacture.getCoutReservation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("<=============== Facture Selected for Paiement ===============>");
	}
	
	public void addPaiementCb() {
		try {
			PaiementCb paiementCb = (PaiementCb)paiementFactory.createPaiement("PaiementCb", new Date());
			paiementCb.setCoutTotal(montantFacture);
			paiementCb.setNumCarte(numCarte);
			paiementCb.setTypeCarte(typeCarte);
			servicePaiement.add(paiementCb, selectedFacture.getId());
			LOGGER.info("<=============== Paiement CB done ===============>");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le paiement par CB a bien été effectué."));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("<=============== Paiement failed ===============>");
		}
	}
	
	public void addPaiementCheque() {
		try {
			PaiementCheque paiementCheque = (PaiementCheque)paiementFactory.createPaiement("PaiementCheque", new Date());
			paiementCheque.setCoutTotal(montantFacture);
			paiementCheque.setNumCheque(numCheque);
			paiementCheque.setBanqueCheque(banqueCheque);
			servicePaiement.add(paiementCheque, selectedFacture.getId());
			LOGGER.info("<=============== Paiement Cheque done ===============>");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le paiement par chèque a bien été effectué."));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("<=============== Paiement failed ===============>");
		}
	}
	
	public void addPaiementEspece() {
		try {
			PaiementEspece paiementEspece = (PaiementEspece)paiementFactory.createPaiement("PaiementEspece", new Date());
			paiementEspece.setCoutTotal(montantFacture);
			paiementEspece.setDevise(devise);
			servicePaiement.add(paiementEspece, selectedFacture.getId());
			LOGGER.info("<=============== Paiement Espece done ===============>");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le paiement par espèces a bien été effectué."));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("<=============== Paiement failed ===============>");
		}
	}
	
	public void generatePdf() {

		// etape 1
		Document document = new Document(PageSize.A4);
	        
		try {
	            // etape 2:
	            // creation du writer -> PDF ou HTML 
	            PdfWriter.getInstance(document, new FileOutputStream("facture.pdf"));
	                      	
	            // etape 3: Ouverture du document
	            document.open();
	           
	            // etape 4: Ajout du contenu au document
	            Hotel h = serviceHotel.getOne(idHotel);
	            Personne c = servicePersonne.getOne(idClient);
	            document.add(new Phrase("Hotel : " + h.getNom() + "\n"));
	            document.add(new Phrase(h.getAdresse().getRue() + "\n"));
	            document.add(new Phrase(h.getAdresse().getCodePostal() + " " + h.getAdresse().getVille() + "\n"));
	            document.add(new Phrase(h.getAdresse().getPays() + "\n"));
	            document.add(new Phrase("\n"));
	            document.add(new Phrase("Facture du " + dateEntree + " au " + dateSortie + " : \n"));
	            document.add(new Phrase("\n"));
	            document.add(new Phrase("M./Mme. " + nomClient + " " + prenomClient + "\n"));
	            try {
		            document.add(new Phrase(c.getAdresse().getRue() + " " + c.getAdresse().getCodePostal() + " " + 
		            		c.getAdresse().getVille() + " " + c.getAdresse().getPays() + "\n"));
				} catch (Exception e) {
					LOGGER.info("Client not loaded");
				}
	            document.add(new Phrase("\n"));
				Set<Reservation> reservSet = selectedFacture.getReservations();
				List<Reservation> reserv = new ArrayList<Reservation>();
				if (reservSet.size() != 0) {
					for (Reservation res:reservSet) {
						reserv.add(res);
					}
				}
	            if (reserv.size() != 0) {
		            document.add(new Phrase("Reservations : \n"));
		            for (Reservation res:selectedFacture.getReservations()) {
			            document.add(new Phrase("        - " + res.getChambre().getClass().getSimpleName() + "            " + res.getChambre().getPrix() + "euros\n"));
		            }
	            }
	            document.add(new Phrase("\n"));
				Set<Consommation> consoSet = selectedFacture.getConsommations();
				List<Consommation> conso = new ArrayList<Consommation>();
				if (consoSet.size() != 0) {
					for (Consommation con:consoSet) {
						conso.add(con);
					}
				}
	            if (conso.size() != 0) {
		            document.add(new Phrase("Consommations : \n"));
		            for (Consommation cons:selectedFacture.getConsommations()) {
			            document.add(new Phrase("        - " + cons.getProduit().getNom() + "        " + cons.getQuantite() + " x " + cons.getProduit().getCoutVente() + "euros\n"));
		            }
	            }
	            document.add(new Phrase("\n"));
	            document.add(new Phrase("TOTAL : " + (selectedFacture.getCoutReservation() + selectedFacture.getCoutConsommation()) + "euros\n"));
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        // etape 5: Fermeture du document
	        document.close();
	        LOGGER.info("<=============== Document 'facture.pdf' generated ===============>");
	}
	
	//=========================
	// Getter / Setter
	//=========================
	
	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public List<Facture> getFactures() {
		return factures;
	}

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	public Long getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(Long idFacture) {
		this.idFacture = idFacture;
	}

	public HashMap<String, Long> getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(HashMap<String, Long> refFacture) {
		this.refFacture = refFacture;
	}

	public Facture getSelectedFacture() {
		return selectedFacture;
	}

	public void setSelectedFacture(Facture selectedFacture) {
		this.selectedFacture = selectedFacture;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Double getMontantFacture() {
		return montantFacture;
	}

	public void setMontantFacture(Double montantFacture) {
		this.montantFacture = montantFacture;
	}

	public Long getNumCarte() {
		return numCarte;
	}

	public void setNumCarte(Long numCarte) {
		this.numCarte = numCarte;
	}

	public String getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(String typeCarte) {
		this.typeCarte = typeCarte;
	}

	public Long getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(Long numCheque) {
		this.numCheque = numCheque;
	}

	public String getBanqueCheque() {
		return banqueCheque;
	}

	public void setBanqueCheque(String banqueCheque) {
		this.banqueCheque = banqueCheque;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

}
