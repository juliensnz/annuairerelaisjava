package commande;

import java.util.List;
import app.Annuaire;
import app.Relais;
import app.Service;
import exceptions.RelaisException;

public abstract class InterfaceAnnuaire extends Interface {

	public static void menuAnnuaire(Annuaire a) {
		System.out.println("Que voulez vous faire ?");
		System.out.println("1. Ajouter un relais");
		System.out.println("2. Editer un relais");
		System.out.println("3. Supprimer un relais");
		System.out.println("4. Rechercher un relais");
		System.out.println("5. Comparer deux relais");
		System.out.println("6. Afficher la liste des relais");
		System.out.println("7. Rechercher un service");
		System.out.println("Autre. Revenir au menu principal");
		System.out.print("Choix : ");
		int entree = Interface.getInt();
		switch (entree) {
			case 1:
				InterfaceAnnuaire.ajouterRelais(a);
				InterfaceAnnuaire.menuAnnuaire(a);
				break;
			case 2:
				if (a.isEmpty())
					System.out.println("L'annuaire est vide. Vous devez créŽer un relais avant de pouvoir l'Žéditer");
				else {
					try {
						InterfaceAnnuaire.editerRelais(a);
					}
					catch (RelaisException e) {
					}
				}
				InterfaceAnnuaire.menuAnnuaire(a);
				break;
			case 3:
				if (a.isEmpty())
					System.out.println("L'annuaire est vide. Vous devez crŽéer un relais avant de pouvoir l'éŽditer");
				else
					InterfaceAnnuaire.supprimerRelais(a);

				InterfaceAnnuaire.menuAnnuaire(a);
				break;
			case 4:
				InterfaceAnnuaire.trouverRelais(a);
				InterfaceAnnuaire.menuAnnuaire(a);
				break;
			case 5:
				InterfaceAnnuaire.comparerRelais(a);
				InterfaceAnnuaire.menuAnnuaire(a);
				break;
			case 6:
				System.out.println("1. Avec les services");
				System.out.println("2. Sans les services");
				System.out.println("Autre. Annuler");
				System.out.print("Choix : ");
				int entree2 = Interface.getInt();
				if (entree2 == 1)
					InterfaceAnnuaire.afficherAnnuaire(a, true, true);
				else if (entree2 == 2)
					InterfaceAnnuaire.afficherAnnuaire(a, true, false);

				InterfaceAnnuaire.menuAnnuaire(a);
				break;
			case 7:
				InterfaceAnnuaire.trouverService(a);
				InterfaceAnnuaire.menuAnnuaire(a);
				break;
			default:
				break;
		}
	}

	public static void afficherAnnuaire(Annuaire a, boolean coord, boolean serv) {
		System.out.println("Voici la liste des relais préŽsents dans l'annuaire : \n");
		int i = 1;
		for (Relais r : a.getListRelais()) {
			System.out.print(i + ". ");
			InterfaceRelais.afficher(r, coord, serv);
			i++;
		}
	}// Affiche tout l'annuaire

	public static void afficherAnnuaire(Annuaire a, Service s) {
		System.out.println("Voici la liste des relais prŽésents dans l'annuaire offrant le service " + s.getNom() + " : \n");
		for (Relais r : a.getListRelais())
			for (Service si : r.getServices())
				if (si.equals(s))
					InterfaceRelais.afficher(r, true, true);
		System.out.println("Fin de la liste");
	}// Affiche tous les relais de l'annuaire offrant le service s, comparaison

	// sur les noms

	public static void ajouterRelais(Annuaire a) {
		System.out.println("CréŽation d'un relais :");
		System.out.print("Nom : ");
		String nom = Interface.getString();

		System.out.print("Abscisse : ");
		int abs = Interface.getInt();

		System.out.print("Ordonnée : ");
		int ord = Interface.getInt();

		a.ajouterRelais(abs, ord, nom);
	}

	public static void editerRelais(Annuaire a) throws RelaisException {
		System.out.println("Choisissez le relais que vous voulez Žéditer : ");
		InterfaceAnnuaire.afficherAnnuaire(a, false, false);
		System.out.println("Autre. Annuler");
		System.out.print("Choix : ");
		int choix = Interface.getInt();
		if (choix > 0 && choix <= a.getNbRelais()) {
			choix--;
			InterfaceRelais.editerRelais(a.getRelais(choix));
		}
	}// Edition d'un relais de l'annuaire (prŽexistant).

	public static void supprimerRelais(Annuaire a) {
		InterfaceAnnuaire.afficherAnnuaire(a, false, false);
		System.out.println("Choisissez le relais que vous voulez supprimer : ");
		System.out.println("Autre. Annuler");
		System.out.print("Choix : ");
		int choix = Interface.getInt();
		if (choix > 0 && choix <= a.getNbRelais()) {
			choix--;
			a.supprimerRelais(choix);
			System.out.println("Le relais a bien ŽtŽ supprimŽ");
		}
	}// Supprimer un relais de l'annuaire

	public static void trouverRelais(Annuaire a) {
		System.out.println("Que voulez vous faire ?");
		System.out.println("1. Trouver les relais ˆ proximitéŽ");
		System.out.println("2. Trouver le relais le plus proche");
		System.out.println("3. Distance d'un relais ˆ la position actuelle");
		System.out.println("Autre. Annuler");
		System.out.print("Choix :");
		int choix = getInt();
		int x, y;
		System.out.print("Abscisse actuelle :");
		x = getInt();
		System.out.print("Ordonnée actuelle :");
		y = getInt();
		switch (choix) {
			case 1:
				InterfaceAnnuaire.trouverRayon(a, x, y);
				break;
			case 2:
				InterfaceAnnuaire.trouverProche(a.getListRelais(), x, y);
				break;
			case 3:
				InterfaceAnnuaire.afficherAnnuaire(a, false, false);
				System.out.print("Choix :");
				choix = getInt();
				if (choix > 0 && choix <= a.getNbRelais()) {
					choix--;
					Relais r = a.getRelais(choix);
					System.out.println("Le relais " + r.getNom() + "se situe à " + r.distance(x, y) + "de la position actuelle");
				}
				break;
			default:
				break;
		}
	}

	public static void comparerRelais(Annuaire a) {
		InterfaceAnnuaire.afficherAnnuaire(a, false, false);
		System.out.println("Autre. Annuler");
		int choix1, choix2;
		System.out.print("Comparer le relais n° ");
		choix1 = Interface.getInt();
		System.out.print("avec le relais n° ");
		choix2 = Interface.getInt();
		if (choix1 > 0 && choix1 <= a.getNbRelais() && choix2 > 0 && choix2 <= a.getNbRelais()) {
			choix1--;
			choix2--;
			if (a.getRelais(choix1).equals(a.getRelais(choix2)))
				System.out.println("Ces deux relais sont éŽgaux (proposent les mêmes services)\n");
			else
				System.out.println("Ces deux relais sont difféŽrents\n");
		}
	}

	public static void trouverRayon(Annuaire a, int x, int y) {
		System.out.print("Rayon de recherche : ");
		int rayon = getInt();
		int i = 0;
		for (Relais r : a.getListRelais()) {
			if (r.distance(x, y) < rayon) {
				InterfaceRelais.afficher(r, true, true);
				i++;
			}
		}
		if (i == 0)
			System.out.println("Aucun relais ne se trouve ˆ moins de " + rayon + "km");

		System.out.println("Retour au menu principal");
	}

	public static void trouverProche(List<Relais> l, int x, int y) {
		Relais plusProche = l.get(0);
		double min = plusProche.distance(x, y);
		for (Relais r : l)
			if (r.distance(x, y) < min)
				plusProche = r;
		System.out.println("Le relais le plus proche est : " + plusProche.getNom());
	}

	public static void trouverService(Annuaire a) {
		System.out.println("Que voulez vous faire ?");
		System.out.println("1. Trouver un service à proximitéŽ");
		System.out.println("2. Trouver les relais proposant un service");
		System.out.println("Autre. Quitter");
		System.out.print("Choix : ");
		int entree = Interface.getInt();

		System.out.print("Nom du service : ");
		Service s = new Service(Interface.getString());
		switch (entree) {
			case 1:
				InterfaceAnnuaire.serviceProche(a, s);
				break;
			case 2:
				System.out.println("Trouver tous les relais proposant un service");
				InterfaceAnnuaire.afficherAnnuaire(a, s);
				break;
			default:
				break;
		}
	}

	public static void serviceProche(Annuaire a, Service s) {
		int x, y;
		System.out.print("Abscisse actuelle :");
		x = getInt();
		System.out.print("Ordonnée actuelle :");
		y = getInt();
		a.rechercherRelais(x, y, s.getNom(), getCurrentTime());
	}
}
