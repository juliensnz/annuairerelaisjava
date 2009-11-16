package commande;

import java.util.Scanner;

import app.Annuaire;

public class Console {
	Annuaire annuaire = null;
	public Console()
	{
		System.out.println("Bienvunue dans notre programme d'annuaire");
		System.out.println("Que voulez vous faire ?");
		annuaire = new Annuaire();
		menuPrincipal();
	}
	
	public void menuPrincipal()
	{
		System.out.println("1. Ajouter un relais");
		System.out.println("2. Editer un relais");
		Scanner sc = new Scanner(System.in);
		int entree;
		do{
			System.out.print("Choix : ");
			entree = sc.nextInt();
			switch(entree) {
				case 1 :
					this.ajouterRelais();
					break;
				case 2 :
					for(int i = 0;i<this.annuaire.getNbRelais();i++)
					{
						System.out.println(i+1+". "+annuaire.getRelais(i));
					}
					break;
				default :
			}
		} while(!(entree == 1 || entree == 2));
		
	}

	public void ajouterRelais()
	{
		
	}

}
