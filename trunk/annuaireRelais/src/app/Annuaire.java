package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import exceptions.RelaisException;

public class Annuaire {

	private Map<String, Relais>	annuaireRelais	= null;

	public Annuaire() {
		this.annuaireRelais = new HashMap<String, Relais>();
	}// Constructeur basique. Ajouter un constructeur prenant une liste en

	// parametre? Un compteur d'annuaires?

	public void ajouterRelais() {
		Relais r = new Relais();
		this.annuaireRelais.put(r.getNom(), r);
	}// Ajoute un nouveau relais par dŽfaut, dŽpourvu de services, de

	// coordonnees (0,0)

	public void ajouterRelais(int positionX, int positionY, String nom) {
		Relais r = null;
		try {
			r = new Relais(positionX, positionY, nom);
			System.out.println("Relais créŽŽé");
			this.annuaireRelais.put(r.getNom(), r);
		}
		catch (RelaisException e) {
		}
	}// Ajoute un relais de coordonnee (positionX, positionY) nomme "nom"

	// l'annuaire.

	public void rechercherRelais(int positionX, int positionY, String service, int heure) {
		List<String> correspond = new ArrayList<String>();
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.contientService(service) && r.getServices(service).getDispo(heure))
				correspond.add(key);
		}
		if (correspond.isEmpty())
			System.out.println("Aucun relais ne correspond ˆ votre recherche àˆ cette heure-ci");
		// Si la liste des relais proposant le service est vide...
		else {
			Relais r = null, plusProche = this.annuaireRelais.get(correspond.get(0));
			for (String key : correspond)
				r = this.annuaireRelais.get(key);
			if (r.distance(positionX, positionY) < plusProche.distance(positionX, positionY))
				plusProche = r;// Si on trouve un relai plus proche, on
			// remplace
			System.out.println("Le relais le plus proche qui propose le service \"" + service + "\" est situŽé ˆ " + plusProche.getNom() + " (ˆ " + plusProche.distance(positionX, positionY) + "km d'ici).");
		}
	}

	public void retirerRelais(int x, int y) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getX() == x && r.getY() == y)
				this.annuaireRelais.remove(key);
		}
	}// Retire tous les relais de coordonnŽes (positionX, positionY)

	public void retirerRelais(String nom) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getNom().equals(nom))
				this.annuaireRelais.remove(key);
		}
	}// Retire de l'annuaire tous les relais nommŽs "nom"

	public void retirerRelais(Service s) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getServices().contains(s))
				this.annuaireRelais.remove(key);
		}
	}// Retire de l'annuaire tous les relais offrant le service s

	public void retirerService(Service s) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			r.retirerService(s);
		}
	}// Retire un service donnŽ de tous les relais de l'annuaire

	public void supprimerRelais(int i) {
		if (i >= 0 && i < this.annuaireRelais.size()) {
			this.annuaireRelais.remove(i);
		}
	}// Supprime un relais de l'annuaire a l'index i

	public Map<String, Relais> getListRelais() {
		return this.annuaireRelais;
	}

	public int getNbRelais() {
		return this.annuaireRelais.size();
	}

	public Relais getRelais(int i) {
		return this.annuaireRelais.get(i);
	}// Getter Relais par index

	public Relais getRelais(String nom) {
		return this.annuaireRelais.get(nom);
	}// Getter Relais par nom

	public boolean equals(Annuaire a) {
		return this.annuaireRelais.equals(a.annuaireRelais);
	}

	public boolean isEmpty() {
		return this.annuaireRelais.isEmpty();
	}
}