import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/******************************************************************* JDRegles */
/********************************************************* Gestion des regles */

class JDRegles extends JFrame implements ActionListener
{
/// Declaration des variables.
    JDialog boite;
    AFaits boiteF;
    ARegles boiteR;
	JComboBox choix;
	JTextField txt1, txt2, txt3;
	JButton ok, ok2;
	JButton voir1, voir2, help;
	JPanel pan;
	
	// Variables de donnees.
	LRegles LR;	
	LFaits LF;

	// Variable temporaires.
	LFaits ListeF, ListeC;
	Regles R;
	Faits F;
	int numTmp;
	String strTmp, strTmp2;
	
/// Constructeur.
	JDRegles (int x, int y, int lg, int ht, JFrame fen, String tab[], LRegles ListeRegles, LFaits ListeFaits, AFaits BF, ARegles BR)
	{
		// Creation de la fenetre.
		boite = new JDialog (fen, ".: Gestion de la base de règles", false);
		boite.setBounds (x, y, lg, ht);
		
		// Creation du panneau principal.
		pan = new pan_regles();
		pan.setBackground (Color.gray);
		pan.setBounds (0, 0, lg, ht);
		pan.setLayout (null);
		boite.getContentPane().add (pan);
		
		// Creation du JComboBox.
		choix = new JComboBox(tab);
		choix.setSelectedIndex(0);
		choix.setBounds (20, 45, 300, 20);
		choix.addActionListener (this);
		pan.add(choix);
	
		// Creation des zones JText.
		txt1 = new JTextField ("Numero", 20);
		txt1.setEditable (true);
		txt1.setBounds (220, 150, 90, 20);
		pan.add (txt1);
		
		txt2 = new JTextField ("F1;F2;...;Fn;", 20);
		txt2.setEditable (true);
		txt2.setBounds	(220, 180, 90, 20);
		pan.add (txt2);
		
		txt3 = new JTextField ("F1;F2;...;Fn;", 20);
		txt3.setEditable (true);
		txt3.setBounds	(220, 210, 90, 20);
		pan.add (txt3);		
		
		// Creation des Bouton de validation.
		ok = new JButton ("GO !");
		ok.setBounds (240, 80, 70, 25);
		pan.add (ok);
		ok.setEnabled (true);
		ok.addActionListener(this);		
		
		ok2 = new JButton ("GO !");
		ok2.setBounds (232, 245, 70, 25);
		pan.add (ok2);
		ok2.setEnabled (false);
		ok2.addActionListener(this);
		
		// Creation des boutons d'aide.
		voir1 = new JButton ("Faits");
		voir1.setBounds (40, 306, 80, 25);
		pan.add (voir1);
		voir1.setEnabled (true);
		voir1.addActionListener(this);
		
		voir2 = new JButton ("Règles");
		voir2.setBounds (130, 306, 80, 25);
		pan.add (voir2);
		voir2.setEnabled (true);
		voir2.addActionListener(this);
		
		help = new JButton ("Help ?");
		help.setBounds (220, 306, 80, 25);
		pan.add (help);
		help.setEnabled (true);
		help.addActionListener(this);					
		
		// Initialisations.
		LR = ListeRegles;
		LF = ListeFaits;
		boiteF = BF;
		boiteR = BR;
		
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
		
		if (source == voir1)  ////////////////////////////////////////// Voir1 :
		{
			boiteF.setVoir(true);
		}
		
		if (source == voir2)  ////////////////////////////////////////// Voir2 :
		{
			boiteR.setVoir(true);
		}
		
		if (source == help)  //////////////////////////////////////////// Help :
		{

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
					txt3.setEditable (true);
					break;								
				}
					
				case 1:
				{
					txt1.setEditable (true);
					txt2.setEditable (false);
					txt3.setEditable (false);
					break;								
				}
						
				case 2:
				{
					txt1.setEditable (false);
					txt2.setEditable (false);
					txt3.setEditable (false);
					break;												
				}
						
				case 3:
				{
					txt1.setEditable (true);
					txt2.setEditable (true);
					txt3.setEditable (false);
					break;								
				}
						
				case 4:
				{
					txt1.setEditable (true);
					txt2.setEditable (false);
					txt3.setEditable (true);
					break;								
				}
						
				case 5:
				{
					txt1.setEditable (true);
					txt2.setEditable (true);
					txt3.setEditable (false);
					break;	
				}
				
				case 6:
				{
					txt1.setEditable (true);
					txt2.setEditable (false);
					txt3.setEditable (true);
					break;	
				}
				
				case 7:
				{
					txt1.setEditable (true);
					txt3.setEditable (false);
					txt2.setEditable (false);
					break;	
				}
				
				case 8:
				{
					txt1.setEditable (true);
					txt3.setEditable (false);
					txt2.setEditable (false);
					break;	
				}										
			}
		}						
		
		if (source == ok2)  ////////////////////////////////////// Second "ok" :
		{	
			direction = choix.getSelectedIndex();
			switch (direction)
			{
				case 0:  // Ajouter une regle.
				{			
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText());
						strTmp = txt2.getText();
						strTmp2 = txt3.getText();
						
						if (LR.EstRegle(numTmp))
						{
							JOptionPane.showMessageDialog(this, "La règle n°" + numTmp + " existe deja ! " + "\nVeuillez ressaisir ...");	
						}
						else
						{
							// On cree deux nouvelles listes de faits.
							ListeF = new LFaits();
							ListeC = new LFaits();
							
							// On insere les elements.
							ListeF.InsertListe(LF, strTmp);
							ListeC.InsertListe(LF, strTmp2);
								
							// On cree la nouvelle regle.
							R = new Regles();
								
							// Affectation des proprietes.
							R.setNum(numTmp);
							R.setLFait(ListeF);
							R.setLCons(ListeC);
								
							// On l'ajoute a la liste de regles.
							LR.Ajouter_Regle(R);							
						}								
					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}
					break;		
				}
						
				case 1:  // Supprimer une regle.
				{
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText());
							
						// On supprime l'element.
						LR.Suppr_Regle(numTmp);
												
					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}
					break;			
				}
					
				case 2:  // Supprimer toutes les regles.
				{
   					LR.InitListe();
					break;												
				}
					
				case 3:  // Ajouter une liste de fait(s) a une regle.
				{				
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donn�es.
						numTmp = Integer.parseInt(txt1.getText());
						strTmp2 = txt2.getText();
	
						// On regarde si la regle existe.
						if (LR.EstRegle(numTmp))
						{			
	  						// On regarde si la liste de faits existe.
	  						if (LR.getRegle(numTmp).getLFait() == null)
	  						{
								// Sinon, on la cree.
	  							ListeF = new LFaits();
	  							LR.getRegle(numTmp).setLFait(ListeF);									
	  						}
	   						// On insere les elements.
							LR.getRegle(numTmp).getLFait().InsertListe(LF, strTmp);   								
	  					}						
					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}
					break;								
				}
				
				case 4:  // Ajouter une liste de consequence(s) a une regle.
				{				
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText());
						strTmp2 = txt3.getText();
	
	   					// On regarde si la regle existe.
	   					if (LR.EstRegle(numTmp))
	   					{			
	   						// On regarde si la liste de faits existe.
	   						if (LR.getRegle(numTmp).getLCons() == null)
	   						{
								// Sinon, on la cree.
	   							ListeC = new LFaits();
	   							LR.getRegle(numTmp).setLFait(ListeC);									
	   						}
	
	   						// On insere les elements.
							LR.getRegle(numTmp).getLFait().InsertListe(LF, strTmp);   								
	   					}							
					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}
					break;													
				}
						
				case 5:  // Suppression de fait(s) d'une regle.
				{
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText());
						strTmp2 = txt2.getText();
	
						// On regarde si la regle existe.
	 					if (LR.EstRegle(numTmp))
	   					{
	   						// On regarde si la regle a une liste de faits.
	   						if (LR.getRegle(numTmp).getLFait()!=null)
	   						{
	   							// On supprime les faits.
	   							LR.getRegle(numTmp).getLFait().DeleteListe(strTmp2);
	   						}
	   						else
	   						{	
	   							System.out.println("  --> La liste de faits n'existent pas !");	
	   						}
	   					}
   					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}   					 
					break;								
				}
						
				case 6:  // Suppression de consequence(s) d'une regle.
				{
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText());
						strTmp2 = txt3.getText();
	
	 					// On regarde si la regle existe.
	   					if (LR.EstRegle(numTmp))
	   					{
	   						// On regarde si la regle a une liste de faits.
	   						if (LR.getRegle(numTmp).getLCons()!=null)
	   						{
	   							// On supprime les faits.
	   							LR.getRegle(numTmp).getLCons().DeleteListe(strTmp2);
	   						}
	   						else
	   						{	
	   							System.out.println("  --> La liste de conséquences n'existent pas !");	
	   						}
	   					}
   					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}   					 
					break;								
				}						
						
				case 7:  // Supprimer la liste de fait(s) d'une regle.
				{
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText()); 
							 
						// On regarde si la regle existe.
	   					if (LR.EstRegle(numTmp))
	   					{   
	   						// On regarde si la regle a une liste de faits.
	   						if (LR.getRegle(numTmp).getLFait()!=null)
	   						{
	   							LR.getRegle(numTmp).setLFait(null);
	   						}
	   						else
	   						{
	   							System.out.println ("  --> La liste de faits est deja supprimee.");	
	   						}
	   					}
   					}
					catch (Exception erreur)  // Si erreur de saisie.
					{
						JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
					}   					
					break;	
				}
					
				case 8:  // Supprimer la liste de consequence(s) d'une regle.
				{
					try  // Gestion des erreurs de saisies.
					{
						// On recupere les donnees.
						numTmp = Integer.parseInt(txt1.getText()); 
							 
						// On regarde si la regle existe.
	    				if (LR.EstRegle(numTmp))
	    				{   
	    					// On regarde si la regle a une liste de faits.
	    					if (LR.getRegle(numTmp).getLCons()!=null)
	    					{
	    						LR.getRegle(numTmp).setLCons(null);
	    					}
	    					else
	    					{
	    						System.out.println ("  --> La liste de consequences est deja supprimee.");	
	    					}
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
}


class pan_regles extends JPanel
{
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.setColor (Color.lightGray);
		g.drawRoundRect(10, 10, 320, 110, 50, 50); 
		g.drawRoundRect(10, 130, 320, 162, 50, 50);
		g.drawRoundRect(10, 302, 320, 33, 25, 25);
					
		g.setColor (Color.white);	
		g.drawString ("Que voulez-vous faire ?", 25, 30);
				
		g.drawString ("Valider l'opération afin de pouvoir", 25, 90);
		g.drawString ("Continuer la saisie.", 25, 105);	
				
		g.drawString (".: Numéro de la règle :", 25, 165);
		g.drawString (".: Liste de fait(s) :", 25, 195);
		g.drawString (".: Liste de conséquence(s) :", 25, 225);
						
		g.drawString ("Cliquer sur 'GO' afin de terminer", 25, 255);
		g.drawString ("Et de valider l'opération.", 25, 270);					
	}		
}