package app;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import exceptions.RelaisException;

public class Relais {
	private String nom;
	private int positionX;
	private int positionY;
	private static int id = 0;
	public List<Service> services = new LinkedList<Service>();
	
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
		Service nouvService = new Service(nomService);
		boolean contient = false;
		for(Service s : this.services)
		{
			if(s.getNom() == nouvService.getNom() || nomService == ""){
				contient = true;
			
				throw new RelaisException("Le service existe dŽjˆ ou le nom est vide");

			}
		}
		if(!(contient)){
			this.services.add(nouvService);
		}
	}
	
	public void ajouterService(List<String> l) throws RelaisException {
		for(String s : l) {
			ajouterService(s);
		}
	}
	
	public void retirerService(String nomService) {
		for(Service s : this.services) {
			if(s.getNom() == nomService)
			{
				this.services.remove(s);
			}
		}
			
	}
	
	public double distance(int x, int y) {
		return Math.round(Math.sqrt(Math.pow((this.positionX - x), 2) + Math.pow((this.positionY - y), 2))*100)/100;
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
	public boolean contientService(String entreeService)
	{
		for(Service s : this.services)
		{
			if(s.getNom() == entreeService)
			{
				return true;
			}
		}
		return false;
	}
	public void afficherRelais() {
		System.out.println(this.nom + " :\nAbscisse : " + this.positionX + "\nOrdonnee : " + this.positionY );
		afficherServices();
	}
	
	public void afficherServices() {
		System.out.println("Services disponnibles :");
		for(Service s : this.services)
			System.out.println("- "+s.getNom()+" | Horaires : "+s.getPlage()+"");
		
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
	
	public List<Service> getServices() {
		return (List<Service>) this.services;
	}
	public Service getServices(int i)
	{
		return this.services.get(i);
	}
	
	public Service getServices(String nom)
	{
		for(Service s : this.services)
		{
			if(s.getNom() == nom)
			{
				return s;
			}
		}
		return null;
	}
	
}
