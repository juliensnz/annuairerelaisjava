package app;

import java.util.List;

public class Service {
	private String nom;
	private static int id = 0;
	private boolean[] dispo = new boolean[1440];
	public Service(String nom)
	{
		this.id++;
		this.nom = nom;
		for(int i=0;i<1440;i++)
		{
			getDispo()[i]=false;
		}
	}

	public String getNom() {
		return nom;
	}
	
	public void ajouterPlage(String heureDebut, String heureFin)
	{
		ajouterPlage(this.traduire(heureDebut),this.traduire(heureFin));
	}
	
	public void ajouterPlage(int minDebut,int minFin)
	{
		if(minDebut <= minFin)
		{
			
			for(int i = minDebut;i<minFin;i++)
			{
				getDispo()[i]=true;
			}
		}
		else
		{
			for(int i = minFin;i<minDebut;i++)
			{
				getDispo()[i]=true;
			}
		}
	}
	
	public void suprimerPlage(String heureDebut,String heureFin)
	{
		suprimerPlage(this.traduire(heureDebut),this.traduire(heureFin));
	}
	
	public void suprimerPlage(int minDebut,int minFin)
	{
		if(minDebut <= minFin)
		{
			for(int i = minDebut;i<minFin;i++)
			{
				getDispo()[i]=false;
			}
		}
		else
		{
			for(int i = minFin;i<minDebut;i++)
			{
				getDispo()[i]=false;
			}
		}
	}
	
	public void afficherPlage()
	{
		System.out.println(this.getPlage());
	}
	
	public String getPlage()
	{
		boolean etat = false;
		int ouvertureService = 0;
		String resultat = "";
		for(int i=0;i<getDispo().length;i++)
		{
			if(getDispo()[i] == true && etat == false)
			{
				ouvertureService = i;
			}
			else if(getDispo()[i] == false && etat == true)
			{
				resultat += " de "+this.traduire(ouvertureService)+" a "+this.traduire(i)+"";
			}
			etat = getDispo()[i];
		}
		resultat += ".";
		return resultat;
	}
	
	public String traduire(int time)
	{
		int heures = time/60;
		int minutes = time%60;
		String resultatHeures = ""+heures;
		String resultatMinutes = ""+minutes;
		if(heures < 10)
		{
			resultatHeures = "0"+heures;
		}
		if(minutes < 10)
		{
			resultatMinutes = "0"+minutes;
		}
		return ""+resultatHeures+"h"+resultatMinutes;
	}
	
	public int traduire(String time)
	{
		int heures = 0;
		int minutes = 0;
		int i = 0;
		while((int)time.charAt(i) >= (int)'0' && (int)time.charAt(i) <= (int)'9')
		{
			heures += ((int)time.charAt(i)-(int)'0')*Math.pow(10, 1-i);
			i++;
		}
		i++;
		while(i < time.length() && (int)time.charAt(i) >= (int)'0' && (int)time.charAt(i) <= (int)'9')
		{
			minutes += ((int)time.charAt(i)-(int)'0')*Math.pow(10, 4-i);
			i++;
		}
		return (heures*60+minutes);
	}
	
	public boolean[] getDispo() {
		return dispo;
	}
}
