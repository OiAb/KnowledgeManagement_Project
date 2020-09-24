import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/********************************************************************* AFaits */
/******************************************************** Affichage des faits */

class ARegles extends JFrame
{
	ARegles(LRegles L)
	{
		// Proprietes de la fenetre.
		setTitle(".: Liste de règles");
		setBounds(45,285, 370, 438);
		
		// Cr�ation du panneau.
		pan = new pan_ARegles(L);
		
		// Cr�ation de la barre de d�filement.
		defil = new JScrollPane(pan);
		getContentPane().add(defil);
	}

	private JPanel pan;
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
}


class pan_ARegles extends JPanel
{
	// Declaration des variables.
	LRegles LR;
	
	// Constructeur.
	pan_ARegles(LRegles L)
	{
		LR = L;
	}
	
	// Fonction appelee lors d'un rafraichissement.
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		// Fonction d'ecriture.
		Ecrire(LR, g);
	}
	
	
	// * Fonction : void Ecrire(String txt, int x, int y, Color C, Graphics g).
	// Cette fonction permet d'ecrire sur le panneau "pan_res".
	void Ecrire(LRegles LR, Graphics g)
	{
		// D�claration des variables.
		int xligne=30;
		int yligne=20;	
		Regles rude;
		Faits rude2;
		FontMetrics fm;
		
		g.setColor (Color.black);
		g.drawString (".: Liste de règles :", xligne, yligne);
		xligne = xligne + 20;
		yligne = yligne + 20;

		rude = LR.getPremier();	
		if (rude==null)
		{
			g.setColor (Color.red);
			g.drawString ("La liste de règles est actuellement vide.", xligne, yligne);
		}
		else
		{
			g.setColor (Color.gray);
			g.drawString ("----------------------------------------------", xligne, yligne);
			yligne = yligne + 20;
			
			g.setColor (Color.white);
			g.drawString ("Vous avez actuellement", xligne, yligne);
			fm = g.getFontMetrics();
			xligne += fm.stringWidth ("Vous avez actuellement ");
			
			g.setColor (Color.blue);
			g.drawString ("" + LR.getNbRegles(), xligne, yligne);
			
			// Calcul de xligne par rapport au numero.
			fm = g.getFontMetrics();
			xligne += fm.stringWidth (" " + LR.getNbRegles());
			
			g.setColor (Color.white);
			g.drawString ("règle(s).", xligne, yligne);
			yligne = yligne + 20;
			xligne = 50;
			
			g.setColor (Color.gray);			
			g.drawString ("---------------------------------------------", xligne, yligne);
			yligne = yligne + 20;
			
			////////////////////////////////////////////// Parcourt des r�gles :
			while (rude != null)  
			{
				xligne = 50;
				g.setColor (Color.black);
				g.drawString ("Règle n° : ", xligne, yligne);
				xligne = xligne + 60;
				
				g.setColor (Color.blue);
				g.drawString ("" + rude.getNum(), xligne, yligne);
				yligne = yligne + 15;
				
				xligne = 50;
				g.setColor (Color.gray);				
				g.drawString ("Faits hypothèses : ", xligne, yligne);
				xligne = xligne + 120;

				//////////////////////////////// Parcourt des faits hypoth�ses :
				rude2 = rude.getLFait().getPremier();
				
				if (rude2 != null)  // Si il y a des faits initiaux.
				{
					while (rude2 != null) 
					{
						g.setColor (Color.gray);
						g.drawString ("Fait n°", xligne, yligne);
						xligne = xligne + 40;
						
						g.setColor (Color.black);
						g.drawString ("" + rude2.getNum(), xligne, yligne);
						
						// Calcul de xligne par rapport au num�ro.
						fm = g.getFontMetrics();
						xligne += fm.stringWidth ("" + rude2.getNum());
						
						g.setColor (Color.gray);
						g.drawString (" : ", xligne, yligne);
						xligne = xligne + 10; 
						
						g.setColor (Color.black);
						g.drawString ("" + rude2.getDesc(), xligne, yligne);			
						
						yligne = yligne + 15;
						xligne = 170;
						rude2=rude2.getSuivant();					
					}
				}
				else  // Si pas de faits initiaux.
				{
					g.setColor (Color.red);
					g.drawString ("Vide", xligne, yligne);
					yligne = yligne + 15;					
				}

				xligne = 50;
				yligne = yligne + 10;
				g.setColor (Color.gray);
				g.drawString ("Faits Résultats : ", xligne, yligne);
				xligne = xligne + 120;

				///////////////////////////////// Parcourt des faits r�sultats :
				rude2 = rude.getLCons().getPremier();
				
				if (rude2 != null)  // Si il y a des faits cons�quences.
				{				
					while (rude2 != null) 
					{
						g.setColor (Color.gray);
						g.drawString ("Fait n°", xligne, yligne);
						xligne = xligne + 40;
						
						g.setColor (Color.black);
						g.drawString ("" + rude2.getNum(), xligne, yligne);
						
						// Calcul de xligne par rapport au num�ro.
						fm = g.getFontMetrics();
						xligne += fm.stringWidth ("" + rude2.getNum());
						
						g.setColor (Color.gray);
						g.drawString (" : ", xligne, yligne);
						xligne = xligne + 10; 
						
						g.setColor (Color.black);
						g.drawString ("" + rude2.getDesc(), xligne, yligne);			
						
						yligne = yligne + 15;
						xligne = 170;
						rude2=rude2.getSuivant();					
					}
				}
				else  // Si pas de faits cons�quences.
				{
						g.setColor (Color.red);
						g.drawString ("Vide", xligne, yligne);
						yligne = yligne + 15;					
				}					
				
				g.setColor (Color.gray);
				xligne = 50;	
				g.drawString ("-----------------------------------------------------------", xligne, yligne);				
				yligne = yligne + 20;
				rude=rude.getSuivant();
				
				 // Mise � l'�chelle de la JScrollPane.		
				setPreferredSize(new Dimension(250, yligne));
				revalidate();
			}					
		}
	}
}