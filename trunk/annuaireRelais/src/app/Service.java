package app;

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
			resultat += (this.dispo[i] == false && etat == true) ? (" de "+this.traduire(ouvertureService)+" a "+this.traduire(i)+"") : "";
			etat = this.dispo[i];
		}
		resultat += ".";
		return resultat;
	}// Retourne les horaires d'ouverture d'un service sous forme de chaine.
	
	public String traduire(int time)
	{
		int heures = time/60;
		int minutes = time%60;
		
		return ((heures < 10) ? ("0"+heures) : (""+heures)) + "h" + ((minutes < 10) ? ("0"+minutes) : (""+minutes)) + "min";
	}//Transforme un temps en minutes (0 ˆ 1440) en XXhYYmin
	
	public int traduire(String time)
	{
		int heures = Integer.parseInt(time.substring(0, time.indexOf("h")));
		int minutes = Integer.parseInt(time.substring(time.indexOf("h") + 1, time.length()));
		
		return (heures * 60 + minutes);
	}//Transforme un temps en heures minutes XXhYYmin en entier (0 ˆ 1440)
	
	public boolean[] getDispo() {
		return dispo;
	}
	
	public String getNom() {
		return nom;
	}
}
