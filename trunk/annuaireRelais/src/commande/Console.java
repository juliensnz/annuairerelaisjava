package commande;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import exceptions.RelaisException;
import app.Annuaire;
import app.Relais;
import app.Service;

public class Console {
	List<Annuaire> listeAnnuaires = new ArrayList<Annuaire>();
	
	public Console() throws RelaisException {
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
		this.ajouterAnnuaire(annuaire);
		System.out.println("Bienvenue dans notre programme d'annuaire");
		System.out.println("Que voulez vous faire ?");
		this.menuPrincipal();
	}
	
	public void ajouterAnnuaire(Annuaire annuaire) {
		this.listeAnnuaires.add(annuaire);
		System.out.println("Un annuaire a �t� cr��.");		
	}

	public void menuRelais(Annuaire monAnnuaire) {
		System.out.println("1. Ajouter un relais");
		System.out.println("2. Editer un relais");
		System.out.println("3. Supprimer un relais");
		System.out.println("4. Rechercher un relais / un service");
		System.out.println("5. Comparer deux relais");
		System.out.println("6. Afficher la liste des relais");
		System.out.println("7. Quitter");
		System.out.print("Choix : ");
		int entree = getInt();
		switch(entree) {
			case 1 :
				this.ajouterRelais(monAnnuaire);
				this.menuRelais(monAnnuaire);
				break;
			case 2 :
				if(monAnnuaire.getNbRelais() == 0) {
					System.out.println("L'annuaire est vide. Vous devez cr�er un relais avant de pouvoir l'�diter");
					this.menuRelais(monAnnuaire);
				}
				else {	
					System.out.println("Quel relais souhaitez vous �diter ?");
					for(int i = 0; i < monAnnuaire.getNbRelais(); i++) {
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
					System.out.println("L'annuaire est vide. Vous devez cr�er des relais avant de pouvoir en supprimer");
					this.menuRelais(monAnnuaire);
				}
				else {	
					System.out.println("Quel relais souhaitez vous supprimer ?");
					for(int i = 0;i<monAnnuaire.getNbRelais();i++) {
						System.out.println(i+1+". "+monAnnuaire.getRelais(i).getNom());
					}
					System.out.println("Autre. Annuler");
					System.out.print("Choix : ");
					entree = getInt();
					if(entree > 0 && entree <= monAnnuaire.getNbRelais()){
						entree--;
						monAnnuaire.supprimerRelais(entree);
						System.out.println("Le relais a bien �t� supprim�");
					}
					this.menuRelais(monAnnuaire);
				}
				break;
			case 4 :
				this.trouverService(monAnnuaire);
				break;
			case 5 :
				monAnnuaire.afficherAnnuaire(false);
				System.out.println("Autre. Annuler");
				int choix1, choix2;
				System.out.print("Comparer le relais n�");
				choix1 = getInt();
				System.out.print("avec le relais n�");
				choix2 = getInt();
				if(choix1 > 0 && choix1 <= monAnnuaire.getNbRelais() && choix2 > 0 && choix2 <= monAnnuaire.getNbRelais()){
					choix1--;
					choix2--;
					if(monAnnuaire.getRelais(choix1).equals(monAnnuaire.getRelais(choix2)))
						System.out.println("Ces deux relais sont �gaux (proposent les m�mes services)\n");
					else
						System.out.println("Ces deux relais sont diff�rents\n");
				}
				this.menuRelais(monAnnuaire);
				break;
			case 6 :
				System.out.println("1. Avec les services");
				System.out.println("2. Sans les services");
				System.out.println("Autre. Annuler");
				
				int entree2;
				System.out.print("Choix : ");
				entree2 = getInt();
				if(entree2 == 1)
					monAnnuaire.afficherAnnuaire(true);
				else if (entree2 == 2)
					monAnnuaire.afficherAnnuaire(false);
				
				this.menuRelais(monAnnuaire);
				break;
			//Case 7 : Quitter, on passe dans le d�fault
			default :
				this.menuPrincipal();
				break;
		}	
	}

	public void menuPrincipal() {
		System.out.println("1. Ajouter un annuaire");
		System.out.println("2. Utiliser / Editer un annuaire");
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
				System.out.println((i+1) + ". Annuaire n�" + (i+1));
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
				System.out.println((i+1) + ". Annuaire n�" + (i+1));
			}
			System.out.println("Autre. Annuler");
			int choix1, choix2;
			System.out.print("Comparer l'annuaire n�");
			choix1 = getInt();
			System.out.print("avec l'annuaire n�");
			choix2 = getInt();
			if(choix1 > 0 && choix1 <= this.listeAnnuaires.size() && choix2 > 0 && choix2 <= this.listeAnnuaires.size()){
				choix1--;
				choix2--;
				if(this.listeAnnuaires.get(choix1).equals(this.listeAnnuaires.get(choix2)))
					System.out.println("Ces deux annuaires sont �gaux");
				else
					System.out.println("Ces deux annuaires sont diff�rents");
			}
			this.menuPrincipal();
			break;
		case 4:
			System.out.println("Fin des tests. Au revoir.");
			System.exit(0);
			break;
		default :
			System.out.println("Fin des tests. Au revoir.");
			System.exit(0);
			break;	
		}
	}

	public void ajouterRelais(Annuaire monAnnuaire)
	{
		System.out.println("Cr�ation d'un relais :");
		String nom = null;
		System.out.print("Nom : ");
		nom = getString();
		
		System.out.print("Abscisse : ");
		int positionX = 0;
		positionX = getInt();
		
		System.out.print("Ordonnee : ");
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
		System.out.println("1. Trouver les relais � proximit�.");
		System.out.println("2. Trouver le relais le plus proche.");
		System.out.println("3. Trouver un service a proximit�.");
		System.out.println("4. Trouver les relais proposant un service");
		System.out.println("5. Distance d'un relais � la position actuelle");
		System.out.println("Autre. Annuler");
		System.out.println("Choix :");
		int choix = getInt();
		switch(choix) {
			case 1 :
				System.out.print("Rayon de recherche : ");
				int rayon = getInt();
				int i = 0;
				int cpt = 0;
				 for(Relais r : monAnnuaire.getListRelais()) {
					 if(r.distance(positionX, positionY) < rayon) {
						 r.afficherRelais(true);
						 cpt++;
					 }
					 i++;
				 }
				 if(cpt == 0)
				 {
					 System.out.println("Aucun relais ne se trouve � moins de "+rayon+"km\nRetour au menu principal");
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
				String nom = getString();
				monAnnuaire.rechercherRelais(positionX,positionY,nom);
				break;
			case 4 :
				System.out.println("Trouver les relais proposant un service");
				System.out.print("Nom du service : ");
				String nom2 = getString();
				Service s = new Service(nom2);
				monAnnuaire.afficherAnnuaire(s);
				break;
			case 5:
				monAnnuaire.afficherAnnuaire(false);
				System.out.println("Autre. Annuler");
				System.out.println("Choix :");
				int entree = getInt();
				if(entree > 0 && entree <= monAnnuaire.getNbRelais()){
					entree--;
					Relais r = monAnnuaire.getRelais(entree);
					System.out.println("Le relais " + r.getNom() + " est � " + r.distance(positionX, positionY) + "km d'ici.\n" );
				}
				else
					System.out.println("Le relais n�"+entree+" n'existe pas, retour au menu pr�c�dent");
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
