import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/******************************************************************** JDAbout */
/******************************************************** A propos de S.E.B.P */

class JDAbout implements ActionListener
{
/// Declaration des variables.
    JDialog boite;
	JButton ok;
	JPanel pan;	

/// Constructeur.
	JDAbout (int x, int y, int lg, int ht, JFrame fen)
	{
		// Creation de la fenetre.
		boite = new JDialog (fen, ".: A propos de S.E", false);
		boite.setBounds (x, y, lg, ht);
		boite.setVisible (true);
		
		// Creation du panneau principal.
		pan = new pan_about();
		pan.setBackground (Color.lightGray);
		pan.setBounds (0, 0, lg, ht);
		pan.setLayout (null);
		boite.getContentPane().add (pan);
		
		// Cr�ation des Bouton de validation.
		ok = new JButton ("ok");
		ok.setBounds (lg/2-35, ht-65, 70, 25);
		pan.add (ok);
		ok.setEnabled (true);
		ok.addActionListener(this);						
	}
	
	public void actionPerformed (ActionEvent e)
	{
		// Action sur les JButton.
		Object source =	e.getSource();
		
		if (source == ok)
		{
			boite.setVisible(false);
		}		
	}


/// Description des methodes.
	
	// * Fonction : setVoir(boolean B).
	// Cette fonction permet de rendre visible ou non le JDialog.
	void setVoir(boolean B)
	{
		if (B)
		{
			boite.setVisible (true);
		}
		else
		{
			boite.setVisible (false);	
		}	
	}
}


class pan_about extends JPanel
{
	// Declaration des variables.
	private ImageIcon Ifond;
	private ImageIcon Ilogo;
	
	pan_about()
	{
		// Image de fond.
		Ifond = new ImageIcon ("img/about2.jpeg");
		// Image logo.
		Ilogo = new ImageIcon ("img/logo.gif");	
	}

	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.drawImage (Ifond.getImage(), 0, 0, null);
		g.drawImage (Ilogo.getImage(), 205, 20, null);
			
		//super.paintComponent(g);
		g.setColor (Color.lightGray);
		g.drawRect(45, 60, 250, 20);  // Cadre titre.
		g.drawRoundRect(10, 10, 320, 155, 50, 50);  // Cadre 1.
		g.drawRoundRect(190, -20, 300, 70, 50, 50);  // Cadre logo. 

		g.setColor (Color.white);	
		g.drawString ("S.E {Système Expert by Ouijdane Abchir}", 63, 75);

		g.setColor (Color.black);
		g.drawString ("Version 2.1", 30, 40);

		g.drawString ("Application réalisée dans le cadre du projet KM", 43, 100);
		g.drawString ("Génie informatique {2ème année} à l'EHTP.", 43, 115);
		
		g.drawString ("Pour plus de renseignements, contactez moi", 42, 140);
		g.drawString ("A l'adresse suivante : ouijdane@gmail.com ", 50, 155);							
	}	
}