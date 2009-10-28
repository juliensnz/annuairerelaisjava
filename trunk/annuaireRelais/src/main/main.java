package main;

import exceptions.RelaisException;
import app.Relais;
import app.Annuaire;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Relais test = null;
		try
		{
			test = new Relais(12,34, "R1");
			test.ajouterService("Test1");
			test.ajouterService("Test2");
			test.ajouterService("Test3");
			test.afficherRelais();
		}catch (RelaisException e){
			System.out.println("Plantage");
		};*/
	Annuaire a = new Annuaire();
	a.remplirAleatoirement(10);
	a.afficherAnnuaire();
	a.editerUnRelais();
	}
}
