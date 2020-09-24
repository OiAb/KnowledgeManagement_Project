class LFaits
{
	
/* D�claration des variables **************************************************/
	Faits premier;
	int nbfaits;

	
/* D�claration des m�thodes ***************************************************/

	// * Fonction : LRegles().
	// Cette fonction est le constructeur de la classe "LRegles".
	LFaits()
	{
		premier=null;
		nbfaits=0;
	}
	
	
	/////////////////////////////////////////////////////////////// Les "gets" :

		// * Fonction : Faits getPremier().
		// Cette fonction retourne le premier element de la liste.
		Faits getPremier()
		{
			return premier;	
		}
		
		// * Fonction : int getNbFaits().
		// Cette fonction retourne le nombre de faits que contient la liste.
		int getNbFaits()
		{
			return nbfaits;	
		}
		
		// * Fonction : Faits getFait(int numFait).
		// Cette fonction retourne l'adresse du fait entr� en param�tre.
		Faits getFait(int numFait)
		{	
			// D�claration des variables.
			Faits rude=premier;
			Faits resultat;
			
			// D�but du traitement.
			while ((rude!=null) && (rude.getNum()!=numFait))
			{
				 rude=rude.getSuivant();
			}
				 
			// En sortant, soit "rude" pointe sur l'�l�ment recherch�,
			// Soit "rude" est null.
			if (rude==null)
			{
				 System.out.println("  --> Le fait recherche n'est pas dans la liste");
				 resultat=null;		
			}
			else
			{
				 resultat=rude;				 	
			}
			
			// Puis on retourne le r�sultat.
			return resultat;
		}
	
	
	/////////////////////////////////////////////////////////////// Les "sets" :

		// * Fonction : void setPremier(Faits element).
		// Cette fonction permet de modifier le premier element de la liste.
		void setPremier(Faits element)
		{
			premier=element;	
		}
		
		// * Fonction : void setNbFaits(int I).
		// Cette fonction permet de modifier le nombre de faits.
		void setNbFaits(int I)
		{
			nbfaits = I;	
		}
	
	////////////////////////////////////////////////////// Les autres m�thodes :

		// * Fonction : void InitListe().
		// Cette fonction permet d'initialiser la liste de faits.
		void InitListe()
		{
			premier=null;
			nbfaits=0;			
		}
	
	
		// * Fonction : void Afficher_liste_fait().
		// Cette fonction affiche la liste de faits.
		void Afficher_liste_fait()
		{
			Faits rude;
			
			if (premier == null)
			{
				System.out.println("           Vide ...");		
			}
			else
			{
				rude = premier;
				while (rude != null)
				{
					System.out.print("           Num(" + rude.getNum() + ") : ");
					System.out.println(rude.getDesc() + ".");
					rude = rude.getSuivant();
				}			
			}
			System.out.println();
		}
		
		
		// * Fonction : boolean EstFait(int num_fait).
		// Cette fonction permet de regarder si un fait appartient � la liste.
		boolean EstFait(int num_fait)
		{	
			// D�claration des variables.
			Faits rude=premier;
			boolean resultat;
			
			// D�but du traitement.
			while ((rude!=null) && (rude.getNum()!=num_fait))
			{
				 rude=rude.getSuivant();
			}
				 
			// En sortant, soit "rude" pointe sur l'�l�ment recherch�,
			// Soit "rude" est null.
			if (rude==null)
			{
				 System.out.println("  --> Le fait recherche n'est pas dans la liste");
				 resultat=false;		
			}
			else
			{
				 resultat=true;				 	
			}
			
			// Puis on retourne le r�sultat.
			return resultat;
		}
	
	
		// * Fonction : void AjouterFait(Faits element).
		// Cette fonction permet d'ajouter un fait � la liste {Avec ordre}.
		void AjouterFait(Faits element)
		{	
			// D�claration des variables.
			Faits rude;
			
			// D�but du traitement.
			if (premier==null)
			{
				premier=element;
			}
			else
			{
				 rude=premier;
				 while ((rude.getSuivant()!=null) && (rude.getSuivant().getNum() < element.getNum()))
				 {
				 	rude=rude.getSuivant();
				 }
				 
				 // En sortant de la boucle,
				 // 		- Soit rude.getSuivant().getNum() > element.getNum().
				 // 		- Soit rude.getSuivant() == null.
				 // Dans les deux cas, le traitement est le m�me.
				 
				 element.setSuivant(rude.getSuivant());
				 rude.setSuivant(element);
			}
			
			nbfaits++;	
		}
	
	
		// * Fonction : void SupprFait(int element).
		// Cette fonction permet de supprimer un fait de la liste.
		void SupprFait(int num_fait)
		{	
			// D�claration des variables.
			Faits rude;
			
			// D�but du traitement.
			if (!EstFait(num_fait))
			{
				System.out.println(" --> Suppression impossible");
			}
			else
			{
				 rude=premier;
				 
				 // Suppression en d�but de liste.
				 if (rude.getNum()==num_fait)
				 {
				 	premier=rude.getSuivant();
				 	rude=null;
				 }
				 else
				 {
				 	// Suppression en milieu et fin de liste.
				 	while (rude.getSuivant().getNum()!=num_fait)
				 	{
				 		rude=rude.getSuivant();
				 	}
				  
				 	// En sortant, nous avons getSuivant(rude) qui pointe sur
				 	// L'�l�ment � supprimer.
				 	
				 	rude.setSuivant(rude.getSuivant().getSuivant());
				 	rude = null;			 	
				}
			}
			nbfaits--;
		}
		
		
		// * Fonction : void InsertListe(LFaits L, String strTmp);
		// Cette fonction permet d'ins�rer des faits dans une liste de faits �
		// Partir du String entr� en param�tre de type F1;F2;F3.
		void InsertListe(LFaits LF, String S)
		{
			Faits F;
			int numTmp=0, numTmp2;
			char charTmp;
			String strTmp="";
			
			while(numTmp<S.length())
			{
				// On r�cup�re le carat�re.
				charTmp = S.charAt(numTmp);
	
				if ((charTmp!=';') && (charTmp!=' '))
				{
					// On r�alise une concat�nation.
					strTmp=strTmp+charTmp;									
				}
				else
				{
					// Conversion en entier.
					numTmp2=Integer.parseInt(strTmp);	
	
					if (LF.EstFait(numTmp2))
					{
						// On cr�e le nouvel �l�ment.
						F = new Faits();
		
						// Affectation des propri�t�s.
						F.setNum(numTmp2);
						F.setDesc(LF.getFait(numTmp2).getDesc());
						
						// On ajoute le fait cr�� � la liste de faits.
						AjouterFait(F);
					}
					// On r�initialise la chaine de caract�re.
					strTmp = "";									
				}
				numTmp++;
			}	
		}
		
		
		// * Fonction : void DeleteListe(LFaits L, String strTmp);
		// Cette fonction permet de supprimer des faits dans une liste de faits �
		// Partir du String entr� en param�tre de type F1;F2;F3.
		void DeleteListe(String S)
		{
			int numTmp=0, numTmp2;
			char charTmp;
			String strTmp="";
	
			while(numTmp<S.length())
			{
				// On r�cup�re le carat�re.
				charTmp=S.charAt(numTmp);
	
				if ((charTmp!=';') && (charTmp!=' '))
				{
					// On r�alise une concat�nation.
					strTmp=strTmp+charTmp;									
				}
				else
				{
					// Conversion en entier.
					numTmp2=Integer.parseInt(strTmp);	
	
					// On supprime l'�l�ment.
					SupprFait(numTmp2);
	
					// On r�initialise la chaine de caract�re.
					strTmp="";									
				}
				numTmp++;
			}	
		}		
}