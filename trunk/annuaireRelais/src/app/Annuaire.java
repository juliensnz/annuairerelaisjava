package app;

import java.util.HashMap;
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
	}// Ajoute un nouveau relais par dÈéfaut, dépourvu de services, de

	// coordonnees (0,0)

	public void ajouterRelais(int x, int y, String nom) {
		Relais r = null;
		try {
			r = new Relais(x, y, nom);
			this.annuaireRelais.put(r.getNom(), r);
		}
		catch (RelaisException e) {
		}
	}// Ajoute un relais de coordonnee (positionX, positionY) nomme "nom"

	// l'annuaire.

	public void retirerRelais(int x, int y) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getX() == x && r.getY() == y)
				this.annuaireRelais.remove(key);
		}
	}// Retire tous les relais de coordonnées (positionX, positionY)

	public void retirerRelais(String nom) {
		for (String key : this.annuaireRelais.keySet()) {
			if (key.equals(nom))
				this.annuaireRelais.remove(key);
		}
	}// Retire de l'annuaire tous les relais nommés "nom"

	public void retirerRelais(Service s) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getServices().contains(s))
				this.annuaireRelais.remove(key);
		}
	}// Retire de l'annuaire tous les relais offrant le service s

	public void retirerService(Service s) {
		for (Relais r : this.annuaireRelais.values())
			r.retirerService(s);
	}// Retire un service donné de tous les relais de l'annuaire

	public void supprimerRelais(String key) {
		this.annuaireRelais.remove(key);
	}// Supprime un relais de l'annuaire a l'index i

	public Map<String, Relais> getMapRelais() {
		return this.annuaireRelais;
	}

	public int getNbRelais() {
		return this.annuaireRelais.size();
	}

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