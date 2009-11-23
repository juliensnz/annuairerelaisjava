package app;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import exceptions.RelaisException;

public class Relais implements Comparable<Relais>{
	private String nom;
	private int positionX;
	private int positionY;
	private static int id = 0;
	private List<Service> services = new LinkedList<Service>();

	public Relais() {
		this.positionX = 0;
		this.positionY = 0;
		this.nom = "";
		Relais.id++;
	}// Constructeur de base.

	public Relais(int x, int y, String nom) throws RelaisException {
		if (x < 0 || y < 0)
			throw new RelaisException("La position d'un relais ne peut être négative !");
		else if (nom.isEmpty())
			throw new RelaisException("Le nom d'un relais ne peut pas être vide !");
		else {
			this.positionX = x;
			this.positionY = y;
			this.nom = nom;
			Relais.id++;
		}
	}// Constructeur paramétré, deux contrôles de saisie.

	public void setPosition(int x, int y) throws RelaisException {
		if (x <= 0 || y <= 0)
			throw new RelaisException("La position d'un relais ne peut être négative !");
		else {
			this.positionX = x;
			this.positionY = y;
		}
	}// Changer la position d'un relais.

	public void setNom(String nom) throws RelaisException {
		if (nom.isEmpty())
			throw new RelaisException("Le nom d'un relais ne peux pas être vide");
		else
			this.nom = nom;
	}// Changer le nom d'un relais.

	public void ajouterService(String nomService) throws RelaisException {
		Service nouvService = new Service(nomService);
		boolean contient = false;
		if (!(nomService.isEmpty())) {
			for (Service s : this.services) {
				if (s.getNom().equals(nouvService.getNom())) {
					contient = true;
					throw new RelaisException("Le service existe déjà");
				}
			}
			if (!(contient)) {
				this.services.add(nouvService);
				Collections.sort(this.services);
			}
		} else
			throw new RelaisException("Le nom du service est vide");
	}// Ajoute un le service nomService au relais, s'il n'existe pas déjà et si le nom est non-vide.

	public void ajouterService(List<String> l) throws RelaisException {
		for (String s : l)
			ajouterService(s);
		Collections.sort(this.services);
	}// Ajouter une liste de services.

	public void retirerService(Service s) {
		for (Service si : this.services) {
			if (si.getNom().equals(s.getNom())) {
				this.services.remove(si);
				Collections.sort(this.services);
				return;
			}
		}
	}// Retire un service d'un relais (éventuellement plusieurs).

	public double distance(int x, int y) {
		return Math.round(Math.sqrt(Math.pow((this.positionX - x), 2) + Math.pow((this.positionY - y), 2)) * 100) / 100;
	}// Renvoie la distance euclidienne entre deux relais.

	public boolean equivalentService(Relais r) {
		return this.services.equals(r.services);
	}// Compare les deux liste de services avec la méthode equals.

	public boolean equals(Relais r) {
		return (this.positionX == r.positionX && this.positionY == r.positionY && this.equivalentService(r));
	}// Si deux relais ont la même position et les même services, alors ce sont les mêmes.

	public void editer() {
		System.out.println("Edition du relais : " + this.nom);
		System.out.println("Que voulez vous éditer ?");
		System.out.println("1. Nom");
		System.out.println("2. Position X");
		System.out.println("3. Position Y");
		System.out.println("4. Services");
		System.out.println("5. Quitter l'editeur de relais");
		Scanner scan = new Scanner(System.in);
		int choixEdit = 0;
		System.out.print("Choix : ");
		choixEdit = scan.nextInt();
		scan.nextLine();
		switch (choixEdit) {
		case 1:
			System.out.print("Nom : ");
			this.nom = scan.nextLine();
			this.editer();
			break;
		case 2:
			System.out.print("X : ");
			this.positionX = scan.nextInt();
			this.editer();
			break;
		case 3:
			System.out.print("Y : ");
			this.positionY = scan.nextInt();
			this.editer();
			break;
		case 4:
			System.out.println("1. Ajouter un service");
			System.out.println("2. Editer un service");
			System.out.println("3. Supprimer un service");
			System.out.println("4. Quitter l'éditeur de service");
			System.out.print("Choix : ");
			choixEdit = scan.nextInt();
			switch (choixEdit) {
			case 1:
				System.out.println("Ajouter un service : ");
				System.out.print("Nom : ");
				scan.nextLine();
				String choixServ = scan.nextLine();
				System.out.println("choixServ : " + choixServ);
				try {
					this.ajouterService(choixServ);
				} catch (RelaisException e) {
				}
				this.editer();
				break;
			case 2:
				if (this.getNbService() == 0) {
					System.out.println("Le relais ne propose pas de service pour l'instant. Vous devez créer un service avant de pouvoir l'éditer");
					this.editer();
				} else {
					System.out.println("Editer un service : ");
					for (int i = 0; i < this.getNbService(); i++)
						System.out.println(i + 1 + ". " + this.services.get(i).getNom());
					System.out.print("Choix : ");
					choixEdit = scan.nextInt();
					this.services.get(choixEdit - 1).editer();
				}
				this.editer();
				break;
			case 3:
				if (this.getNbService() == 0) {
					System.out.println("Le relais ne propose pas de service pour l'instant. Vous devez créer des services avant de pouvoir en supprimer");
					this.editer();
				} else {
					System.out.println("Supprimer un service : ");
					for (int i = 0; i < this.getNbService(); i++)
						System.out.println(i + 1 + ". " + this.services.get(i).getNom());
					System.out.print("Choix : ");
					choixEdit = scan.nextInt();
					this.retirerService(this.services.get(choixEdit - 1));
				}
				this.editer();
				break;
			case 4:
				this.editer();
				break;
			default:
				break;
			}
			break;
		case 5:
			System.out.println("Retour au menu précédent");
			break;
		default:
			System.out.println("Retour au menu précédent");
		}
	}

	public boolean contientService(String nom) {
		for (Service s : this.services) {
			if (s.getNom().equals(nom))
				return true;
		}
		return false;
	}// Renvoie vrai si le relais contient un service nommé "nom", faux sinon.

	public void afficherRelais() {
		System.out.println(this.nom + " :\nAbscisse : " + this.positionX + "\nOrdonnee : " + this.positionY);
		afficherServices();
	}// Affiche un relais et ses services.

	public void afficherServices() {
		System.out.println("Services disponnibles :");
		for (Service s : this.services)
			System.out.println("\t- " + s.getNom() + " | Horaires :" + s.getPlage());
	}// Affiche les services d'un relais et leur horaires de disponibilité.

	public static Relais genererRelais() {
		Relais nouveauRelais = null;
		int x = (int) (Math.round(Math.random() * 100));
		int y = (int) (Math.round(Math.random() * 100));
		String nom = "Relais n° " + Relais.getId();
		try {
			nouveauRelais = new Relais(x, y, nom);
		} catch (RelaisException e) {
			nouveauRelais = new Relais();
		}
		return nouveauRelais;
	}// Méthode pour générer aléatoirement un relais.

	public static List<Relais> genererRelais(int nbRelais) {
		List<Relais> l = new LinkedList<Relais>();
		for (int i = 0; i < nbRelais; i++) {
			l.add(Relais.genererRelais());
		}
		return l;
	}// Générer un nombre donné de relais.

	public static int getId() {
		return Relais.id;
	}

	public String getNom() {
		return this.nom;
	}

	public int getX() {
		return this.positionX;
	}

	public int getY() {
		return this.positionY;
	}

	public List<Service> getServices() {
		return (List<Service>) this.services;
	}

	public Service getServices(int i) {
		return this.services.get(i);
	}// Service par index

	public Service getServices(String nom) {
		for (Service s : this.services) {
			if (s.getNom().equals(nom))
				return s;
		}
		return null;
	}// Services par nom

	public int getNbService() {
		return this.services.size();
	}

	public int compareTo(Relais o) {
		return this.getNom().compareTo(o.getNom());
	}
}
