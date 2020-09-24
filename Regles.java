class Regles
{

/* D�claration des variables **************************************************/
	int numRegle;
	Regles suivantRegle;
	LFaits C;
	LFaits F;
	boolean EstTeste;
	

/* D�claration des m�thodes ***************************************************/	

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
		// Cette fonction retourne le num�ro de la r�gle.
		int getNum()
		{
			return numRegle;		
		}
	
		// * Fonction : Regles getSuivant().
		// Cette fonction retourne la r�gle suivante.
		Regles getSuivant()
		{
			return suivantRegle;	
		}
		
		// * Fonction : LFaits getLCons().
		// Cette fonction retourne la liste de cons�quences associ�e.
		LFaits getLCons()
		{
			return C;	
		}

		// * Fonction : LFaits getLFait().
		// Cette fonction retourne la liste de faits associ�e.
		LFaits getLFait()
		{
			return F;	
		}
		
		// * Fonction : boolean getTeste().
		// Cette m�thode retourne l'�tat de la variable EstTeste.
		boolean getTeste()
		{
			return EstTeste;		
		}


	/////////////////////////////////////////////////////////////// Les "sets" :
	
		// * Fonction : void setNum(int I).
		// Cette fonction permet de modifier le num�ro de la r�gle.
		void setNum(int I)
		{
			numRegle = I;		
		}	
		
		// * Fonction : void setSuivant(Regles R).
		// Cette fonction permet de modifier la r�gle suivante.
		void setSuivant(Regles R)
		{
			suivantRegle=R;	
		}
		
		// * Fonction : void setFait(LFaits L).
		// Cette fonction permet de modifier le liste de faits associ�e.
		void setLFait(LFaits L)
		{
			F=L;	
		}
		
		// * Fonction : void setCons(LFaits L).
		// Cette fonction permet de modifier le liste de cons�quences associ�e.
		void setLCons(LFaits L)
		{
			C=L;	
		}
		
		// * Fonction : void setTeste(boolean B).
		// Cette m�thode permet de modifier l'�tat de la variable EstTeste.
		void setTeste(boolean B)
		{
			EstTeste = B;		
		}
}