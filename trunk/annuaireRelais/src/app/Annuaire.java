package app;

import java.util.LinkedList;
import java.util.List;
import exceptions.RelaisException;

public class Annuaire {
	
	public static List<Relais> annuaireRelais = new LinkedList<Relais>();

	public Annuaire() {
		System.out.println("Cr�ation de l'annuaire termin�.");
	}

	public void ajouterRelais() {

		Relais nouveauRelais = new Relais();
		Annuaire.annuaireRelais.add(nouveauRelais);
	}//Ajoute un nouveau relais par d�faut, d�pourvu de services, de coordonn�es (0,0)

	public void ajouterRelais(int positionX, int positionY, String nom) {

		Relais nouveauRelais = null;
		try {
			nouveauRelais = new Relais(positionX, positionY, nom);

		} catch (RelaisException e) {
			nouveauRelais = new Relais();
		}
		Annuaire.annuaireRelais.add(nouveauRelais);
	}
	
	public void remplirAleatoirementAnnuaire(int nbRelais) {
		List<Relais> mesRelais = Relais.genererRelais(nbRelais);
		
		Annuaire.annuaireRelais.addAll(mesRelais);
	}
} 