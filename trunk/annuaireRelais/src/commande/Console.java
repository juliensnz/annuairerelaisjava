package commande;

import java.util.Scanner;
import exceptions.RelaisException;
import app.Annuaire;
import app.Relais;

public class Console {
	Annuaire annuaire = null;
	public Console() throws RelaisException {
		System.out.println("Bienvenue dans notre programme d'annuaire");
		System.out.println("Que voulez vous faire ?");
		annuaire = new Annuaire();
		annuaire.ajouterRelais(12, 6, "Nantes");
		annuaire.ajouterRelais(2, 20, "Lyon");
		annuaire.ajouterRelais(5, 5, "Paris");
		annuaire.getRelais("Nantes").ajouterService("Pain");
		annuaire.getRelais("Nantes").getServices("Pain").ajouterPlage("08h00", "18h00");
		annuaire.getRelais("Nantes").ajouterService("Pizza");
		annuaire.getRelais("Nantes").getServices("Pizza").ajouterPlage("12h00", "19h00");
		annuaire.getRelais("Nantes").ajouterService("WC");
		annuaire.getRelais("Nantes").getServices("WC").ajouterPlage("06h00", "22h00");
		
		annuaire.getRelais("Lyon").ajouterService("Pain");
		annuaire.getRelais("Lyon").getServices("Pain").ajouterPlage("04h00", "12h00");
		annuaire.getRelais("Lyon").ajouterService("Essence");
		annuaire.getRelais("Lyon").getServices("Essence").ajouterPlage("11h00", "17h00");
		annuaire.getRelais("Lyon").ajouterService("WC");
		annuaire.getRelais("Lyon").getServices("WC").ajouterPlage("04h00", "23h00");
		
		annuaire.getRelais("Paris").ajouterService("Snack");
		annuaire.getRelais("Paris").getServices("Snack").ajouterPlage("09h00", "20h00");
		annuaire.getRelais("Paris").ajouterService("Essence");
		annuaire.getRelais("Paris").getServices("Essence").ajouterPlage("08h00", "19h23");
		annuaire.getRelais("Paris").ajouterService("WC");
		annuaire.getRelais("Paris").getServices("WC").ajouterPlage("06h00", "22h00");
		menuPrincipal();
	}
	
	public void menuPrincipal() {
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
					System.out.println("Relais cr�� \n");
					this.menuPrincipal();
					break;
				case 2 :
					if(this.annuaire.getNbRelais() == 0) {
						System.out.println("L'annuaire est vide. Vous devez cr�er un relais avant de pouvoir l'�diter");
						this.menuPrincipal();
					}
					else {	
						System.out.println("Quel relais souhaitez vous �diter ?");
						for(int i = 0;i<this.annuaire.getNbRelais();i++) {
							System.out.println(i+1+". "+annuaire.getRelais(i).getNom());
						}
						
						do {
						System.out.print("Choix : ");
						entree = getInt();
						}while(entree >= 0 && entree > this.annuaire.getNbRelais());
						entree--;
						this.annuaire.getRelais(entree).editer();
						
						this.menuPrincipal();
						
					}
					break;
				case 3 :
					this.trouverService();
					break;
				default :
			}
		} while(!(entree == 1 || entree == 2));
		
	}

	public void ajouterRelais()
	{
		System.out.println("Cr�ation d'un relais :");
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
	
	public void trouverService()
	{
		System.out.println("Ou vous trouvez vous ?");
		System.out.print("Position X : ");
		int positionX = getInt();
		System.out.print("Position Y : ");
		int positionY = getInt();
		System.out.println("Que voulez vous faire ?");
		System.out.println("1. Trouver les relais � proximit�.");
		System.out.println("2. Trouver un service.");
		int choix = getInt();
		switch(choix) {
			case 1 :
				int i = 0;
				int cpt = 0;
				 for(Relais r : this.annuaire.getListRelais()) {
					 if(r.distance(positionX, positionY) < 5) {
						 System.out.print("- "+r.getNom()+" , ");
						 r.afficherServices();
						 cpt++;
					 }
					 i++;
				 }
				 if(cpt == 0)
				 {
					 System.out.println("Aucun relais ne se trouve � moins de 5 km\nRetour au menu principal");
				 }
				 break;
			case 2 :
				System.out.println("Trouver le relais le plus proche proposant un service");
				System.out.print("Nom du service : ");
				String nom;
				nom = getString();
				this.annuaire.rechercherRelais(positionX,positionY,nom);
				break;
		}
		
		this.menuPrincipal();
		
	}
	
	public String getString() {
		Scanner sc = new Scanner(System.in);
		String entree = sc.nextLine();
		return entree;
	}
	
	public int getInt()
	{
		Scanner sc = new Scanner(System.in);
		int entree = 0;
		try{
			entree = sc.nextInt();
		}catch(java.util.InputMismatchException e) {
			System.out.print("Veuillez entrer un nombre valide : ");
			return this.getInt();
		}
		return entree;
	}
	public String getHeure() {
		Scanner sc = new Scanner(System.in);
		String entree ;
		entree = sc.next();
		return entree;
	}
	
	

}
