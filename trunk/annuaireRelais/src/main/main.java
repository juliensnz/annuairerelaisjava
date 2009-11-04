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
	a.ajouterRelais(12, 12, "test");
	try {
		a.annuaireRelais.get(0).ajouterService("pain");
	} catch (RelaisException e) {}
	a.annuaireRelais.get(0).services.get(0).ajouterPlage("12h13","18h55");
	a.annuaireRelais.get(0).services.get(0).ajouterPlage("19h22","23h13");
	a.annuaireRelais.get(0).services.get(0).suprimerPlage("20h00","21h30");
	a.annuaireRelais.get(0).services.get(0).afficherPlage();
	//a.editerUnRelais();
	//a.afficherAnnuaire();
	//a.editerUnRelais();
	}
}
