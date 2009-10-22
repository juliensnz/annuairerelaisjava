package app;

import java.util.LinkedList;
import java.util.List;
import exceptions.RelaisException;

public class Annuaire {
	
	private List<Relais> annuaireRelais = null;
	
	public Annuaire() {
		this.annuaireRelais = new LinkedList<Relais>();
		
		System.out.println("Cr�ation de l'annuaire termin�.");
	}

	public void ajouterRelais() {

		Relais nouveauRelais = new Relais();
		this.annuaireRelais.add(nouveauRelais);
	}//Ajoute un nouveau relais par d�faut, d�pourvu de services, de coordonn�es (0,0)

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
	
	public void afficherAnnuaire() {
		System.out.println("Voici la liste des relais pr�sents dans l'annuaire : \n");
		for(Relais r : this.annuaireRelais) {
			r.afficherRelais();
			System.out.println("=========================");
		}
	}
} 