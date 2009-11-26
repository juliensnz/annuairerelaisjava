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
		System.out.println("Un annuaire a été créé.");		
	}
	
	
	
	
	
	
	public void editer() {
		System.out.println("Edition du relais : " + this.nom);
		System.out.println("Que voulez vous éditer ?");
		System.out.println("1. Nom");
		System.out.println("2. Position X");
		System.out.println("3. Position Y");
		System.out.println("4. Services");
		System.out.println("5. Quitter l'editeur de relais");
		Scanner scan = new Scanner(System.in);
		int choixEdit = 0;
		System.out.print("Choix : ");
		choixEdit = scan.nextInt();
		scan.nextLine();
		switch (choixEdit) {
		case 1:
			System.out.print("Nom : ");
			this.nom = scan.nextLine();
			this.editer();
			break;
		case 2:
			System.out.print("X : ");
			this.positionX = scan.nextInt();
			this.editer();
			break;
		case 3:
			System.out.print("Y : ");
			this.positionY = scan.nextInt();
			this.editer();
			break;
		case 4:
			System.out.println("1. Ajouter un service");
			System.out.println("2. Editer un service");
			System.out.println("3. Supprimer un service");
			System.out.println("4. Quitter l'éditeur de service");
			System.out.print("Choix : ");
			choixEdit = scan.nextInt();
			switch (choixEdit) {
			case 1:
				System.out.println("Ajouter un service : ");
				System.out.print("Nom : ");
				scan.nextLine();
				String choixServ = scan.nextLine();
				try {
					this.ajouterService(choixServ);
				} catch (RelaisException e) {}
				this.editer();
				break;
			case 2:
				if (this.getNbService() == 0) {
					System.out.println("Le relais ne propose pas de service pour l'instant. Vous devez créer un service avant de pouvoir l'éditer");
					this.editer();
				} else {
					System.out.println("Editer un service : ");
					for (int i = 0; i < this.getNbService(); i++)
						System.out.println(i + 1 + ". " + this.services.get(i).getNom());
					System.out.print("Choix : ");
					choixEdit = scan.nextInt();
					this.services.get(choixEdit - 1).editer();
				}
				this.editer();
				break;
			case 3:
				if (this.getNbService() == 0) {
					System.out.println("Le relais ne propose pas de service pour l'instant. Vous devez créer des services avant de pouvoir en supprimer");
					this.editer();
				} else {
					System.out.println("Supprimer un service : ");
					for (int i = 0; i < this.getNbService(); i++)
						System.out.println(i + 1 + ". " + this.services.get(i).getNom());
					System.out.print("Choix : ");
					choixEdit = scan.nextInt();
					this.retirerService(this.services.get(choixEdit - 1));
				}
				this.editer();
				break;
			case 4:
				this.editer();
				break;
			default:
				break;
			}
			break;
		case 5:
			System.out.println("Retour au menu précédent");
			break;
		default:
			System.out.println("Retour au menu précédent");
		}
	}
	public void editer() {
		System.out.println("Editer un service :");
		
		System.out.println("1. Ajouter une plage horaire");
		System.out.println("2. Supprimer une plage horaire");
		System.out.println("3. Afficher les plages horaires");
		System.out.println("4. Quitter l'éditeur de services");
		System.out.print("Choix : ");
		Scanner sc = new Scanner(System.in);
		int entree = sc.nextInt();
		switch(entree) {
			case 1 :
					System.out.println("Ajouter une plage horaire (XXhYYmin) : ");
					System.out.print("De : ");
					String debut = sc.next();
					System.out.print("à : ");
					String fin = sc.next();
					this.ajouterPlage(debut,fin);
					this.editer();
				break;
			case 2 :
				System.out.println("Supprimer une plage horaire (XXhYYmin) : ");
				System.out.print("De : ");
				String debutSup = sc.next();
				System.out.print("à : ");
				String finSup = sc.next();
				this.supprimerPlage(debutSup,finSup);
				this.editer();
				break;
			case 3 :
				System.out.print("Plages d'ouverture : ");
				afficherPlage();
				this.editer();
				break;
			case 4 :
				System.out.println("Retour au menu précédent");
				break;
			default :
				System.out.println("Retour au menu précédent");
		}
	}//Editer un service.

	public void afficherRelais(boolean service) {
		System.out.println("- "+this.nom + " :\nAbscisse : " + this.positionX + "\nOrdonnee : " + this.positionY);
		if (service)
			afficherServices();
	}// Affiche un relais et ses services.

	public void afficherServices() {
		System.out.println("Services disponnibles :");
		for (Service s : this.services)
			System.out.println("\t- " + s.getNom() + " | Horaires :" + s.getPlage());
	}// Affiche les services d'un relais et leur horaires de disponibilité.
	public void afficherPlage(){
		System.out.println(this.getPlage());
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
		default :
			System.out.println("Fin des tests. Au revoir.");
			System.exit(0);
			break;	
		}
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
		System.out.println("2. Trouver le relais le plus proche.");
		System.out.println("3. Trouver un service a proximité.");
		System.out.println("4. Trouver les relais proposant un service");
		System.out.println("5. Distance d'un relais à la position actuelle");
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
					System.out.println("Le relais " + r.getNom() + " est à " + r.distance(positionX, positionY) + "km d'ici.\n" );
				}
				else
					System.out.println("Le relais n°"+entree+" n'existe pas, retour au menu précédent");
				break;
			default :
				break;
		}
		this.menuRelais(monAnnuaire);
	}
	
	public static void lancer() throws RelaisException{
		Console c = new Console();
	}
}
