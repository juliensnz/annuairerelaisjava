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
	 * 	Constructeur de base initialisant un relais avec des valeurs par d�faut.
	 */
	public Relais() {
		this.positionX = 0;
		this.positionY = 0;
		this.nom = "";
		Relais.id++;
	}

	/**
	 * 	Constructeur avec param�tre : position et nom (avec contr�le de saisie)
	 */
	public Relais(int x, int y, String nom) throws RelaisException {
		if (x < 0 || y < 0)
			throw new RelaisException("La position d'un relais ne peut �tre n�gative !");
		else if (nom.isEmpty())
			throw new RelaisException("Le nom d'un relais ne peut pas �tre vide !");
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
					throw new RelaisException("Le service existe d��j��");
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
	 * 	Calcul la distance euclidienne entre le relais et la position entr�e en param�tre
	 */
	public double distance(int x, int y) {
		return Math.round(Math.sqrt(Math.pow((this.positionX - x), 2) + Math.pow((this.positionY - y), 2)) * 100) / 100;
	}

	/**
	 * 	M�thode pour g�n�rer al�atoirement un relais.
	 */
	public static Relais genererRelais() {
		Relais nouveauRelais = null;
		int x = (int) Math.round(Math.random() * 100);
		int y = (int) Math.round(Math.random() * 100);
		String nom = "Relais n� " + Relais.getId();
		try {
			nouveauRelais = new Relais(x, y, nom);
		}
		catch (RelaisException e) {
			nouveauRelais = new Relais();
		}
		return nouveauRelais;
	}

	/**
	 * 	G�n�rer un nombre donn� (nbRelais) de relais. 
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
	 * 	Teste l'�galit� avec le relais entr� en param�tre.
	 */
	public boolean equals(Relais o) {
		return this.positionX == o.positionX && this.positionY == o.positionY && this.equivalentService(o);
	}// Si deux relais ont la m�me position et les m�me services, alors ce sont

	/**
	 * 	Teste l'�quivalence des services de deux relais.
	 */
	public boolean equivalentService(Relais r) {
		Collections.sort(this.services);
		Collections.sort(r.services);
		return this.services.equals(r.services);
	}// Compare les deux liste de services avec la m�thode equals.

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
	 * 	Retourne le service ayant le m�me nom "nom"
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
			throw new RelaisException("Le nom d'un relais ne peux pas ��tre vide");
		else
			this.nom = nom;
	}

	/**
	 * 	Setter sur la position du relais.
	 */
	public void setPosition(int x, int y) throws RelaisException {
		if (x <= 0 || y <= 0)
			throw new RelaisException("La position d'un relais ne peut �tre n��gative !");
		else {
			this.positionX = x;
			this.positionY = y;
		}
	}
}
