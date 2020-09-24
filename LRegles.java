class LRegles
{
	
/* Déclaration des variables **************************************************/
	Regles premier;
	int nbRegles;
	
	
/* Déclaration des méthodes ***************************************************/

	// * Fonction : LRegles().
	// Cette fonction est le constructeur de la classe "LRegles".
	LRegles()
	{
		premier=null;
		nbRegles=0;			
	}	
	

	/////////////////////////////////////////////////////////////// Les "gets" :
	
		// * Fonction : Regles getPremier().
		// Cette fonction retourne le premier élément de la liste.
		Regles getPremier()
		{
			return premier;	
		}
	
		// * Fonction : int getNbRegles().
		// Cette fonction retourne le nombre de régles.
		int getNbRegles()
		{
			return nbRegles;	
		}
		
		// * Fonction : Regles getRegle(int I).
		// Cette fonction retourne l'adresse de la regle entrée en paramétre.
		Regles getRegle(int I)
		{
			// Déclaration des variables.
			Regles rude=premier;
			
			// Regarde si la règle est présente dans la liste.
			if (!EstRegle(I))  
			{
				System.out.println("  --> Suppression impossible !");
			}
			else
			{
				// Début du traitement.
				while (rude.getNum()!=I)
				{
					rude=rude.getSuivant();		
				}				
			}
			return rude;	
		}

	/////////////////////////////////////////////////////////////// Les "sets" :
	
		// * Fonction : void setPremier(Regles R).
		// Cette fonction permet de modifier le premier élément de la liste.
		void setPremier(Regles R)
		{
			premier=R;	
		}
		
		// * Fonction : void setNbRegles(int I).
		// Cette fonction permet de modifier le nombre de régles.
		void setNbRegles(int I)
		{
			nbRegles = I;	
		}	
	
	
	////////////////////////////////////////////////////// Les autres méthodes :
	
		// * Fonction : void InitListe().
		// Cette fonction permet d'initialiser la liste.
		void InitListe()
		{
			premier = null;
			nbRegles = 0;			
		}

		// * Fonction : int EstRegle(int numRegle).
		// Cette fonction permet de vérifier si une régle fait partie de la liste.
		boolean EstRegle(int numRegle)
		{	
			// Déclaration des variables.
			Regles rude=premier;
			boolean resultat;
			
			// Début du traitement.
			while ((rude!=null) && (rude.getNum()!=numRegle))
			{
				 rude=rude.getSuivant();
			}
				 
			// En sortant, soit "rude" pointe sur l'élément recherché,
			// Soit "rude" est null.
			if (rude==null)
			{
				 System.out.println("");
				 System.out.println("  --> La Regles recherchee n'est pas dans la liste.");
				 System.out.println("");
				 resultat=false;		
			}
			else
			{
				 resultat=true;				 	
			}
			
			// Puis on retourne le résultat.
			return resultat;
		}
		
		
		// * Fonction : void Ajouter_Regle(Regles R).
		// Cette fonction permet d'ajouter une règle à la liste de règle.
		void Ajouter_Regle(Regles R)
		{	
			// Déclaration des variables.
			Regles rude;
			
			// Début du traitement.
			if (premier==null)
			{
				premier=R;
			}
			else
			{
				 rude=premier;
				 while ((rude.getSuivant()!=null) && (rude.getSuivant().getNum() < R.getNum()))
				 {
				 	rude=rude.getSuivant();
				 }
				 
				 // En sortant de la boucle,
				 // 		- Soit rude.getSuivant().getNum() > R.getNum().
				 // 		- Soit (rude.getSuivant() == null.
				 // Dans les deux cas, le traitement est le même.
				 
				 R.setSuivant(rude.getSuivant());
				 rude.setSuivant(R);
			}
			nbRegles++;
		}
		
		
		// * Fonction : void Suppr_Regle(int numRegle).				
		// Cette fonction permet de supprimer une régle de la liste.
		void Suppr_Regle(int numRegle)
		{	
			// Déclaration des variables.
			Regles rude;
			
			// Début du traitement.
			// Regarde si la règle est présente dans la liste.
			if (!EstRegle(numRegle))  
			{
				System.out.println("  --> Suppression impossible !");
			}
			else
			{
				 rude=premier;
				 // Suppression en début de liste.
				 if (rude.getNum()==numRegle)
				 {
				 	premier=rude.getSuivant();
				 	rude=null;
				 }
				 else
				 {
				 	// Suppression en milieu et fin de liste.
				 	while (rude.getSuivant().getNum()!=numRegle)
				 	{
				 		rude=rude.getSuivant();
				 	}
				  
				 	// En sortant, nous avons rude.getSuivant() qui pointe sur
				 	// L'élément à supprimer.
				 	
				 	rude.setSuivant(rude.getSuivant().getSuivant());
					rude = null;			 	
				}
			}
			nbRegles--;
		}
		
		
		// * Fonction : void Afficher_Regles().
		// Cette fonction affiche la liste de règles.
		void Afficher_Regles()
		{
			// Déclaration des variables.
			Regles rude;
			rude = premier;
			
			// Début du traitement.
			System.out.println("* Les regles :");
			System.out.println("");
			
			if (premier==null)
			{
			 	System.out.println("  --> La liste de regles est vide !");
			 	System.out.println("");	
			}
			else
			{
				System.out.println("   - Vous avez actuellement dans votre liste " + getNbRegles() + " regles.");
				System.out.println("");	
				System.out.println("   - Liste de regles :");
				System.out.println("");	
				
				while (rude != null)
				{
					System.out.println("     Regle numero " + rude.getNum() + ".");
					System.out.println("");
					System.out.println("        - Faits : ");
					rude.getLFait().Afficher_liste_fait();
					System.out.println("        - Consequences : ");
					rude.getLCons().Afficher_liste_fait();
					rude = rude.getSuivant();
				}					
			}
		}
		
		
		// * Fonction : void InitTeste().
		// Cette fonction réinitialise la variable EstTeste de chaque règle
		// A "false".
		void InitTeste()
		{
			Regles R;
			
			R = premier;
			
			while (R != null)
			{
				R.setTeste(false);
				R = R.getSuivant();	
			}
		}			
}