package app;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import exceptions.RelaisException;
import java.util.Scanner;

public class Relais {
	private String nom;
	private int positionX;
	private int positionY;
	private static int id = 0;
	private List<String> services = new LinkedList<String>();
	
	public Relais() {
		this.positionX = 0;
		this.positionY = 0;
		this.nom = "";
		Relais.id++;
	}

	public Relais(int x, int y, String nom) throws RelaisException {
		if (x <= 0 || y <= 0) {
			throw new RelaisException("La position d'un relais ne peut tre nŽgative !");
		} else if(nom == "") {
			throw new RelaisException("Le nom d'un relais ne peut pas tre vide !");
		} else {
			this.positionX = x;
			this.positionY = y;
			this.nom = nom;
			Relais.id++;
		}
	}
	
	public static Relais genererRelais() {

		Relais nouveauRelais = null;
		int x = (int)(Math.round(Math.random()*100));
		int y = (int)(Math.round(Math.random()*100));
		String nom = "Relais n¡ " + Relais.getId();
		try {
			nouveauRelais = new Relais(x, y, nom);
		} catch (RelaisException e) {
			nouveauRelais = new Relais();
		}
		return nouveauRelais;
	}
	public static List<Relais> genererRelais(int nbRelais) {
		List<Relais> l = new LinkedList<Relais>();
		
		for(int i = 0; i < nbRelais; i++) {
			l.add(Relais.genererRelais());
		}
		return l;
	}

	public void setPosition(int x, int y) throws RelaisException {
		if (x <= 0 || y <= 0) {
			throw new RelaisException("La position d'un relais ne peut tre nŽgative !");
		}
		else {
		this.positionX = x;
		this.positionY = y;
		}
	}
	
	public void setNom(String nom) throws RelaisException {
		if(nom == "")
			throw new RelaisException("Le nom d'un relais ne peux pas tre vide");
		else
			this.nom = nom;
	}

	public void ajouterService(String nomService) throws RelaisException{
		if(this.services.contains(nomService) || nomService == ""){
			throw new RelaisException("Le service existe dŽjˆ ou le nom est vide");
		}
		else{
			this.services.add(nomService);
		}
	}
	
	public void ajouterService(List<String> l) throws RelaisException {
		for(String s : l) {
			if(this.services.contains(s))
				throw new RelaisException("Un des services de la liste existe dŽjˆ");
			else
				this.services.add(s);
		}
	}
	
	public void retirerService(String service) {
			this.services.remove(service);
	}
	
	public double distance(int x, int y) {
		return Math.sqrt(Math.pow((this.positionX - x), 2) + Math.pow((this.positionY - y), 2));
	}
	
	public boolean equivalentService(Relais r) {
		return this.services.equals(r.services);
	}
	
	public boolean egalA(Relais r) {
		if (this.positionX == r.positionX && this.positionY == r.positionY && this.equivalentService(r))
			return true;
		else
			return false;
	}
	
	public void editer()
	{
		System.out.println("Edition du relais : "+this.nom);
		System.out.println("Que voulez vous Žditer ?");
		System.out.println("1. Nom\n2. Position x\n3. Position y\n4. Services");
		Scanner scan = new Scanner(System.in);
		int choixEdit = 0;
		do
		{
			System.out.print("Choix : ");
			choixEdit = scan.nextInt();
		}while(!(choixEdit > 0 && choixEdit <= 4));
		switch(choixEdit)
		{
			case 1:
				System.out.print("Nom : ");
				this.nom = scan.next();
				break;
			case 2:
				System.out.print("X : ");
				this.positionX = scan.nextInt();
				break;
			case 3:
				System.out.print("Y : ");
				this.positionY = scan.nextInt();
				break;
			case 4:
				
				System.out.print("Service ˆ ajouter : ");
			try{
				this.ajouterService(scan.next());
			} catch (RelaisException e) {}
				break;
			default:
			 
		}
	}
	
	public void afficherRelais() {
		System.out.println(this.nom + " :\nAbscisse : " + this.positionX + "\nOrdonnee : " + this.positionY );
		afficherServices();
	}
	
	public void afficherServices() {
		System.out.println("Services disponnibles :");
		for(String s : services)
			System.out.println(s);
		
	}
	public static int getId(){
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
	
	public List<String> getServices() {
		return this.services;
	}

	
}
