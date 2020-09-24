class Regles
{

/* Déclaration des variables **************************************************/
	int numRegle;
	Regles suivantRegle;
	LFaits C;
	LFaits F;
	boolean EstTeste;
	

/* Déclaration des méthodes ***************************************************/	

	// * Fonction : Regles().
	// Cette fonction est le constructeur de la classe "Regle".
	Regles()
	{
	 	numRegle = 0;
	 	suivantRegle = null;
	 	C = null;
	 	F = null;
	 	EstTeste = false;	
	}


	/////////////////////////////////////////////////////////////// Les "gets" :
	
		// * Fonction : int getNum().
		// Cette fonction retourne le numéro de la règle.
		int getNum()
		{
			return numRegle;		
		}
	
		// * Fonction : Regles getSuivant().
		// Cette fonction retourne la régle suivante.
		Regles getSuivant()
		{
			return suivantRegle;	
		}
		
		// * Fonction : LFaits getLCons().
		// Cette fonction retourne la liste de conséquences associée.
		LFaits getLCons()
		{
			return C;	
		}

		// * Fonction : LFaits getLFait().
		// Cette fonction retourne la liste de faits associée.
		LFaits getLFait()
		{
			return F;	
		}
		
		// * Fonction : boolean getTeste().
		// Cette méthode retourne l'état de la variable EstTeste.
		boolean getTeste()
		{
			return EstTeste;		
		}


	/////////////////////////////////////////////////////////////// Les "sets" :
	
		// * Fonction : void setNum(int I).
		// Cette fonction permet de modifier le numéro de la règle.
		void setNum(int I)
		{
			numRegle = I;		
		}	
		
		// * Fonction : void setSuivant(Regles R).
		// Cette fonction permet de modifier la règle suivante.
		void setSuivant(Regles R)
		{
			suivantRegle=R;	
		}
		
		// * Fonction : void setFait(LFaits L).
		// Cette fonction permet de modifier le liste de faits associée.
		void setLFait(LFaits L)
		{
			F=L;	
		}
		
		// * Fonction : void setCons(LFaits L).
		// Cette fonction permet de modifier le liste de conséquences associée.
		void setLCons(LFaits L)
		{
			C=L;	
		}
		
		// * Fonction : void setTeste(boolean B).
		// Cette méthode permet de modifier l'état de la variable EstTeste.
		void setTeste(boolean B)
		{
			EstTeste = B;		
		}
}