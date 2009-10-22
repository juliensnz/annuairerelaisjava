package app;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;
import exceptions.RelaisException;

public class Annuaire {
	
	private List<Relais> annuaireRelais = null;
	
	public Annuaire() {
		this.annuaireRelais = new LinkedList<Relais>();
		
		System.out.println("Création de l'annuaire terminé.");
	}

	public void ajouterRelais() {

		Relais nouveauRelais = new Relais();
		this.annuaireRelais.add(nouveauRelais);
	}//Ajoute un nouveau relais par défaut, dépourvu de services, de coordonnées (0,0)

	public void ajouterRelais(int positionX, int positionY, String nom) {

		Relais nouveauRelais = null;
		try {
			nouveauRelais = new Relais(positionX, positionY, nom);

		} catch (RelaisException e) {
			nouveauRelais = new Relais();
		}
		this.annuaireRelais.add(nouveauRelais);
	}
	
	public void remplirAleatoirement(int nbRelais) {
		List<Relais> mesRelais = Relais.genererRelais(nbRelais);
		
		this.annuaireRelais.addAll(mesRelais);
	}
	
	public void retirerRelais(String nom) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			if((it.next()).getNom() == nom) {
				it.remove();
				return;
			}
		}	
	}
	
	public void retirerRelais(int positionX, int positionY) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			Relais r = it.next();
			if(r.getX() == positionX && r.getY() == positionY) {
				it.remove();
				return;
			}
		}
	}
	
	public void retirerRelaisServ(String service) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		
		while(it.hasNext()) {
			Relais r = it.next();
			for(String s : r.getServices()) {
				if(s == service)
					it.remove();
			}
		}
	}// Retire de l'annuaire tous les relais offrant un service donné.
	
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
	}
	
	public void afficherAnnuaire(String service) {
		System.out.println("Voici la liste des relais présents dans l'annuaire offrant le service " + service + " : \n");
		for(Relais r : this.annuaireRelais) {
			if(r.getServices().contains(service)) {
				r.afficherRelais();
				System.out.println("=========================");
			}
		}
	}
} 