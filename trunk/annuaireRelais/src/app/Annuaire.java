package app;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import exceptions.RelaisException;

public class Annuaire {
	
	private List<Relais> annuaireRelais = null;
	
	public Annuaire() {
		this.annuaireRelais = new LinkedList<Relais>();
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
	
	public void editerUnRelais() {
		Scanner scan = new Scanner(System.in);
		int choixDuRelais;
		
		System.out.println("Choisissez le relais que vous voulez éditer : ");
		for(int i = 0; i < this.annuaireRelais.size(); i++)
			System.out.println((i+1)+" : "+this.annuaireRelais.get(i).getNom());
		//Listage des relais
		do {
			System.out.print("Choix : ");
			choixDuRelais = scan.nextInt() - 1;
		} while(!(choixDuRelais >= 0  && choixDuRelais < this.annuaireRelais.size()));
		
		this.annuaireRelais.get(choixDuRelais).editer();
	}//Edition d'un relais de l'annuaire (préexistant).
	
	public void retirerRelais(String nom) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			if((it.next()).getNom() == nom)
				it.remove();
		}	
	}//Retire de l'annuaire tous les relais nommés "nom"
	
	public void retirerRelais(int positionX, int positionY) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			Relais r = it.next();
			if(r.getX() == positionX && r.getY() == positionY)
				it.remove();
		}
	}//Retire tous les relais de coordonnées (positionX, positionY)
	
	public void retirerRelais(Service s) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			Relais r = it.next();
			for(Service si : r.getServices()) {
				if(si.getNom() == s.getNom())
					it.remove();
			}
		}
	}// Retire de l'annuaire tous les relais offrant le service s
	
	public void retirerService(Service s) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext())
			 it.next().retirerService(s);
	}// Retire un service donné de tous les relais de l'annuaire
	
	public void afficherAnnuaire() {
		System.out.println("Voici la liste des relais présents dans l'annuaire : \n");
		for(Relais r : this.annuaireRelais) {
			r.afficherRelais();
			System.out.println("=========================");
		}
	}//Affiche tout l'annuaire
	
	public void afficherAnnuaire(Service s) {
		System.out.println("Voici la liste des relais présents dans l'annuaire offrant le service " + s.getNom() + " : \n");
		for(Relais r : this.annuaireRelais) {
			for(Service si : r.getServices()) {
				if(si.getNom() == s.getNom()){
					r.afficherRelais();
					System.out.println("=========================");
				}
			}
			System.out.println("Fin de la liste");
		}
		
	}//Affiche tous les relais de l'annuaire offrant le service s, comparaison sur les noms
	
	public void rechercherRelais(int positionX,int positionY,String service) {
		List<Relais> correspond = new LinkedList<Relais>();
		int heures = (int) (System.currentTimeMillis()/(1000*60*60)%24 + 1);//Initialisation du timestamp
		int minutes = (int) (System.currentTimeMillis()/(1000*60)%60);//Initialisation du timestamp
		
		System.out.println(heures+"h"+minutes+"min");
		
		for(Relais r : this.annuaireRelais) {
			if(r.contientService(service) && r.getServices(service).getDispo()[heures*60+minutes]) {
				correspond.add(r);
			}//On trouve la liste des relais proposant le service.
		}
		if(correspond.isEmpty())
			System.out.println("Aucun relais ne correspond à votre recherche à cette heure-ci");
			//Si la liste des relais proposant le service est vide...
		else {
			Relais plusProche = correspond.get(0);//Initialisation du relais le plus proche au premier de la liste des candidats par défaut
			for(Relais r : correspond) {
				if(r.distance(positionX,positionY) < plusProche.distance(positionX,positionY))
					plusProche = r;//Si on trouve un relai plus proche, on remplace
			}
			System.out.println("Le relais le plus proche qui propose le service \""+service+"\" est situé à "+plusProche.getNom()+" (à "+plusProche.distance(positionX,positionY)+"km d'ici).");
		}
	}
	
	public Relais getRelais(int i) {
		return this.annuaireRelais.get(i);
	}//Getter Relais par index
	
	public Relais getRelais(String nom) {
		for(Relais r : this.annuaireRelais) {
			if(r.getNom() == nom)
				return r;
		}
		return null;
	}//Getter Relais par nom
	
	public int getNbRelais() {
		return this.annuaireRelais.size();
	}
	public List<Relais> getListRelais()
	{
		return this.annuaireRelais;
	}
} 