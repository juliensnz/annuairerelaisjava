package commande;

import java.util.LinkedList;
import java.util.Scanner;
import exceptions.RelaisException;
import app.Annuaire;
import app.Relais;

public class Console {
	LinkedList<Annuaire> listeAnnuaires = new LinkedList<Annuaire>();
	
	public Console() throws RelaisException {
		System.out.println("Bienvenue dans notre programme d'annuaire");
		System.out.println("Que voulez vous faire ?");
		Annuaire annuaire = new Annuaire();
		annuaire.ajouterRelais(12, 6, "Nantes");
		annuaire.ajouterRelais(23, 45, "Nancy");
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
		this.ajouterAnnuaire(annuaire);
		this.menuPrincipal();
	}
	
	public void ajouterAnnuaire(Annuaire annuaire) {
		this.listeAnnuaires.add(annuaire);
		System.out.println("Un annuaire a été créé.");		
	}

	public void menuRelais(Annuaire monAnnuaire) {
		System.out.println("1. Ajouter un relais");
		System.out.println("2. Editer un relais");
		System.out.println("3. Supprimer un relais");
		System.out.println("4. Rechercher . . .");
		System.out.println("5. Quitter");
		int entree;
		
		System.out.print("Choix : ");
		entree = getInt();
		switch(entree) {
			case 1 :
				this.ajouterRelais(monAnnuaire);
				System.out.println("Relais créé \n");
				this.menuRelais(monAnnuaire);
				break;
			case 2 :
				if(monAnnuaire.getNbRelais() == 0) {
					System.out.println("L'annuaire est vide. Vous devez créer un relais avant de pouvoir l'éditer");
					this.menuRelais(monAnnuaire);
				}
				else {	
					System.out.println("Quel relais souhaitez vous éditer ?");
					for(int i = 0;i<monAnnuaire.getNbRelais();i++) {
						System.out.println(i+1+". "+monAnnuaire.getRelais(i).getNom());
					}
					System.out.println("Autre. Annuler");
					System.out.print("Choix : ");
					entree = getInt();
					if(entree > 0 && entree <= monAnnuaire.getNbRelais()){
						entree--;
						monAnnuaire.getRelais(entree).editer();
					}
					this.menuRelais(monAnnuaire);
				}
				break;
			case 3 :
				if(monAnnuaire.getNbRelais() == 0) {
					System.out.println("L'annuaire est vide. Vous devez créer des relais avant de pouvoir en supprimer");
					this.menuRelais(monAnnuaire);
				}
				else {	
					System.out.println("Quel relais souhaitez vous spprimer ?");
					for(int i = 0;i<monAnnuaire.getNbRelais();i++) {
						System.out.println(i+1+". "+monAnnuaire.getRelais(i).getNom());
					}
					System.out.println("Autre. Annuler");
					System.out.print("Choix : ");
					entree = getInt();
					if(entree > 0 && entree <= monAnnuaire.getNbRelais()){
						entree--;
						monAnnuaire.supprimerRelais(entree);
					}
					this.menuRelais(monAnnuaire);
				}
				break;
			case 4 :
				this.trouverService(monAnnuaire);
				break;
			default :
				this.menuPrincipal();
				break;
		}	
	}

	public void menuPrincipal() {
		System.out.println("1. Ajouter un annuaire");
		System.out.println("2. Editer un annuaire");
		System.out.println("3. Comparer deux annuaires");
		System.out.println("4. Quitter");
		
		int entree;
		System.out.print("Choix : ");
		entree = getInt();
		
		switch(entree){
		case 1:
			Annuaire a = new Annuaire();
			this.ajouterAnnuaire(a);
			this.menuPrincipal();
			break;
		case 2:
			for(int i = 0; i < this.listeAnnuaires.size(); i++){
				System.out.println((i+1) + ". Annuaire n°" + (i+1));
			}
			System.out.println("Autre. Annuler");
			System.out.print("Choix : ");
			entree = getInt();
			if(entree > 0 && entree <= this.listeAnnuaires.size()){
				entree--;
				this.menuRelais(this.listeAnnuaires.get(entree));
			}
			break;
		case 3:
			for(int i = 0; i < this.listeAnnuaires.size(); i++){
				System.out.println((i+1) + ". Annuaire n°" + (i+1));
			}
			System.out.println("Autre. Annuler");
			int choix1, choix2;
			System.out.print("Comparer l'annuaire n°");
			choix1 = getInt();
			System.out.print("avec l'annuaire n°");
			choix2 = getInt();
			System.out.println("Choix 1 : "+choix1+" Choix2 : "+choix2);
			if(choix1 > 0 && choix1 <= this.listeAnnuaires.size() && choix2 > 0 && choix2 <= this.listeAnnuaires.size()){
				choix1--;
				choix2--;
				if(this.listeAnnuaires.get(choix1).equals(this.listeAnnuaires.get(choix2)))
					System.out.println("Ces deux annuaires sont égaux");
				else
					System.out.println("Ces deux annuaires sont différents");
			}
			this.menuPrincipal();
			break;
		case 4:
			System.out.println("Fin des tests. Au revoir.");
			System.exit(0);
			break;
		}
	}

	public void ajouterRelais(Annuaire monAnnuaire)
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
		monAnnuaire.ajouterRelais(positionX,positionY,nom);
	}
	
	public void trouverService(Annuaire monAnnuaire)
	{
		System.out.println("Ou vous trouvez vous ?");
		System.out.print("Position X : ");
		int positionX = getInt();
		System.out.print("Position Y : ");
		int positionY = getInt();
		System.out.println("Que voulez vous faire ?");
		System.out.println("1. Trouver les relais à proximité.");
		System.out.println("2. Trouver le relais à le plus proche.");
		System.out.println("3. Trouver un service.");
		System.out.println("Autre. Annuler");
		int choix = getInt();
		switch(choix) {
			case 1 :
				System.out.print("Rayon de recherche : ");
				int rayon = getInt();
				int i = 0;
				int cpt = 0;
				 for(Relais r : monAnnuaire.getListRelais()) {
					 if(r.distance(positionX, positionY) < rayon) {
						 System.out.print("- "+r.getNom()+" , ");
						 r.afficherServices();
						 cpt++;
					 }
					 i++;
				 }
				 if(cpt == 0)
				 {
					 System.out.println("Aucun relais ne se trouve à moins de "+rayon+"km\nRetour au menu principal");
				 }
				 else
					 System.out.println("");
				 break;
			case 2 :
				Relais plusProche = monAnnuaire.getRelais(0);
				double min = plusProche.distance(positionX, positionY);
				for(Relais r : monAnnuaire.getListRelais()){
					if(r.distance(positionX, positionY) < min)
						plusProche = r;
				}
				System.out.println("Le relais le plus proche est : " + plusProche.getNom());
				break;
			case 3 :
				System.out.println("Trouver le relais le plus proche proposant un service");
				System.out.print("Nom du service : ");
				String nom;
				nom = getString();
				monAnnuaire.rechercherRelais(positionX,positionY,nom);
				break;
			default :
				break;
		}
		this.menuRelais(monAnnuaire);
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
