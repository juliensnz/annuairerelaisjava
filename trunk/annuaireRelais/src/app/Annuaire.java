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
	 * @param nom
	 *            Nom du relais, qui sert d'identifiant dans la table
	 * @throws exceptions.RelaisException
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
	 * Retire le ou les relais se trouvant � l'abscisse x et � l'ordonn�e y
	 * pass�e en param�tre. Le controle de saisie est effectu� dans la classe
	 * d'interface. Cette m�thode parcours tous les �l�ments de la table.
	 * 
	 * @param x
	 *            Abscisse du relais
	 * @param y
	 *            Ordonn�e du relais
	 */
	public void retirerRelais(int x, int y) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getX() == x && r.getY() == y)
				this.annuaireRelais.remove(key);
		}
	}

	/**
	 * Retire le ou les relais qui s'appelle(nt) "nom" de l'annuaire. Cette
	 * m�thode parcours toute la table.
	 * 
	 * @param nom
	 *            Nom du relais � supprimer.
	 */
	public void retirerRelais(String nom) {
		for (String key : this.annuaireRelais.keySet()) {
			if (key.equals(nom))
				this.annuaireRelais.remove(key);
		}
	}

	/**
	 * Retire le ou les relais de l'annuaire qui proposent le service "s". Le
	 * relais est supprim� si le service est contenu dans sa liste de services.
	 * Ce test est r�alis� via la m�thode contains de l'interface List.
	 * 
	 * @param s
	 *            Service � rechercher pour �liminer les relais le contenant.
	 * @see app.Service
	 */
	public void retirerRelais(Service s) {
		for (String key : this.annuaireRelais.keySet()) {
			Relais r = this.annuaireRelais.get(key);
			if (r.getServices().contains(s))
				this.annuaireRelais.remove(key);
		}
	}

	/**
	 * Supprime un service de tous les relais, s'il y est pr�sent. On parours
	 * toute l'annuaire.
	 * 
	 * @param s
	 *            Service � rechercher et supprimer.
	 */
	public void retirerService(Service s) {
		for (Relais r : this.annuaireRelais.values())
			r.retirerService(s);
	}

	/**
	 * Supprime le relais ayant la cl� "key" dans la table.
	 * 
	 * @param key
	 *            La cl� du relais � supprimer dans la table, qui est aussi son
	 *            nom.
	 */
	public void supprimerRelais(String key) {
		this.annuaireRelais.remove(key);
	}

	/**
	 * Getter pour obtenir la table des relais.
	 * 
	 * @return La table associative des relais de l'annuaire.
	 */
	public Map<String, Relais> getMapRelais() {
		return this.annuaireRelais;
	}

	/**
	 * Cette m�thode permet d'obtenir le nombre de relais de l'annuaire.
	 * 
	 * @return La taille de la table associative (m�thode size de l'interface
	 *         Map)
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