package exceptions;

public class ConsoleException extends Exception{
		public ConsoleException()
		{
			System.out.println("Une erreur inconnue c'est produite.");
		}
		public ConsoleException(int type)
		{
			switch(type)
			{
			case 1 :
				System.out.println("Votre entrée n'est pas valide");
				try{
					
					System.out.print("Nom : ");
					String nom = sc.next();
				}
				catch(ConsoleException e)
				{
					e = new ConsoleException(1);
				}
				break;
			}
		}

}
