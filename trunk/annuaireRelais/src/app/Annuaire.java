package app;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import exceptions.RelaisException;

public class Annuaire {
	
	public List<Relais> annuaireRelais = null;
	
	public Annuaire() {
		this.annuaireRelais = new LinkedList<Relais>();
		
		System.out.println("Creation de l'annuaire terminee.");
	}//Constructeur basique. Ajouter un constructeur prenant une liste en parametre? Un compteur d'annuaires?

	public void ajouterRelais() {

		Relais nouveauRelais = new Relais();
		this.annuaireRelais.add(nouveauRelais);
	}//Ajoute un nouveau relais par défaut, dépourvu de services, de coordonnees (0,0)

	public void ajouterRelais(int positionX, int positionY, String nom) {

		Relais nouveauRelais = null;
		try {
			nouveauRelais = new Relais(positionX, positionY, nom);

		} catch (RelaisException e) {
			nouveauRelais = new Relais();
		}
		this.annuaireRelais.add(nouveauRelais);
	}//Ajoute un relais de coordonnee (positionX, positionY) nomme "nom" à l'annuaire, ajoute un relais par defaut en cas d'echec de la creation d'un relais parametre
	
	public void editerUnRelais()
	{
		System.out.println("Choisissez le relais que vous voulez éditer : ");
		for(int i = 0;i<this.annuaireRelais.size();i++)
		{
			System.out.println((i+1)+" : "+this.annuaireRelais.get(i).getNom());
		}
		Scanner scan = new Scanner(System.in);
		int choixDuRelais;
		do
		{
			System.out.print("Choix : ");
			choixDuRelais = scan.nextInt();
		}while(!(this.annuaireRelais.size() > choixDuRelais-1 && choixDuRelais-1 >= 0 ));
		this.annuaireRelais.get(choixDuRelais-1).editer();
	}
	public void remplirAleatoirement(int nbRelais) {
		List<Relais> mesRelais = Relais.genererRelais(nbRelais);
		
		this.annuaireRelais.addAll(mesRelais);
	}//Remplis un annuaire avec nbRelais relais aleatoirement generes (depourvus de service => ajouter une banque de service?)
	
	public void retirerRelais(String nom) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			if((it.next()).getNom() == nom) {
				it.remove();
				return;
			}
		}	
	}//Retire de l'annuaire tous les relais nommes "nom"
	
	public void retirerRelais(int positionX, int positionY) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			Relais r = it.next();
			if(r.getX() == positionX && r.getY() == positionY) {
				it.remove();
				return;
			}
		}
	}//Retire tous les relais de coordonnees (positionX, positionY)
	
	public void retirerRelaisServ(String service) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			Relais r = it.next();
			for(Service s : r.getServices()) {
				if(s.getNom() == service)
					it.remove();
			}
		}
	}// Retire de l'annuaire tous les relais offrant le service "service"
	
	public void retirerService(String service) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext())
			 it.next().retirerService(service);
	}// Retire un service donné de tous les relais de l'annuaire
	
	public void afficherAnnuaire() {
		System.out.println("Voici la liste des relais présents dans l'annuaire : \n");
		for(Relais r : this.annuaireRelais) {
			r.afficherRelais();
			System.out.println("=========================");
		}
	}//Affiche tout l'annuaire
	
	public void afficherAnnuaire(String service) {
		System.out.println("Voici la liste des relais présents dans l'annuaire offrant le service " + service + " : \n");
		for(Relais r : this.annuaireRelais) {
			if(r.getServices().contains(service)) {
				r.afficherRelais();
				System.out.println("=========================");
			}
		}
	}//Affiche tous les relais de l'annuaire offrant le service "service"
	
	public boolean egualA(Relais r) {
		boolean resultat = true;
		boolean temp = false;
		for(int i = 0;i<this.annuaireRelais.size();i++)
		{
			for(int j = i;j<this.annuaireRelais.size();j++)
			{
				if(this.annuaireRelais.get(i).egalA(this.annuaireRelais.get(j)) && this.annuaireRelais.get(i).equivalentService(this.annuaireRelais.get(j)))
				{
					temp = true;
				}
			}
			if(!temp)
			{
				resultat = false;
				break;
			}
		}
		return resultat;
	}
	
	public void rechercherRelais(int positionX,int positionY,String service)
	{
		List<Relais> correspond = new ArrayList<Relais>();
		int heures = (int) (System.currentTimeMillis()/(1000*60*60))-349247;
		int minutes = (int) (System.currentTimeMillis()/(1000*60))-20955420;
		for(Relais r : annuaireRelais)
		{
			
			if(r.contientService(service) && r.getServices(service).getDispo()[heures*60+minutes])
			{
				correspond.add(r);
			}
		}
		if(correspond.isEmpty())
		{
			System.out.println("Aucun relais ne correspond à votre recherche à cette heure-ci");
		}
		else
		{
			Relais plusProche = correspond.get(0);
			for(Relais r : correspond)
			{
				if(r.distance(positionX,positionY) < plusProche.distance(positionX,positionY))
				{
					plusProche = r;
				}
			}
			System.out.println("Le relais le plus proche qui propose le service \""+service+"\" est situé à "+plusProche.getNom()+" (à "+plusProche.distance(positionX,positionY)+"km d'ici).");
		}
		
	}
	
	public Relais getRelais(int i)
	{
		return this.annuaireRelais.get(i);
	}
	
	public Relais getRelais(String nom)
	{
		for(Relais r : this.annuaireRelais)
		{
			if(r.getNom() == nom)
			{
				return r;
			}
		}
		return null;
	}
} 