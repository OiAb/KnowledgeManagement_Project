import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/******************************************************************* JDMoteur */
/********************************************************* Moteur d'inference */

class JDMoteur extends JFrame implements ActionListener
{
/// Declaration des variables.
    private JDialog boite;
	private AResultats boite2;
	private JTextField txt1;
	private JButton ok, ok2;
	private JComboBox choix;	
	private pan_moteur pan;
	
	// Variables de donnees.
	private LRegles LR;	
	private LFaits LF;
	private LFaits ListeFTmp;
	
	// Variables de sauvegardes.
	private int[][] Mat = new int[2][10000];
	private int nbSaves=0;
	
		// Cette matrice contiendra en sa premiere ligne des faits {int}, en sa
		// Deuxieme, leur qualification :									  //
		//		- Faits initiaux {0}.										  //
		//		- Faits Resultats {1}.										  //
		////////////////////////////////////////////////////////////////////////
		
/// Constructeur.	
	JDMoteur(int x, int y, int lg, int ht, JFrame fen, LRegles ListeRegles, LFaits ListeFaits)
	{
		
		////////////////////////////////////////////////////// Boite recherche :
		// Creation de la fenetre.
		boite = new JDialog (fen, ".: Moteur d'inférence", false);
		boite.setBounds (x, y, lg, ht);
		
		// Creation du panneau principal.
		pan = new pan_moteur();
		pan.setBackground (Color.gray);
		pan.setBounds (0, 0, lg, ht);
		pan.setLayout (null);
		boite.getContentPane().add (pan);		
		
		// Creation des JComboBox choix.
		String tab[] = {"Recherche en chainage avant", "Recherche en chainage arrière"};
		choix = new JComboBox (tab);
		choix.setSelectedIndex(0);		
		choix.setBounds (20, 45, 300, 20);
		choix.addActionListener (this);
		pan.add(choix);
		
		// Creation du Bouton de validation.
		ok = new JButton ("GO !");
		ok.setBounds (240, 80, 70, 25);
		pan.add (ok);
		ok.setEnabled (true);
		ok.addActionListener(this);					
		
		// Creation des zones JText.
		txt1 = new JTextField ("F1;F2;...;Fn;", 20);
		txt1.setEditable (true);
		txt1.setBounds (180, 147, 130, 20);
		pan.add (txt1);
		
		// Creation du Bouton de validation.
		ok2 = new JButton ("Résultat");
		ok2.setBounds (220, 182, 90, 24);
		pan.add (ok2);
		ok2.setEnabled (true);
		ok2.addActionListener(this);	
		
		// Allocation pour la liste temporaire.
		ListeFTmp = new LFaits();
		
		pan.updateUI();	
		
		////////////////////////////////////////////////////// Boite résultats :
		// Creation de la fenetre.
		boite2 = new AResultats(Mat, ListeRegles, ListeFaits);
		boite2.setBounds(x, y+ht+4, 350, 399);			
		
		// Initialisations.
		LR=ListeRegles;
		LF=ListeFaits;
	}
		
	public void actionPerformed (ActionEvent e)
	{
		// Declaration des variables.
		String S, strTmp = "";
		char charTmp;
		int numTmp = 0;
		Faits rude;
		LFaits Resultat;
		int direction;
		
		// Action sur les JButton.
		Object source =	e.getSource();

		if (source == choix)  ////////////////////////////////////// JComboBox :
		{
			ok.setEnabled (true);
			ok2.setEnabled (false);
		}
		
		if (source == ok) //////////////////////////////// Premier "ok" :
		{
			ok.setEnabled (false);
			ok2.setEnabled (true);
		}

		// Si le bouton Résultat est valide.
		if (source == ok2)
		{
			try  // Gestion des erreurs de saisies.
			{
				direction = choix.getSelectedIndex();			
				
				//////////////////////// Complète le tableau des donnees initiales :
				S = txt1.getText();
				while(numTmp<S.length())
				{
					// On recupere le caratere.
					charTmp = S.charAt(numTmp);
	
					if ((charTmp!=';') && (charTmp!=' '))
					{
						// On realise une concatenation.
						strTmp=strTmp+charTmp;									
					}
					else
					{
						// On insere l'element sur la premiere ligne de la matrice.
						Mat[0][nbSaves] = Integer.parseInt(strTmp);
						
						// Au meme indice de la ligne 2 : 0 {Fait initial}.
						Mat[1][nbSaves] = direction;
					
						strTmp = "";
						nbSaves++;	
					}
					numTmp++;
				}
			
				
				// La matrice de resultats est completee avec les faits initiaux de
				// La recherche en cours.
				
				// Lance le moteur d'inference.
				if (direction == 0)
				{
					Moteur_1();
				}
				else
				{
					if (direction == 1)
					{
						Moteur_2();	
					}
				}
				
				// Apres la recherche, la matrice de resultat est complete avec les
				// regles utilisees par la recherche en cours.
				
				// Mise à jour de NbSaves dans AResultats.
				boite2.setNbSaves(nbSaves);
			}
			catch (Exception erreur)  // Si erreur de saisie.
			{
				JOptionPane.showMessageDialog(this, "Erreur de saisie !");	
			}			
		}
	}
	
	
	// * Fonction : void setNbSaves(int I).
	// Cette fonction permet de modifier la valeur de nbSaves.
	void setNbSaves(int I)
	{
		nbSaves = I;
		boite2.setNbSaves(I);
	}


	// * Fonction : void Moteur_1().
	// Cette fonction permet d'effectuer une recherche par rapport à des faits
	// Initiaux. 
	void Moteur_1()
	{
		// Declaration des variables.
		Faits F;
		ListeFTmp.setPremier(null);
		Regles rude;
		Faits rude2;		
		boolean flag = false;

		// Ajoute les faits initiaux à la liste temporaire de faits.
		ListeFTmp.InsertListe(LF, txt1.getText());

		while (!flag)
		{
			flag = true;
			
			// "rude" pointe sur le premier element de la liste de regles.
			rude = LR.getPremier();			
			while ((rude != null) && (flag))
			{	
				if (!(rude.getTeste()) && EstContenu(rude.getLFait(), ListeFTmp))
				{
					// Si la liste de faits hypothese de "rude" est contenu dans
					// ListeFTmp.On copie les faits conclsions de "rude" dans
					// ListeFTmp.
					rude2 = rude.getLCons().getPremier();
					while (rude2 != null)
					{
						// Creation d'un nouveau fait.
						F = new Faits();
						
						// Affectation des proprietes.
						F.setNum(rude2.getNum());
						F.setDesc(rude2.getDesc());						
					
						// On l'ajoute a la liste.
						ListeFTmp.AjouterFait(F);
						
						rude2 = rude2.getSuivant();
					}
					
					// Puis on ajoute la regle a la "matrice de resultats" pour
					// Permettre un affichage en detail de la recherche.
					
					// On insere l'element sur la premiere ligne de la matrice.
					Mat[0][nbSaves] = rude.getNum();
					
					// Au meme indice de la ligne 2 : 1 {Regle utilisee}.
					Mat[1][nbSaves] = 2;					
					nbSaves++;
									
					rude.setTeste(true);
					flag = false;
				}
				else
				{
					// Sinon on effectue le meme traitement avec la regle
					// Suivante.
					rude = rude.getSuivant();	
				}
			}
			
			if (Mat[1][nbSaves-1] == 0)  // Si aucune regle ne fut utilisee.
			{
					// Insertion d'un marqueur permettant une distinction.
					Mat[1][nbSaves] = -1;
					nbSaves++;				
			}
			
			// Si (!flag) alors la liste de faits consequences a ete ajoute
			// A la liste temporaire. On effectue le meme traitement avec
			// La nouvelle liste de faits temporaire.
		
			// Si (flag) alors toutes les regles ont ete testees et leur listes
			// De consequences ajoutees si necessaire. On notera qu'a chaque
			// Fois qu'une liste de consequences est ajoutee, on reteste la
			// Liste de regles avec la nouvelle liste temporaire. La redondance
			// Est evitee grace a l'utilisation d'un booleen dans la class regle.
			// Si on a deja ajoute la liste de consequences d'une regle, son
			// Booleen est mis a vrai.
		}

		// Renitialisation des variables "test" de chaque regles.
		LR.InitTeste();
		
		// A la sortie de la fonction, ListeFTmp contient tous les faits
		// Initiaux et finaux de la recherche et ListeRTmp contient toutes les
		// Regles utilisees.
	}
	

	// * Fonction : void Moteur_2().
	// Cette fonction permet d'effectuer une recherche de faits par rapports a
	// Un ou plusieurs faits consequences / finaux. 
	void Moteur_2()
	{
		// Declaration des variables.
		Faits F;
		ListeFTmp.setPremier(null);
		Regles rude;
		Faits rude2;		
		boolean flag = false;		

		// Ajoute les faits initiaux a la liste temporaire de faits.
		ListeFTmp.InsertListe(LF, txt1.getText());

		while (!flag)
		{
			flag = true;
			
			// "rude" pointe sur le premier element de la liste de regles.
			rude = LR.getPremier();			
			while ((rude != null) && (flag))
			{	
				if (!(rude.getTeste()) && Contien1Element(rude.getLCons(), ListeFTmp))
				{
					
					// Si un element de la liste de faits hypothese de "rude" 
					// Est contenu dans ListeFTmp.On copie les faits initiaux
					// De "rude" dans ListeFTmp.
					
					rude2 = rude.getLFait().getPremier();
					while (rude2 != null)
					{
						// Creation d'un nouveau fait.
						F = new Faits();
						
						// Affectation des proprietes.
						F.setNum(rude2.getNum());
						F.setDesc(rude2.getDesc());						
					
						// On l'ajoute a la liste.
						ListeFTmp.AjouterFait(F);
						
						rude2 = rude2.getSuivant();
					}
					
					// Puis on ajoute la regle a la "matrice de rasultats" pour
					// Permettre un affichage en detail de la recherche.
					
					// On insere l'element sur la premiere ligne de la matrice.
					Mat[0][nbSaves] = rude.getNum();
					
					// Au meme indice de la ligne 2 : 1 {Regle utilisee}.
					Mat[1][nbSaves] = 3;					
					nbSaves++;
									
					rude.setTeste(true);
					flag = false;
				}
				else
				{
					// Sinon on effectue le meme traitement avec la regle
					// Suivante.
					rude = rude.getSuivant();	
				}
			}
			
			if (Mat[1][nbSaves-1] == 1)  // Si aucune regle ne fut utilisee.
			{
					// Insertion d'un marqueur permettant une distinction.
					Mat[1][nbSaves] = -1;
					nbSaves++;				
			}
			
			// Si (!flag) alors la liste de faits initiaux a ete ajoutee
			// A la liste temporaire. On effectue le meme traitement avec
			// La nouvelle liste de faits temporaire.
		
			// Si (flag) alors toutes les regles ont ete testees et leur listes
			// De faits ajoutees si necessaire. On notera qu'a chaque fois 
			// Qu'une liste de consequences est ajoutee, on reteste la liste de
			// Regles avec la nouvelle liste temporaire. La redondance est 
			// Evitee grace a l'utilisation d'un booleen dans la class regle.
			// Si on a deja ajoute la liste de faits d'une regle, son booleen
			// Est mis a vrai.
		}

		// Reinitialisation des variables "test" de chaque regles.
		LR.InitTeste();
		
		// A la sortie de la fonction, ListeFTmp contient tous les faits
		// Initiaux et finaux de la recherche et ListeRTmp contient toutes les
		// Regles utilisees.
	}

	
	// * Fonction : boolean EstContenu(LFaits Liste1, LFaits Liste2).
	// Cette fonction retourne "TRUE" si la liste de faits "liste1" est contenu
	// Dans la liste de faits "liste2". 
	boolean EstContenu(LFaits Liste1, LFaits Liste2)
	{
		// Declaration des variables.
		Faits rude, rude2;
		boolean flag=true;
		
		// Debut du traitement.
		rude = Liste1.getPremier();
		while ((rude != null) && (flag))	
		{
			flag = false;
			rude2 = Liste2.getPremier();
			
			// Tant qu'on a pas trouve "rude" dans "liste2".
			while ((rude2 != null) && (!flag))
			{
				if (rude.getNum() == rude2.getNum())
				{
					flag = true;	
				}
				rude2 = rude2.getSuivant();
			}
			// Si (flag) alors on a trouve rude.
			// Si (!flag) alors on sort.
			rude = rude.getSuivant();
		}
		
		// Si (flag) && (rude == null) alors tous les elements sont trouves.
		// Si (!flag) && (rude != null || rude == null) alors au moins un
		// Element absent.
		
		return flag;
	} 
	

	// * boolean Contient1Element(LFaits Liste1, LFaits Liste2).
	// Cette fonction retourne "TRUE" si la liste de faits "liste2" contient
	// au moins un element de la liste de faits "liste1". 
	boolean Contien1Element(LFaits Liste1, LFaits Liste2)
	{
		// Declaration des variables.
		Faits rude, rude2;
		boolean flag=false;
		
		// Debut du traitement.
		rude = Liste1.getPremier();
		while ((rude != null) && (!flag))	
		{
			rude2 = Liste2.getPremier();
			
			// Tant qu'on a pas trouve "rude" dans "liste2".
			while ((rude2 != null) && (!flag))
			{
				if (rude.getNum() == rude2.getNum())
				{
					flag = true;	
				}
				rude2 = rude2.getSuivant();
			}
			// Si (flag) alors on a trouve rude.
			// Si (!flag) alors on sort.
			rude = rude.getSuivant();
		}
		
		// Si (flag) && (rude == null) alors tous les elements sont trouves.
		// Si (!flag) && (rude != null || rude == null) alors au moins un
		// Element absent.
		
		return flag;
	} 	
	
	
	////////////////////////////////////////// Affichage du moteur d'inference :
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


	////////////////////////////////////////// Affichage du moteur d'inference :
	// * Fonction : setVoirRes(boolean B).
	// Cette fonction permet de rendre visible ou non la fenetre de resultat.
	// Comme cette fenetre depend du la JDialog du Moteur, nous sommes obliges
	// De passe par l'intermediaire de cette fonction.
	void setVoirRes(boolean B)
	{
		if (B)
		{
			boite2.setVoir (true);
		}
		else
		{
			boite2.setVoir (false);
		}	
	}	
}


class pan_moteur extends JPanel
{
	// Fonction appelée lors d'un rafraichissement.
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.setColor (Color.lightGray);
		g.drawRoundRect(10, 10, 320, 110, 50, 50); 
		g.drawRoundRect(10, 130, 320, 92, 50, 50);
			
		g.setColor (Color.white);				
		g.drawString ("Nature de la recherche ?", 25, 30);
				
		g.drawString ("Valider l'opération afin de pouvoir", 25, 90);
		g.drawString ("Continuer la saisie.", 25, 105);	
				
		g.drawString (".: Liste de fait(s) :", 25, 162);
				
		g.drawString (".: Afin de valider l'opération :", 25, 198);					
	}
}