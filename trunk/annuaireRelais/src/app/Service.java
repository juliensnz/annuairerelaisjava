package app;

import java.util.List;

public class Service {
	private String nom;
	private static int id = 0;
	private List<Integer[]> dispo = null;
	public Service(String nom)
	{
		this.id++;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	public void ajouterPlage()
	{
		
	}
	
	public void ajouterPlage(int plage[])
	{
		if(plage.length == 2)
		{
			for(Integer[] i : this.dispo)
			{
				
			}
		}
	}
}
