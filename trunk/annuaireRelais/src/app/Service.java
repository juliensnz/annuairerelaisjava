package app;

import java.util.List;

public class Service {
	private String nom;
	private static int id = 0;
	private boolean[] dispo ;
	public Service(String nom)
	{
		this.id++;
		this.nom = nom;
		dispo = new boolean[1440];
		for(int i=0;i<1440;i++)
		{
			dispo[i]=false;
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
				dispo[i]=true;
			}
		}
		else
		{
			for(int i = minFin;i<minDebut;i++)
			{
				dispo[i]=true;
			}
		}
	}
	
	public void suprimerPlage(int minDebut,int minFin)
	{
		if(minDebut <= minFin)
		{
			for(int i = minDebut;i<minFin;i++)
			{
				dispo[i]=false;
			}
		}
		else
		{
			for(int i = minFin;i<minDebut;i++)
			{
				dispo[i]=false;
			}
		}
	}
	
	public void afficherPlage()
	{
		boolean etat = false;
		int ouvertureService = 0;
		for(int i=0;i<dispo.length;i++)
		{
			if(dispo[i] == true && etat == false)
			{
				ouvertureService = i;
			}
			else if(dispo[i] == false && etat == true)
			{
				System.out.println("de "+this.traduire(ouvertureService)+" a "+this.traduire(i));
			}
			
			etat = dispo[i];
		}
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
		while((int)time.charAt(i) >= (int)'0' && (int)time.charAt(i) < (int)'9')
		{
			heures += ((int)time.charAt(i)-(int)'0')*Math.pow(10, i);
			i++;
		}
		i++;
		while(i < time.length() && (int)time.charAt(i) >= (int)'0' && (int)time.charAt(i) < (int)'9')
		{
			minutes += (int)time.charAt(i)-(int)'0';
			minutes *= 10;
			i++;
		}
		return heures*60+minutes;
	}
}
