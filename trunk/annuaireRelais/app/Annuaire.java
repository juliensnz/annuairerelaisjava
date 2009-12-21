package app;

import java.util.HashMap;
import java.util.Map;
import exceptions.RelaisException;

/**
 * La classe annuaire est d�di�e � la gestion d'un annuaire de Relais<br/>
 * Elle propose l'ensemble des outils d�di�s � la cr�ation, modification et
 * suppression de ceux-ci.
 * 
 * @author Bury Maxime, Julien Sanchez
 * @see app.Relais
 * @see app.Service
 */
public class Annuaire {

	private Map<String, Relais>	annuaireRelais	= null;

	/**
	 * Constructeur basique. Il se charge simplement d'initialiser la table
	 * associative des relais.
	 */
	public Annuaire() {
		this.annuaireRelais = new HashMap<String, Relais>();
	}

	/**
	 * Ajoute un nouveau relais par d��faut, d�pourvu de services, de
	 * coordonnees (0,0). Les relais sont identifi�s par leur nom dans
	 * l'annuaire.
	 */
	public void ajouterRelais() {
		Relais r = new Relais();
		this.annuaireRelais.put(r.getNom(), r);
	}

	/**
	 * Ajoute un relais de coordonn�e (x, y) nomm� "nom" dans l'annuaire. En cas
	 * d'�chec, un relais par d�faut est ajout�, sans prendre en compte les
	 * param�tres.
	 * 
	 * @param x
	 *            Abscisse du relais
	 * @param y
	 *            Ordonn�e du relais
	 */
	public void ajouterRelais(int x, int y, String nom) {
		Relais r = null;
		try {
			r = new Relais(x, y, nom);
			this.annuaireRelais.put(r.getNom(), r);
		}
		catch (RelaisException e) {
		}
	}

	/**
	 * Retire le relais se trouvant � la position entr�e en param�tre.
	 */
	public void retirerRelais(int x, int y) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getX() == x && r.getY() == y)
				this.annuaireRelais.remove(key);
		}
	}

	/**
	 * Retire les relais proposant le m�me service que celui entr� en param�tre
	 * (String).
	 */
	public void retirerRelais(String nom) {
		for (String key : this.annuaireRelais.keySet()) {
			if (key.equals(nom))
				this.annuaireRelais.remove(key);
		}
	}

	/**
	 * Retire les relais de l'annuaire proposant le m�me service que celui
	 * entr�e en param�tre.
	 */
	public void retirerRelais(Service s) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getServices().contains(s))
				this.annuaireRelais.remove(key);
		}
	}

	/**
	 * Supprime tous les relais contenant le m�me service (entr�e en param�tre)
	 */
	public void retirerService(Service s) {
		for (Relais r : this.annuaireRelais.values())
			r.retirerService(s);
	}

	/**
	 * Supprime le relais ayant le la cl� key
	 */
	public void supprimerRelais(String key) {
		this.annuaireRelais.remove(key);
	}

	public Map<String, Relais> getMapRelais() {
		return this.annuaireRelais;
	}

	/**
	 * Obtenir le nombre de relais dans l'annuaire.
	 */
	public int getNbRelais() {
		return this.annuaireRelais.size();
	}

	/**
	 * Obtenir un relais en fonction de son nom
	 */
	public Relais getRelais(String nom) {
		return this.annuaireRelais.get(nom);
	}

	/**
	 * Tester l'�galit� entre deux relais.
	 */
	public boolean equals(Annuaire a) {
		return this.annuaireRelais.equals(a.annuaireRelais);
	}

	public boolean isEmpty() {
		return this.annuaireRelais.isEmpty();
	}
}