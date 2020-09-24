import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/***************************************************************** AResultats */
/**************************************************** Affichage des resultats */

class AResultats extends JFrame
{
	AResultats(int[][] Matrice, LRegles LR, LFaits LF)
	{
		// Proprietes de la fenetre.
		setTitle(".: Les résultats");
		setBounds(45,115, 400, 621);
		
		// Cr�ation du panneau.
		pan = new pan_AResultats(Matrice, LR, LF);
		
		// Cr�ation de la barre de d�filement.
		defil = new JScrollPane(pan);
		getContentPane().add(defil);
	}
	
	private pan_AResultats pan;
	private JScrollPane defil;
	Dimension d;
	
	
	// * Fonction : setVoir(boolean B).
	// Cette fonction permet de rendre visible ou non le JFrame.
	void setVoir(boolean B)
	{
		if (B)
		{
			setVisible (true);
		}
		else
		{
			setVisible (false);	
		}	
	}

	// * Fonction : void setNbSave(int I).
	// Cette fonction permet de modifier nbSaves.
	void setNbSaves(int I)
	{
		pan.setNbSaves(I);	
	}	
}


class pan_AResultats extends JPanel
{
	// Declaration des variables.
	int[][] Mat;
	int nbSaves;
	LFaits LF;
	LRegles LR;
	
	// Constructeur.
	public pan_AResultats(int[][] Matrice, LRegles L1, LFaits L2)
	{
		Mat	= Matrice;
		LR = L1;
		LF = L2;
	}
	

	// Fonction appelee lors d'un rafraichissement.
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		// Fonction d'ecriture.
		Ecrire(Mat, nbSaves, LR, LF, g);
	}
	
	
	// * Fonction : void setMat(int[][] M).
	// Cette fonction permet de modifier la matrice Mat.
	void setMat(int[][] M)
	{
		Mat = M;
	}
	
	
	// * Fonction : void setNbSave(int I).
	// Cette fonction permet de modifier nbSaves.
	void setNbSaves(int I)
	{
		nbSaves = I;	
	}
	
	
	// * Fonction : void setLF(LFaits L).
	// Cette fonction permet de modifier LF.
	void setLF(LFaits L)
	{
		LF = L;	
	}
	
	
	// * Fonction : void Ecrire(String txt, int x, int y, Color C, Graphics g).
	// Cette fonction permet d'ecrire sur le panneau "pan_res".
	void Ecrire(int[][] Mat, int nbSaves, LRegles LR, LFaits LF, Graphics g)
	{
		// Declaration des variables.
		Regles R;
		LFaits LFts;
		Faits F;
		int xligne=30;
		int yligne=20;		
		int i, tmp=1, nbRech=1;
		FontMetrics fm;
		
		g.setColor (Color.black);
		g.drawString (".: Les résultats :", xligne, yligne);
		xligne = xligne + 20;
		yligne = yligne + 20;
		
		for (i=0; i<nbSaves; i++)
		{
			/// Si changement de faits {Initiaux/Finaux} -> Gestion des titres :
			if (tmp != Mat[1][i])
			{
				// Si faits initiaux.
				if ((Mat[1][i] == 0) || (Mat[1][i] == 1))
				{
					tmp = Mat[1][i];
					g.setColor (Color.white);
					xligne = 50;
					
					g.drawString ("------------------------------------------------------------", xligne, yligne);
					yligne = yligne + 20;
					
					g.setColor (Color.black);
					g.drawString ("Recherche n°", xligne, yligne);
					xligne = xligne + 80;
					
					g.setColor (Color.blue);
					g.drawString ("" + nbRech, xligne, yligne);
					
					g.setColor (Color.white);
					if (Mat[1][i] == 0)
					{
						xligne = 200;
						g.drawString ("Chainage avant.", xligne, yligne);
					}
					else
					{
						if (Mat[1][i] == 1)
						{
							xligne = 200;
							g.drawString ("Chainage arrière.", xligne, yligne);
						}						
					}
					
					yligne = yligne + 25;
					xligne = 50;				
					g.setColor (Color.gray);
					g.drawString ("Liste de faits initiaux :", xligne, yligne);
					yligne = yligne + 20;
					nbRech++;
				}
				else  // Si faits finaux {Regles}.
				{
					tmp = Mat[1][i];
					g.setColor (Color.gray);
					yligne = yligne + 10;
					xligne = 50;
					g.drawString ("Liste de faits résultats :", xligne, yligne);
					yligne = yligne + 20;	
				}		
			}
			

			///////////////////////////////////// Affichage des faits initiaux :
			if ((Mat[1][i] == 0) || (Mat[1][i] == 1))
			{
				xligne = 70;
				
				g.setColor (Color.black);
				g.drawString ("Fait n°", xligne, yligne);
				xligne = xligne + 37;
				
				g.setColor (Color.blue);
				g.drawString ("" + Mat[0][i], xligne, yligne); 
	
				// Calcul de xligne par rapport au num�ro.
				fm = g.getFontMetrics();
				xligne += fm.stringWidth ("" + Mat[0][i]);
				
				g.setColor (Color.black);
				g.drawString (" : ", xligne, yligne);
				xligne = xligne + 10; 
				
				if (LF.EstFait(Mat[0][i]))  // Si le fait existe. 
				{
					g.setColor (Color.gray);
					g.drawString ("" + LF.getFait(Mat[0][i]).getDesc(), xligne, yligne);
				}
				else  // Sinon, on le d�clare vide.
				{
					g.setColor (Color.red);
					g.drawString ("Vide" , xligne, yligne);
				}
				
				yligne = yligne + 15;
			}
			
			
			////////////////////////////// Affichage des regles {Faits finaux} :
			if ((Mat[1][i] == 2) || (Mat[1][i] == 3))
			{
				xligne = 70;
				g.setColor (Color.black);
				g.drawString ("Règle n°", xligne, yligne);
				xligne = xligne + 50;
				
				g.setColor (Color.blue);
				g.drawString ("" + Mat[0][i], xligne, yligne); 	
				
				// Calcul de xligne par rapport au num�ro.
				fm = g.getFontMetrics();
				xligne += fm.stringWidth ("" + Mat[0][i]);	
				
				g.setColor (Color.black);
				g.drawString (" : ", xligne, yligne);
				xligne = xligne + 10;
				
				
				//////////////////////////////////////////// Si chainage avant : 
				////////////////////////////////////////////////////////////////
				if (Mat[1][i] == 2)
				{
					R = LR.getRegle(Mat[0][i]);  // Affectation de la règle "rèelle".
					LFts = R.getLFait();  // Affectation de la liste de faits Initiale.
	 				F = LFts.getPremier();  // Affectation du premier fait de cette liste.
	 				
	 				while (F.getSuivant()!=null)
	 				{
						g.setColor (Color.green);
						g.drawString ("" + F.getNum(), xligne, yligne);
					
						fm = g.getFontMetrics();
						xligne += fm.stringWidth ("" + F.getNum());				
						
						g.setColor (Color.gray);
						g.drawString (" et ", xligne, yligne);
					 	xligne = xligne + 16;
					 						
	 					F = F.getSuivant();
	 				}
	 				
	 				g.setColor (Color.green);
	 				g.drawString ("" + F.getNum(), xligne, yligne);
					
					fm = g.getFontMetrics();
					xligne += fm.stringWidth ("" + F.getNum()); 								

	 				g.setColor (Color.gray);
	 				g.drawString (" = ", xligne, yligne);
					xligne = xligne + 12;
	 				
	 				// A partir d'ici, tous les faits initiaux sont notés.
					// Il ne reste plus qu'à notés les faits conclusions / finaux.
					
					LFts = R.getLCons();  // Affectation de la liste de faits finale.
	 				F = LFts.getPremier();  // Affectation du premier fait de cette liste. 				
	 				
	  				while (F.getSuivant()!=null)
	 				{
						g.setColor (Color.red);
						g.drawString ("" + F.getNum(), xligne, yligne);
					
						fm = g.getFontMetrics();
						xligne += fm.stringWidth ("" + F.getNum());				
	
						g.setColor (Color.gray);
						g.drawString (" ou ", xligne, yligne);
					 	xligne = xligne + 19;
					 											
	 					F = F.getSuivant();	
	 				}
	 
	 				g.setColor (Color.red);
	 				g.drawString ("" + F.getNum(), xligne, yligne); 

					fm = g.getFontMetrics();
					xligne += fm.stringWidth ("" + F.getNum());	
								 				
		 			g.setColor (Color.gray);
		 			g.drawString (".", xligne, yligne);
	 				
	 				yligne = yligne + 20;	
	 				
	 				// Affichage des descriptions.
		 				xligne = 70;
		 				g.setColor (Color.gray);
		 				g.drawString ("Avec :", xligne, yligne);
		 				
		 				F = LFts.getPremier();  // Affectation du premier fait de cette liste. 				
		  				while (F!=null)
		 				{
							xligne = 110;
							
							g.setColor (Color.gray);
							g.drawString ("Fait n°", xligne, yligne);
							xligne = xligne + 37;

							g.setColor (Color.white);
							g.drawString ("" + F.getNum(), xligne, yligne);
							
							fm = g.getFontMetrics();
							xligne += fm.stringWidth ("" + F.getNum());							
							
							g.setColor (Color.gray);
							g.drawString (" : ", xligne, yligne);
							xligne = xligne + 10; 
							
							if (LF.EstFait(Mat[0][i]))  // Si le fait existe. 
							{
								g.setColor (Color.white);
								g.drawString ("" + F.getDesc() + ".", xligne, yligne);
							}
							else  // Sinon, on le déclare vide.
							{
								g.setColor (Color.red);
								g.drawString ("Vide." , xligne, yligne);
							}
							
							yligne = yligne + 15;
						 											
		 					F = F.getSuivant();	
		 				}	 					
		 				
		 				xligne = 70;
		 				g.setColor (Color.gray);
		 				g.drawString ("-------------------------------------------", xligne, yligne);
		 				yligne = yligne + 20;				
				}
				else
				{		
					if (Mat[1][i] == 3) ////////////////// Si chainage arrière :
					{ //////////////////////////////////////////////////////////
					
						R = LR.getRegle(Mat[0][i]);  // Affectation de la règle "réelle".
						LFts = R.getLCons();  // Affectation de la liste de faits finaux.
		 				F = LFts.getPremier();  // Affectation du premier fait de cette liste.
		 				
		 				while (F.getSuivant()!=null)
		 				{
							g.setColor (Color.red);
							g.drawString ("" + F.getNum(), xligne, yligne);
						
							fm = g.getFontMetrics();
							xligne += fm.stringWidth ("" + F.getNum());				
							
							g.setColor (Color.gray);
							g.drawString (" ou ", xligne, yligne);
						 	xligne = xligne + 19;
						 						
		 					F = F.getSuivant();
		 				}
		 				
		 				g.setColor (Color.red);
		 				g.drawString ("" + F.getNum(), xligne, yligne);
						
						fm = g.getFontMetrics();
						xligne += fm.stringWidth ("" + F.getNum()); 
						
						g.setColor (Color.gray);
						g.drawString (" = ", xligne, yligne);
						xligne = xligne + 12;
														
		 				// A partir d'ici, tous les faits conséquences sont notés.
						// Il ne reste plus qu'à notés les faits initiaux.
						
						LFts = R.getLFait();  // Affectation de la liste de faits initiale.
		 				F = LFts.getPremier();  // Affectation du premier fait de cette liste. 				
		 				
		  				while (F.getSuivant()!=null)
		 				{
							g.setColor (Color.green);
							g.drawString ("" + F.getNum(), xligne, yligne);
						
							fm = g.getFontMetrics();
							xligne += fm.stringWidth ("" + F.getNum());				
		
							g.setColor (Color.gray);
							g.drawString (" et ", xligne, yligne);
						 	xligne = xligne + 16;
						 											
		 					F = F.getSuivant();	
		 				}
		 
		 				g.setColor (Color.green);
		 				g.drawString ("" + F.getNum(), xligne, yligne); 
		 				
						fm = g.getFontMetrics();
						xligne += fm.stringWidth ("" + F.getNum());	
								 				
		 				g.setColor (Color.gray);
		 				g.drawString (".", xligne, yligne);
		 				
	 				yligne = yligne + 20;	
	 				
	 				// Affichage des descriptions.
		 				xligne = 70;
		 				g.setColor (Color.gray);
		 				g.drawString ("Avec :", xligne, yligne);
		 				
		 				F = LFts.getPremier();  // Affectation du premier fait de cette liste. 				
		  				while (F!=null)
		 				{
							xligne = 110;
							
							g.setColor (Color.gray);
							g.drawString ("Fait n°", xligne, yligne);
							xligne = xligne + 37;

							g.setColor (Color.white);
							g.drawString ("" + F.getNum(), xligne, yligne);
							
							fm = g.getFontMetrics();
							xligne += fm.stringWidth ("" + F.getNum());							
							
							g.setColor (Color.gray);
							g.drawString (" : ", xligne, yligne);
							xligne = xligne + 10; 
							
							if (LF.EstFait(Mat[0][i]))  // Si le fait existe. 
							{
								g.setColor (Color.white);
								g.drawString ("" + F.getDesc() + ".", xligne, yligne);
							}
							else  // Sinon, on le d�clare vide.
							{
								g.setColor (Color.red);
								g.drawString ("Vide." , xligne, yligne);
							}
							
							yligne = yligne + 15;
						 											
		 					F = F.getSuivant();	
		 				}	 					
		 				
		 				xligne = 70;
		 				g.setColor (Color.gray);
		 				g.drawString ("-------------------------------------------", xligne, yligne);
		 				yligne = yligne + 20;				
											
					}					
				}										
			}
			
			if (Mat[1][i] == -1)  // Si aucun résultat à la recherhce.
			{
		 		xligne = 70;

		 		g.setColor (Color.red);
		 		g.drawString ("Aucun résultat !", xligne, yligne);
		 		yligne = yligne + 20;								
			}								
		}


		// Si la liste de r�sultat est tremin�e.
		if (nbSaves != 0)
		{
			xligne = 50;
			yligne = yligne + 5;
			g.setColor (Color.white);
			g.drawString ("------------------------------------------------------------", xligne, yligne);
			yligne = yligne + 20;
			
 			// Mise à l'échelle de la JScrollPane.		
			setPreferredSize(new Dimension(250, yligne));
			revalidate();			
		}
		else  // Si il n'y a pas encore eu de recherche lancée.
		{
			g.setColor (Color.red);
			g.drawString ("Liste de résultat vide ...", xligne, yligne);;
		}	
	}	
}