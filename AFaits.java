import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/********************************************************************* AFaits */
/******************************************************** Affichage des faits */

class AFaits extends JFrame
{
	AFaits(LFaits L)
	{
		// Proprietes de la fenetre.
		setTitle(".: Liste de faits");
		setBounds(10,235, 300, 450);
		
		// Cr�ation du panneau.
		pan = new pan_AFaits(L);
		
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


class pan_AFaits extends JPanel
{
	// D�claration des variables.
	LFaits LF;
	
	// Constructeur.
	pan_AFaits(LFaits L)
	{
		LF = L;		
	}
	
	// Fonction appel�e lors d'un rafraichissement.
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		// Fonction d'�criture.
		Ecrire(LF, g);
	}
	
	
	// * Fonction : void Ecrire(String txt, int x, int y, Color C, Graphics g).
	// Cette fonction permet d'�crire sur le panneau "pan_res".
	void Ecrire(LFaits LF, Graphics g)
	{
		// D�claration des variables.
		int xligne=30;
		int yligne=20;	
		Faits rude;
		FontMetrics fm;
		
		g.setColor (Color.black);
		g.drawString (".: Liste de faits :", xligne, yligne);
		xligne = xligne + 20;
		yligne = yligne + 20;
			
		rude = LF.getPremier();
		if (rude==null)
		{
			g.setColor (Color.red);
			g.drawString ("La liste de faits est actuellement vide.", xligne, yligne);
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
			g.drawString ("" + LF.getNbFaits(), xligne, yligne);
			
			// Calcul de xligne par rapport au numero.
			fm = g.getFontMetrics();
			xligne += fm.stringWidth (" " + LF.getNbFaits()+1);
			
			g.setColor (Color.white);
			g.drawString ("fait(s).", xligne, yligne);
			yligne = yligne + 20;
			xligne = 50;
			
			g.setColor (Color.gray);			
			g.drawString ("---------------------------------------------", xligne, yligne);
			yligne = yligne + 20;			

			while (rude != null)
			{
				g.setColor (Color.gray);
				g.drawString ("Fait n°", xligne, yligne);
				xligne = xligne + 40;
				
				g.setColor (Color.black);
				g.drawString ("" + rude.getNum(), xligne, yligne);
				
				// Calcul de xligne par rapport au numero.
				fm = g.getFontMetrics();
				xligne += fm.stringWidth ("" + rude.getNum());
				
				g.setColor (Color.gray);
				g.drawString (" : ", xligne, yligne);
				xligne = xligne + 10; 
				
				g.setColor (Color.black);
				g.drawString ("" + rude.getDesc(), xligne, yligne);			
				
				yligne = yligne + 15;
				xligne = 50;
				rude=rude.getSuivant();
			}
			
			g.setColor (Color.gray);
			g.drawString ("----------------------------------------------", xligne, yligne);
			yligne = yligne + 20;
			
			 // Mise a l'echelle de la JScrollPane.		
			setPreferredSize(new Dimension(250, yligne));
			revalidate();
		}	
	}	
}	
