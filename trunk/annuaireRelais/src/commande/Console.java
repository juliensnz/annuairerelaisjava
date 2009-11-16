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
					System.out.println("Relais créé");
					this.menuPrincipal();
					break;
				case 2 :
					if(this.annuaire.getNbRelais() == 0)
					{
						System.out.println("l'annuaire est vide. Vous devez créer un relais avant de vouloir l'éditer");
						this.menuPrincipal();
					}
					else
					{	
						System.out.println("Quel relais souhaitez vous éditer ?");
						for(int i = 0;i<this.annuaire.getNbRelais();i++)
						{
							System.out.println(i+1+". "+annuaire.getRelais(i).getNom());
						}
						do{
						System.out.print("Choix : ");
						entree = sc.nextInt();
						entree--;
						}while(entree > 0 && entree < this.annuaire.getNbRelais());
						System.out.println(entree);
						this.annuaire.getRelais(entree).editer();
						this.menuPrincipal();
						
					}
					break;
				default :
					System.out.println(entree);
			}
		} while(!(entree == 1 || entree == 2));
		
	}

	public void ajouterRelais()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Création d'un relais :");
		System.out.print("Nom : ");
		String nom = sc.next();
		System.out.print("Position X : ");
		int positionX = sc.nextInt();
		System.out.print("Position Y : ");
		int positionY = sc.nextInt();
		this.annuaire.ajouterRelais(positionX,positionY,nom);
	}

}
