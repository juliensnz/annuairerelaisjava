package app;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import exceptions.RelaisException;

public class Annuaire {
	private List<Relais> annuaireRelais = null;

	public Annuaire() {
		this.annuaireRelais = new ArrayList<Relais>();
	}// Constructeur basique. Ajouter un constructeur prenant une liste en parametre? Un compteur d'annuaires?

	public void ajouterRelais() {
		Relais nouveauRelais = new Relais();
		this.annuaireRelais.add(nouveauRelais);
		Collections.sort(this.annuaireRelais);
	}// Ajoute un nouveau relais par défaut, dépourvu de services, de coordonnees (0,0)

	public void ajouterRelais(int positionX, int positionY, String nom) {
		Relais nouveauRelais = null;
		try {
			nouveauRelais = new Relais(positionX, positionY, nom);
			System.out.println("Relais créé \n");
			this.annuaireRelais.add(nouveauRelais);
			Collections.sort(this.annuaireRelais);
		} catch (RelaisException e) {}
	}// Ajoute un relais de coordonnee (positionX, positionY) nomme "nom" à
	// l'annuaire.
	
	public void supprimerRelais(int i){
		if(i >= 0 && i < this.annuaireRelais.size()){
			this.annuaireRelais.remove(i);
			Collections.sort(this.annuaireRelais);
		}
	}//Supprime un relais de l'annuaire a l'index i
	
	public void retirerRelais(String nom) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		while (it.hasNext()) {
			if ((it.next()).getNom().equals(nom))
				it.remove();
		}
		Collections.sort(this.annuaireRelais);
	}// Retire de l'annuaire tous les relais nommés "nom"

	public void retirerRelais(int positionX, int positionY) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		while (it.hasNext()) {
			Relais r = it.next();
			if (r.getX() == positionX && r.getY() == positionY)
				it.remove();
		}
		Collections.sort(this.annuaireRelais);
	}// Retire tous les relais de coordonnées (positionX, positionY)

	public void retirerRelais(Service s) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		while (it.hasNext()) {
			Relais r = it.next();
			for (Service si : r.getServices()) {
				if (si.getNom().equals(s.getNom()))
					it.remove();
			}
		}
		Collections.sort(this.annuaireRelais);
	}// Retire de l'annuaire tous les relais offrant le service s

	public void retirerService(Service s) {
		ListIterator<Relais> it = this.annuaireRelais.listIterator();
		while (it.hasNext())
			it.next().retirerService(s);
	}// Retire un service donné de tous les relais de l'annuaire

	public void rechercherRelais(int positionX, int positionY, String service, int heure) {
		List<Relais> correspond = new ArrayList<Relais>();
		System.out.println("Heure : " + heure);
		for (Relais r : this.annuaireRelais) {
			if (r.contientService(service) && r.getServices(service).getDispo(heure)) {
				correspond.add(r);
			}// On trouve la liste des relais proposant le service.
		}
		if (correspond.isEmpty())
			System.out.println("Aucun relais ne correspond à votre recherche à cette heure-ci");
		// Si la liste des relais proposant le service est vide...
		else {
			Relais plusProche = correspond.get(0);
			// Initialisation du relais le plus proche au premier de la liste des candidats par défaut
			for (Relais r : correspond) {
				if (r.distance(positionX, positionY) < plusProche.distance(positionX, positionY))
					plusProche = r;// Si on trouve un relai plus proche, on remplace
			}
			System.out.println("Le relais le plus proche qui propose le service \"" + service + "\" est situé à " + plusProche.getNom() + " (à " + plusProche.distance(positionX, positionY) + "km d'ici).");
		}
	}
	
	public Relais getRelais(int i) {
		return this.annuaireRelais.get(i);
	}// Getter Relais par index

	public Relais getRelais(String nom) {
		for (Relais r : this.annuaireRelais) {
			if (r.getNom().equals(nom))
				return r;
		}
		return null;
	}// Getter Relais par nom

	public int getNbRelais() {
		return this.annuaireRelais.size();
	}

	public List<Relais> getListRelais() {
		return this.annuaireRelais;
	}
	
	public boolean isEmpty(){
		return this.annuaireRelais.isEmpty();
	}

	public boolean equals(Annuaire annuaire) {
		return this.annuaireRelais.equals(annuaire.getListRelais());
	}
}