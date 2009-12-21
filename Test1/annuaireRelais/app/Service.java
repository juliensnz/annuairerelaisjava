package app;

import commande.Interface;

public class Service implements Comparable<Service> {

	private String		nom;
	private static int	id		= 0;
	private boolean[]	dispo	= new boolean[1440];

	/**
	 * 	Constructeur simple initialisant le service. Le nom est entré en paramètre.
	 */
	public Service(String nom) {
		Service.id++;
		this.nom = nom;
		for (int i = 0; i < 1440; i++)
			this.dispo[i] = false;
	}

	/**
	 * 	Ajout d'une plage horaire au service. Les contrôles sont effectués lors de la saisie.
	 *  Les horaires sont exprimées en min/1440 (nb de minutes par 24h)
	 */
	public void ajouterPlage(int minDebut, int minFin) {
		int min = minDebut <= minFin ? minDebut : minFin;
		int max = minDebut <= minFin ? minFin : minDebut;

		for (int i = min; i < max; i++)
			this.dispo[i] = true;
	}

	/**
	 * 	Ajout d'une plage horaire au service. Les contrôles sont effectués lors de la saisie.
	 *  Les Horaires sont exprimés en HHhMM
	 */
	public void ajouterPlage(String heureDebut, String heureFin) {
		this.ajouterPlage(Service.traduire(heureDebut), Service.traduire(heureFin));
	}

	/**
	 * 	Supprimer une plage horaire du service. Les contrôles sont effectués lors de la saisie.
	 *  Les horaires sont exprimées en min/1440 (nb de minutes par 24h)
	 */
	public void supprimerPlage(int minDebut, int minFin) {
		int min = minDebut <= minFin ? minDebut : minFin;
		int max = minDebut <= minFin ? minFin : minDebut;

		for (int i = min; i < max; i++)
			this.dispo[i] = false;
	}

	/**
	 * 	Supprimer une plage horaire du service. Les contrôles sont effectués lors de la saisie.
	 *  Les Horaires sont exprimés en HHhMM
	 */
	public void supprimerPlage(String heureDebut, String heureFin) {
		this.supprimerPlage(Service.traduire(heureDebut), Service.traduire(heureFin));
	}

	/**
	 * Transforme une horaire de min/24h en HHhMM
	 */
	public static String traduire(int time) {
		int heures = time / 60;
		int minutes = time % 60;

		return (heures < 10 ? "0" + heures : "" + heures) + "h" + (minutes < 10 ? "0" + minutes : "" + minutes);
	}

	/**
	 * Traduire une horaire de HHhMM en min/24h
	 */
	public static int traduire(String time) {
		int heures, minutes;
		try {
			heures = Integer.parseInt(time.substring(0, time.indexOf("h")));
			minutes = Integer.parseInt(time.substring(time.indexOf("h") + 1, time.indexOf("h") + 3));
		}
		catch (NumberFormatException e) {
			return Interface.getCurrentTime();
		}
		return heures * 60 + minutes;
	}

	/**
	 * Comparaison avex un autre service.
	 */
	public int compareTo(Service o) {
		return this.getNom().compareTo(o.getNom());
	}

	/**
	 * Obtenir la disponibilité d'un service en fonction d'une horaire (min/24h)
	 */
	public boolean getDispo(int heure) {
		return this.dispo[heure];
	}

	/**
	 * Getter sur le nom du service
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Rtourne sous forme de chaine la liste des plages horaire de disponnibilité d'un service.
	 */
	public String getPlage() {
		boolean etat = false;
		int ouvertureService = 0;
		String resultat = "";

		for (int i = 0; i < this.dispo.length; i++) {
			ouvertureService = this.dispo[i] == true && etat == false ? i : ouvertureService;
			// Prend pour valeur vrai au déŽbut d'une plage horaire.
			resultat += this.dispo[i] == false && etat == true ? " [" + Service.traduire(ouvertureService) + " : " + Service.traduire(i) + "]" : "";
			// Se dŽéclenche a la fin d'une plage.
			etat = this.dispo[i];
		}
		if (!resultat.isEmpty())
			resultat += ".";

		return resultat;
	}
}
