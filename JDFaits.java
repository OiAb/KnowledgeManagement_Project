import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/******************************************************************** JDFaits */
/********************************************************** Gestion des faits */

class JDFaits extends JFrame implements ActionListener
{
/// Declaration des variables.
    JDialog boite;
	JComboBox choix;
	JTextField txt1, txt2;
	JButton ok, ok2;
	JPanel pan;
	
	// Variables de donnees.
	LRegles LR;	
	LFaits LF;

	// Variable temporaires.
	LFaits ListeF, ListeC;
	Regles R;
	Faits F;
	int numTmp;
	String descTmp;
	
/// Constructeur.
	JDFaits (int x, int y, int lg, int ht, JFrame fen, String tab[], LRegles ListeRegles, LFaits ListeFaits)
	{
		// Cr�ation de la fen�tre.
		boite = new JDialog (fen, ".: Gestion de la base de faits", false);
		boite.setBounds (x, y, lg, ht);
		
		// Cr�ation du panneau principal.
		pan = new pan_faits();
		pan.setBackground (Color.gray);
		pan.setBounds (0, 0, lg, ht);
		pan.setLayout (null);
		boite.getContentPane().add (pan);
		
		// Cr�ation du JComboBox.
		choix = new JComboBox(tab);
		choix.setSelectedIndex(0);
		choix.setBounds (20, 45, 300, 20);
		choix.addActionListener (this);
		pan.add(choix);
	
		// Cr�ation des zones JText.
		txt1 = new JTextField ("Numéro", 20);
		txt1.setEditable (true);
		txt1.setBounds (220, 147, 90, 20);
		pan.add (txt1);
		
		txt2 = new JTextField ("Description", 20);
		txt2.setEditable (true);
		txt2.setBounds	(220, 177, 90, 20);
		pan.add (txt2);
		
		// Cr�ation des Boutons de validation.
		ok = new JButton ("GO !");
		ok.setBounds (240, 80, 70, 25);
		pan.add (ok);
		ok.setEnabled (true);
		ok.addActionListener(this);		
		
		ok2 = new JButton ("GO !");
		ok2.setBounds (232, 210, 70, 25);
		pan.add (ok2);
		ok2.setEnabled (false);
		ok2.addActionListener(this);
		
		// Initialisations.
		LR=ListeRegles;
		LF=ListeFaits;

		pan.updateUI();						
	}
	
	
	public void actionPerformed (ActionEvent e)
	{
		int direction;
		
		// Action sur les JButton.
		Object source =	e.getSource();	

		if (source == choix)  ////////////////////////////////////// JComboBox :
		{
			ok.setEnabled (true);
			ok2.setEnabled (false);
		}
		
		if (source == ok)  ////////////////////////////////////// Premier "ok" :
		{
			ok.setEnabled (false);
			ok2.setEnabled (true);
			
			direction = choix.getSelectedIndex();					
			switch (direction)
			{
				case 0:
				{							
					txt1.setEditable (true);
					txt2.setEditable (true);
					break;		
				}
							
				case 1:
				{
					txt1.setEditable (true);
					txt2.setEditable (false);
					break;							
				}
						
				case 2:
				{							
					txt1.setEditable (true);
					txt2.setEditable (true);
					break;		
				}									
			}
		}
						
		if (source == ok2)  ////////////////////////////////////// Second "ok" :
		{
			direction = choix.getSelectedIndex();					
			switch (direction)
			{
				case 0:  // Ajout d'un fait à la liste de faits principale.
				{													
					try  // Gestion des erreurs de saisies.
					{
						// On récupère les données.
						numTmp = Integer.parseInt(txt1.getText());
						descTmp = txt2.getText();								
								
						if (LF.EstFait(numTmp))  // Si le fait né de fait existe. 
						{
							JOptionPane.showMessageDialog(this, "Le fait n°" + numTmp + " existe déjà ! " + "\nVeuillez ressaisir ...");	
						}
						else
						{
							// Cr�ation d'un nouveau fait.
							F = new Faits();
									
							// Affectation des proprietes.
							F.setNum(numTmp);
							F.setDesc(descTmp);
							
							// On l'ajoute à la liste.
							LF.AjouterFait(F);
						}						
					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}					
					break;
				}
							
				case 1:  // Suppression d'un fait à la liste de faits principale.
				{
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText());
								
						// On supprime l'element.
						LF.SupprFait(numTmp);
					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}					
					break;							
				}
						
				case 2:  // Renommer un fait de la liste de faits principale.
				{
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText());
						descTmp = txt2.getText();
								
						// On regarde si l'element est dans la liste.
						if (LF.EstFait(numTmp))
						{
							// On renomme l'element.
							LF.getFait(numTmp).setDesc(descTmp);								
						}
						else
						{
							System.out.println("  --> Le fait recherche n'est pas dans la liste");	
						}
					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}					
					break;							
				}							
			}
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
	
	
	// * Fonction : void InsertListe(LFaits L, String strTmp);
	// Cette fonction permet d'inserer des faits dans une liste de faits a
	// Partir du String entre en parametre de type F1;F2;F3.
	void InsertListe(LFaits L, String S)
	{
		int numTmp=0, numTmp2;
		char charTmp;
		String strTmp="";

		while(numTmp<=S.length()-1)
		{
			// On recupere le caratere.
			charTmp=S.charAt(numTmp);

			if ((charTmp!=';') && (charTmp!=' '))
			{
				// On realise une concatenation.
				strTmp=strTmp+charTmp;									
			}
			else
			{
				// Conversion en entier.
				numTmp2=Integer.parseInt(strTmp);	

				// On cr�e le nouvel element.
				F = new Faits();

				// Affectation des proprietes.
				F.setNum(numTmp2);
				F.setDesc(LF.getFait(numTmp2).getDesc());
				
				// On ajoute le fait cree a la liste de faits.
				L.AjouterFait(F);

				// On reinitialise la chaine de caractere.
				strTmp="";									
			}
			numTmp++;
		}	
	}
	
	
	// * Fonction : void DeleteListe(LFaits L, String strTmp);
	// Cette fonction permet de supprimer des faits dans une liste de faits �
	// Partir du String entre en parametre de type F1;F2;F3.
	void DeleteListe(LFaits L, String S)
	{
		int numTmp=0, numTmp2;
		char charTmp;
		String strTmp="";

		while(numTmp<=S.length()-1)
		{
			// On recupere le caratere.
			charTmp=S.charAt(numTmp);

			if ((charTmp!=';') && (charTmp!=' '))
			{
				// On realise une concatenation.
				strTmp=strTmp+charTmp;									
			}
			else
			{
				// Conversion en entier.
				numTmp2=Integer.parseInt(strTmp);	

				// On supprime l'element.
				L.SupprFait(numTmp2);

				// On reinitialise la chaine de caractere.
				strTmp="";									
			}
			numTmp++;
		}	
	}
}


class pan_faits extends JPanel
{
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.setColor (Color.lightGray);
		g.drawRoundRect(10, 10, 320, 110, 50, 50); 
		g.drawRoundRect(10, 130, 320, 120, 50, 50);
			
		g.setColor (Color.white);				
		g.drawString ("Que voulez-vous faire ?", 25, 30);
				
		g.drawString ("Valider l'opération afin de pouvoir", 25, 90);
		g.drawString ("Continuer la saisie.", 25, 105);	
				
		g.drawString (".: Numéro du fait :", 25, 162);
		g.drawString (".: Description du fait :", 25, 192);
				
		g.drawString (".: Afin de valider l'opération :", 25, 227);								
	}
}