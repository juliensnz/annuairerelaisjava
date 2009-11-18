package commande;

import java.util.Scanner;
import app.Annuaire;
import app.Relais;
import app.Service;
import exceptions.ConsoleException;

public class Console {
	Annuaire annuaire = null;
	public Console() throws ConsoleException
	{
		System.out.println("Bienvenue dans notre programme d'annuaire");
		System.out.println("Que voulez vous faire ?");
		annuaire = new Annuaire();
		menuPrincipal();
	}
	
	public void menuPrincipal()
	{
		System.out.println("1. Ajouter un relais");
		System.out.println("2. Editer un relais");
		System.out.println("3. Rechercher un service");
		int entree;
		do{
			System.out.print("Choix : ");
			entree = getInt();
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
						entree = getInt();
						entree--;
						}while(entree >= 0 && entree < this.annuaire.getNbRelais());
						System.out.println(entree);
						this.annuaire.getRelais(entree).editer();
						this.menuPrincipal();
						
					}
					break;
				case 3 :
					System.out.println("Ou vous trouvez vous ?");
					System.out.print("Position X :");
					int positionX = getInt();
					System.out.print("Position Y :");
					int positionY = getInt();
					System.out.println("Que voulez vous faire ?");
					System.out.println("1. Trouver les relais à proximité.");
					System.out.println("2. Trouver un service.");
					int choix = getInt();
					switch(choix)
					{
					case 1 :
						int i = 0;
						 for(Relais r : this.annuaire.getListRelais())
						 {
							 if(r.distance(positionX, positionY) < 5)
							 {
								 System.out.println("- "+r.getNom()+" Service : ");
								 for(Service s : r.getServices())
								 {
									 System.out.println("    "+s.getNom()+" Ouverture : "+s.getPlage());
								 }
							 }
							 i++;
						 }
					}
					System.out.println("Nom :");
					String nom = getString();
					this.annuaire.rechercherRelais(positionX,positionY,nom);
					
					this.menuPrincipal();
					break;
				default :
					System.out.println(entree);
			}
		} while(!(entree == 1 || entree == 2));
		
	}

	public void ajouterRelais()
	{
		System.out.println("Création d'un relais :");
		String nom = null;
			System.out.print("Nom : ");
		    nom = getString();
		
		System.out.print("Position X : ");
		int positionX = 0;
		
		 positionX = getInt();
		
		System.out.print("Position Y : ");
		int positionY = getInt();
		this.annuaire.ajouterRelais(positionX,positionY,nom);
	}
	
	public String getString()
	{
		Scanner sc = new Scanner(System.in);
		String entree = sc.next();
		return entree;
	}
	
	public int getInt()
	{
		Scanner sc = new Scanner(System.in);
		int entree = 0;
		try{
		entree = sc.nextInt();
		}catch(java.util.InputMismatchException e)
		{
			System.out.print("Veuillez entrer un nombre valide : ");
			return this.getInt();
		}
		return entree;
	}
	public String getHeure()
	{
		Scanner sc = new Scanner(System.in);
		String entree ;
		entree = sc.next();
		return entree;
	}

}
