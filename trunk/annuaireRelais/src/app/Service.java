package app;

import java.util.Scanner;

import exceptions.RelaisException;

public class Service {
	private String nom;
	private static int id = 0;
	private boolean[] dispo = new boolean[1440];
	
	public Service(String nom)
	{
		Service.id++;
		this.nom = nom;
		for(int i=0;i<1440;i++)
		{
			this.dispo[i]=false;
		}
	}//Constructeur
	

	public void ajouterPlage(int minDebut,int minFin)
	{
		int min = (minDebut <= minFin) ? minDebut : minFin;
		int max = (minDebut <= minFin) ? minFin : minDebut;
		
		for(int i = min; i < max; i++)
		{
			this.dispo[i]=true;
		}
	}//On ajoute une plage horaire, valeurs controlŽes lors de la saisie, valeurs en minutes de 0 ˆ 1440.
	
	public void ajouterPlage(String heureDebut, String heureFin)
	{
		ajouterPlage(this.traduire(heureDebut),this.traduire(heureFin));
	}//Ajout d'une plage avec des valeurs formatŽes en XXhYYmin
	
	

	
	public void supprimerPlage(int minDebut,int minFin)
	{
		int min = (minDebut <= minFin) ? minDebut : minFin;
		int max = (minDebut <= minFin) ? minFin : minDebut;
		
		for(int i = min; i < max; i++)
		{
			this.dispo[i]=false;
		}
	}//Supprimer une plage horaire, valeurs en minutes de 0 ˆ 1440
	
	public void supprimerPlage(String heureDebut,String heureFin)
	{
		supprimerPlage(this.traduire(heureDebut),this.traduire(heureFin));
	}//Suppression d'une plage avec des valeurs formatŽes en XXhYYmin
	
	public void afficherPlage()
	{
		System.out.println(this.getPlage());
	}
	
	public String getPlage()
	{
		boolean etat = false;
		int ouvertureService = 0;
		String resultat = "";
		for(int i = 0; i < this.dispo.length; i++)
		{
			ouvertureService = (this.dispo[i] == true && etat == false) ? i : ouvertureService;
			resultat += (this.dispo[i] == false && etat == true) ? ("["+this.traduire(ouvertureService)+" : "+this.traduire(i)+"] ") : "";
			etat = this.dispo[i];
		}
		resultat += ".";
		return resultat;
	}// Retourne les horaires d'ouverture d'un service sous forme de chaine.
	
	public String traduire(int time)
	{
		int heures = time/60;
		int minutes = time%60;
		
		return ((heures < 10) ? ("0"+heures) : (""+heures)) + "h" + ((minutes < 10) ? ("0"+minutes) : (""+minutes));
	}//Transforme un temps en minutes (0 ˆ 1440) en XXhYYmin
	
	public int traduire(String time)
	{
		int heures = Integer.parseInt(time.substring(0, time.indexOf("h")));
		int minutes = Integer.parseInt(time.substring(time.indexOf("h") + 1, time.length()));
		
		return (heures * 60 + minutes);
	}//Transforme un temps en heures minutes XXhYYmin en entier (0 ˆ 1440)
	
	
	public void editer()
	{
		System.out.println("Editer un service :");
		
		System.out.println("1. Ajouter une plage horaire");
		System.out.println("2. Supprimer une plage horaire");
		System.out.println("3. Afficher les plages horaires");
		System.out.print("Choix : ");
		Scanner sc = new Scanner(System.in);
		int entree = sc.nextInt();
		switch(entree)
		{
			case 1 :
					System.out.println("Ajouter une plage horaire : ");
					System.out.print("De : ");
					String debut = sc.next();
					System.out.print("ˆ : ");
					String fin = sc.next();
					this.ajouterPlage(debut,fin);
					this.editer();
				break;
			case 2 :
				System.out.println("Supprimer une plage horaire : ");
				System.out.print("De : ");
				String debutSup = sc.next();
				System.out.print("ˆ : ");
				String finSup = sc.next();
				this.supprimerPlage(debutSup,finSup);
				this.editer();
				break;
			case 3 :
				System.out.print("Plages d'ouverture : ");
				afficherPlage();
				this.editer();
				break;
		}
	}
	
	public boolean[] getDispo() {
		return dispo;
	}
	
	public String getNom() {
		return nom;
	}
}
