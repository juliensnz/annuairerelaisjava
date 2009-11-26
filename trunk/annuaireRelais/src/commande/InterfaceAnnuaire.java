package commande;

import app.Annuaire;
import app.Relais;
import app.Service;

public class InterfaceAnnuaire extends Interface {
	
	public static void ajouterRelais(Annuaire a) {
		System.out.println("CrŽation d'un relais :");
		System.out.print("Nom : ");
		String nom = getString();
		
		System.out.print("Abscisse : ");
		int abs = getInt();
		
		System.out.print("Ordonnee : ");
		int ord = getInt();
		
		a.ajouterRelais(abs, ord, nom);
	}
	
	public static void supprimerRelais(Annuaire a) {
		afficherAnnuaire(a, false, false);
		System.out.println("Choisissez le relais que vous voulez supprimer : ");
		System.out.println("Autre. Annuler");
		System.out.print("Choix : ");
		int choix = getInt();
		if(choix >= 0 && choix < a.getNbRelais()){
			a.supprimerRelais(choix);
			System.out.println("Le relais a bien ŽtŽ supprimŽ");
		}

	}//Supprimer un relais de l'annuaire
	
	public static void editerRelais(Annuaire a) {
		System.out.println("Choisissez le relais que vous voulez Žditer : ");
		afficherAnnuaire(a, false, false);
		System.out.println("Autre. Annuler");
		System.out.print("Choix : ");
		int choix = getInt();
		if(choix >= 0 && choix < a.getNbRelais());
			InterfaceRelais.editerRelais(a.getRelais(choix));
	}// Edition d'un relais de l'annuaire (prŽexistant).
	
	public static void afficherAnnuaire(Annuaire a, boolean coord, boolean serv) {
		System.out.println("Voici la liste des relais prŽsents dans l'annuaire : \n");
		int i = 1;
		for (Relais r : a.getListRelais()) {
			System.out.print(i+". ");
			InterfaceRelais.afficher(r, coord, serv);
			i++;
		}
	}// Affiche tout l'annuaire
	
	public static void afficherAnnuaire(Annuaire a, Service s) {
		System.out.println("Voici la liste des relais prŽsents dans l'annuaire offrant le service " + s.getNom() + " : \n");
		for (Relais r : a.getListRelais()) {
			for (Service si : r.getServices()) {
				if (si.equals(s))
					InterfaceRelais.afficher(r, true, true);
			}
		}
		System.out.println("Fin de la liste");
	}// Affiche tous les relais de l'annuaire offrant le service s, comparaison sur les noms
	
	public static void menuAnnuaire(Annuaire a) {
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
				ajouterRelais(a);
				break;
			case 2 :
				if(a.isEmpty())
					System.out.println("L'annuaire est vide. Vous devez crŽer un relais avant de pouvoir l'Žditer");
				else	
					editerRelais(a);
				break;
			case 3 :
				if(a.isEmpty())
					System.out.println("L'annuaire est vide. Vous devez crŽer un relais avant de pouvoir l'Žditer");
				else 
					supprimerRelais(a);
				break;
			case 4 :
				trouver(a);
				break;
			case 5 :
				monAnnuaire.afficherAnnuaire(false);
				System.out.println("Autre. Annuler");
				int choix1, choix2;
				System.out.print("Comparer le relais n¡");
				choix1 = getInt();
				System.out.print("avec le relais n¡");
				choix2 = getInt();
				if(choix1 > 0 && choix1 <= monAnnuaire.getNbRelais() && choix2 > 0 && choix2 <= monAnnuaire.getNbRelais()){
					choix1--;
					choix2--;
					if(monAnnuaire.getRelais(choix1).equals(monAnnuaire.getRelais(choix2)))
						System.out.println("Ces deux relais sont Žgaux (proposent les mmes services)\n");
					else
						System.out.println("Ces deux relais sont diffŽrents\n");
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
			//Case 7 : Quitter, on passe dans le dŽfault
			default :
				this.menuPrincipal();
				break;
		}
		menuAnnuaire(a);
	}

	private static void trouver(Annuaire a) {
		// TODO Auto-generated method stub
		
	}
}
