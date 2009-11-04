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
	a.ajouterRelais(12, 12, "Lyon");
	a.ajouterRelais(14, 2, "Nantes");
	a.ajouterRelais(13, 14, "Paris");
	try {
		a.getRelais("Lyon").ajouterService("WC");
		a.getRelais("Lyon").ajouterService("Snake");
		a.getRelais("Nantes").ajouterService("Pain");
		a.getRelais("Nantes").ajouterService("Essence");
		a.getRelais("Paris").ajouterService("Pain");
		a.getRelais("Paris").ajouterService("Snake");
		
		
		
	} catch (RelaisException e) {}
	
	
	a.getRelais("Lyon").getServices("WC").ajouterPlage("02h13","18h55");
	a.getRelais("Lyon").getServices("WC").suprimerPlage("07h00","13h55");
	a.getRelais("Nantes").getServices("Pain").ajouterPlage("09h22","13h13");
	a.getRelais("Paris").getServices("Pain").ajouterPlage("10h00","21h30");
	
	a.afficherAnnuaire();
	
	a.rechercherRelais(13, 7, "WC");
	//a.editerUnRelais();
	//a.afficherAnnuaire();
	//a.editerUnRelais();
	}
}
