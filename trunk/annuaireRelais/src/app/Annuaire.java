package app;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;
import exceptions.RelaisException;

public class Annuaire {
	
	private List<Relais> annuaireRelais = null;
	
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
			for(String s : r.getServices()) {
				if(s == service)
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
} 