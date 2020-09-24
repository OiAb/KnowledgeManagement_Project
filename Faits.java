class Faits
{

/* Declaration des variables **************************************************/
	int numFait;
	String descFait;
	Faits suivantFait;
	
	
/* Declaration des methodes ***************************************************/

	// * Fonction : Faits().
	// Cette fonction est le constructeur de la classe "Faits".
	Faits ()
	{
		numFait=0;	
		descFait=null;
		suivantFait=null;
	}


	/////////////////////////////////////////////////////////////// Les "gets" :
	
		// * Fonction : int getNum().
		// Cette fonction retourne le numero du fait.
		int getNum()
		{
			return numFait;	
		}
		
		// * Fonction : String getDesc().
		// Cette fonction retourne la description du fait.
		String getDesc()
		{
			return descFait;	
		}	
		
	
		// * Fonction : Faits getSuivant().
		// Cette fonction retourne le fait suivant.
		Faits getSuivant()
		{
			return suivantFait;	
		}
	
	
	/////////////////////////////////////////////////////////////// Les "sets" :
	
		// * Fonction : void setNum().
		// Cette fonction permet de modifier le num�ro du fait.
		void setNum(int I)
		{
			numFait=I;	
		}
	
		// * Fonction : void setDesc().
		// Cette fonction permet de modifier la description du fait.
		void setDesc(String S)
		{
			descFait=S;	
		}
		
		// * Fonction : void setSuivant(Faits element).
		// Cette fonction permet de modifier le fait suivant.
		void setSuivant(Faits F)
		{
			suivantFait=F;	
		}	
}