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
			throw new RelaisException("La position d'un relais ne peut être négative !");
		} else if(nom == "") {
			throw new RelaisException("Le nom d'un relais ne peut pas être vide !");
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
		String nom = "Relais n° " + Relais.getId();
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
			throw new RelaisException("La position d'un relais ne peut être négative !");
		}
		else {
		this.positionX = x;
		this.positionY = y;
		}
	}
	
	public void setNom(String nom) throws RelaisException {
		if(nom == "")
			throw new RelaisException("Le nom d'un relais ne peux pas être vide");
		else
			this.nom = nom;
	}

	public void ajouterService(String nomService) throws RelaisException{
		if(this.services.contains(nomService) || nomService == ""){
			throw new RelaisException("Le service existe déjà ou le nom est vide");
		}
		else{
			this.services.add(nomService);
		}
	}
	
	public void ajouterService(List<String> l) throws RelaisException {
		for(String s : l) {
			if(this.services.contains(s))
				throw new RelaisException("Un des services de la liste existe déjà");
			else
				this.services.add(s);
		}
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

	public void afficherRelais() {
		System.out.println(this.nom + " :\n\nAbscisse : " + this.positionX + "\nOrdonnee : " + this.positionY +"\n");
		afficherServices();
	}
	
	public void afficherServices() {
		System.out.println("Services disponnibles : \n");
		for(String s : services)
			System.out.println(s);
		System.out.print("\n");
	}
	static public int getId(){
		return Relais.id;
	}
}
