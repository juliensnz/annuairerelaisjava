package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import exceptions.RelaisException;

public class Relais implements Comparable<Relais> {

	private String			nom;
	private int				positionX;
	private int				positionY;
	private static int		id			= 0;
	private List<Service>	services	= new ArrayList<Service>();

	/**
	 * 	Constructeur de base initialisant un relais avec des valeurs par dÈfaut.
	 */
	public Relais() {
		this.positionX = 0;
		this.positionY = 0;
		this.nom = "";
		Relais.id++;
	}

	/**
	 * 	Constructeur avec paramËtre : position et nom (avec contrÙle de saisie)
	 */
	public Relais(int x, int y, String nom) throws RelaisException {
		if (x < 0 || y < 0)
			throw new RelaisException("La position d'un relais ne peut Íêtre nÈégative !");
		else if (nom.isEmpty())
			throw new RelaisException("Le nom d'un relais ne peut pas Íêtre vide !");
		else {
			this.positionX = x;
			this.positionY = y;
			this.nom = nom;
			Relais.id++;
		}
	}

	/**
	 * 	Ajouter un service de nom nomService au relais (verification de non-existence)
	 */
	public void ajouterService(String nomService) throws RelaisException {
		boolean contient = false;
		if (!nomService.isEmpty()) {
			Service nouvService = new Service(nomService);
			for (Service s : this.services)
				if (s.equals(nouvService)) {
					contient = true;
					throw new RelaisException("Le service existe déÈj‡à");
				}
			if (!contient)
				this.services.add(nouvService);
		}
		else
			throw new RelaisException("Le nom du service est vide");
	}

	/**
	 * 	Ajout d'une liste de service au relais.
	 */
	public void ajouterService(List<String> l) throws RelaisException {
		for (String s : l)
			this.ajouterService(s);
	}

	/**
	 * 	Retire tous les services s du relais
	 */
	public void retirerService(Service s) {
		for (Service si : this.services)
			if (si.getNom().equals(s.getNom())) {
				this.services.remove(si);
				return;
			}
	}

	/**
	 * 	Recherche si un service porte le nom "nom" dans la liste du relais
	 */
	public boolean contientService(String nom) {
		for (Service s : this.services)
			if (s.getNom().equals(nom))
				return true;
		return false;
	}

	/**
	 * 	Calcul la distance euclidienne entre le relais et la position entrÈe en paramËtre
	 */
	public double distance(int x, int y) {
		return Math.round(Math.sqrt(Math.pow((this.positionX - x), 2) + Math.pow((this.positionY - y), 2)) * 100) / 100;
	}

	/**
	 * 	MÈthode pour gÈnÈrer alÈatoirement un relais.
	 */
	public static Relais genererRelais() {
		Relais nouveauRelais = null;
		int x = (int) Math.round(Math.random() * 100);
		int y = (int) Math.round(Math.random() * 100);
		String nom = "Relais n° " + Relais.getId();
		try {
			nouveauRelais = new Relais(x, y, nom);
		}
		catch (RelaisException e) {
			nouveauRelais = new Relais();
		}
		return nouveauRelais;
	}

	/**
	 * 	GÈnÈrer un nombre donnÈ (nbRelais) de relais. 
	 */
	public static List<Relais> genererRelais(int nbRelais) {
		List<Relais> l = new ArrayList<Relais>();
		for (int i = 0; i < nbRelais; i++)
			l.add(Relais.genererRelais());
		return l;
	}

	/**
	 * 	Comparaison de deux relais
	 */
	public int compareTo(Relais o) {
		return this.getNom().compareTo(o.getNom());
	}

	/**
	 * 	Teste l'ÈgalitÈ avec le relais entrÈ en paramËtre.
	 */
	public boolean equals(Relais o) {
		return this.positionX == o.positionX && this.positionY == o.positionY && this.equivalentService(o);
	}// Si deux relais ont la même position et les même services, alors ce sont

	/**
	 * 	Teste l'Èquivalence des services de deux relais.
	 */
	public boolean equivalentService(Relais r) {
		Collections.sort(this.services);
		Collections.sort(r.services);
		return this.services.equals(r.services);
	}// Compare les deux liste de services avec la méthode equals.

	/**
	 * Getter sur l'id du Relais
	 */
	public static int getId() {
		return Relais.id;
	}

	/**
	 * 	Getter sur le nom du relais
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * 	Getter su la positionX du relais
	 */
	public int getX() {
		return this.positionX;
	}

	/**
	 * 	Getter sur la positionY du relais
	 */
	public int getY() {
		return this.positionY;
	}

	/**
	 * 	Retourne le nombre de services disponnible dans le relais
	 */
	public int getNbService() {
		return this.services.size();
	}

	/**
	 * 	Retourne le service par index
	 */
	public Service getServices(int i) {
		return this.services.get(i);
	}
	
	/**
	 * 	Retourne le service ayant le mÍme nom "nom"
	 */
	public Service getServices(String nom) {
		for (Service s : this.services)
			if (s.getNom().equals(nom))
				return s;
		return null;
	}

	/**
	 * 	Retourne la liste des services du relais
	 */
	public List<Service> getServices() {
		return this.services;
	}

	/**
	 * 	Setter sur le nom du relais
	 */
	public void setNom(String nom) throws RelaisException {
		if (nom.isEmpty())
			throw new RelaisException("Le nom d'un relais ne peux pas êÍtre vide");
		else
			this.nom = nom;
	}

	/**
	 * 	Setter sur la position du relais.
	 */
	public void setPosition(int x, int y) throws RelaisException {
		if (x <= 0 || y <= 0)
			throw new RelaisException("La position d'un relais ne peut Íêtre néÈgative !");
		else {
			this.positionX = x;
			this.positionY = y;
		}
	}
}
