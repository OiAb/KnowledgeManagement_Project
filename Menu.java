import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

class Menu extends JFrame implements ActionListener
{
	
	//////////////////////////////////////////////// D�claration des variables :
	 	
	// Variables de donn�es.
		// Bases.
		LRegles LR = new LRegles();
		LFaits LF = new LFaits();
	
		// Fichiers.
		String repSE, repBF, repBR;
		String ficSE, ficBF, ficBR;
	
	// Mode Graphique.
	private JMenuBar barreMenus;  // Menus barre.
		private JMenu A, B, C, D;  // Composantes niveau 1. 
			private JMenuItem Aa, Ac, Ae, Ah;  // Options Syst�me {Simples}.
			private JMenu Ab, Ad, Af, Ag;  // Option Syst�me {Menu d�roulants}.
				private JMenuItem Aba, Abb, Abc, Ada, Adb, Afa, Afb, Afc, Aga, Agb;// Option Syst�me {Composantes des menu d�roulants}.
			private JRadioButton Ba, Bb;  // Options Utilisation.
			private JCheckBox Ca, Cb, Cc;  // Options Affichage.
			private JMenuItem Da, Db;  // Options Aide.
				
	// Barre d'outils.
	private JToolBar barreOutils;
	
		// Ses bouttons.
		private JButton B_a, B_b, B_c, B_d, B_e;	
								
		// Icones.
		private Icon Isys_create = new ImageIcon ("img/1.gif");
		private Icon Isys_fleche = new ImageIcon ("img/2.gif");
		private Icon Isys_close = new ImageIcon ("img/3.gif");
		private Icon Isys_save = new ImageIcon ("img/4.gif");
		private Icon Isys_save_all = new ImageIcon ("img/5.gif");
		private Icon Isys_save_as = new ImageIcon ("img/6.gif");
		private Icon Isys_print = new ImageIcon ("img/7.gif");
		private Icon Isys_quit = new ImageIcon ("img/8.gif");
		private Icon Iaff_b = new ImageIcon ("img/9.gif");
		private Icon Ihelp_about = new ImageIcon ("img/10.gif");
		private Icon Ihelp_doc = new ImageIcon ("img/11.gif");
		
	// Les JDialog {Fen�tre}.
	private JDFaits boite1;
	private JDRegles boite2;
	private JDMoteur boite3;
	private JDAbout boite6;
	
	// Les JFrame {Affichage des faits et r�gles}
	private AFaits boite4;
	private ARegles boite5;
	
	// D�finition de l'�cran.	
	int larg = 1024;
	int haut = 768;
		
	// Cr�ation d'un container.
	Container contenu = getContentPane();
		
	///////////////////////////////////////////////////////////// Constructeur :
	public Menu()
	{
		//////////////////////////////////////////////////////////// Affichage :

		setTitle("S.E - Système Expert [Nouveau]"); 	
		setBounds(0, 0, larg, haut);

		// Cr�ation Paneau.	
		JPanel pan = new panneau();
		pan.setLayout (null);
		contenu.add(pan);

		// Cr�ation de la barre de menu.
		barreMenus = new JMenuBar();
		setJMenuBar(barreMenus);

		// Cr�ation des menu de la barre.
		A = new JMenu ("Système");
		A.setMnemonic('S');
		barreMenus.add(A);

			// Cr�ation des sous-menus.
			
			// Nouveau.
			Aa = new JMenuItem ("Nouveau", Isys_create);
			Aa.addActionListener (this);
			Aa.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
			A.add(Aa);

			// Ouvrir.
			Ab = new JMenu ("      Ouvrir...");
			Ab.addActionListener (this);
			A.add(Ab);
			
				// Sous menu de "Ouvrir".
				Aba = new JMenuItem ("Un projet ...", Isys_fleche);
				Aba.addActionListener (this);
				Aba.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
				Ab.add(Aba);
				
				Abb = new JMenuItem ("Une base de faits ...", Isys_fleche);
				Abb.addActionListener (this);
				Ab.add(Abb);
				
				Abc = new JMenuItem ("Une base de règles ...", Isys_fleche);
				Abc.addActionListener (this);
				Ab.add(Abc);
				
			// Fermer.
			Ac = new JMenuItem ("Fermer", Isys_close);
			Ac.addActionListener (this);
			A.add(Ac);

			A.addSeparator();

			// Enregistrer.
			Ad = new JMenu ("       Enregistrer");
			Ad.addActionListener (this);
			A.add(Ad);
			
				// Sous menu de "Enregistrer".
				Ada = new JMenuItem ("La base de faits ...", Isys_save);
				Ada.addActionListener (this);
				Ad.add(Ada);
				
				Adb = new JMenuItem ("La base de règles ...", Isys_save);
				Adb.addActionListener (this);
				Ad.add(Adb);

			// Enregistrer tout.
			Ae = new JMenuItem ("Enregistrer tout", Isys_save_all);
			Ae.addActionListener (this);
			Ae.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
			A.add(Ae);

			// Enregistrer sous.
			Af = new JMenu ("       Enregistrer sous ...");
			Af.addActionListener (this);
			A.add(Af);
			
				// Sous menu de "Enregistrer sous".
				Afa = new JMenuItem ("Le projet ...", Isys_save_as);
				Afa.addActionListener (this);
				Af.add(Afa);
				
				Afb = new JMenuItem ("La base de faits ...", Isys_save_as);
				Afb.addActionListener (this);
				Af.add(Afb);
				
				Afc = new JMenuItem ("La base de règles ...", Isys_save_as);
				Afc.addActionListener (this);
				Af.add(Afc);

			A.addSeparator();

			// Imprimer.
			Ag = new JMenu ("       Imprimer ...");
			Ag.addActionListener (this);
			A.add(Ag);
			
				// Sous menu de "Imprimer".
				Aga = new JMenuItem ("La base de faits ...", Isys_print);
				Aga.addActionListener (this);
				Ag.add(Aga);
				
				Agb = new JMenuItem ("La base de règles ...", Isys_print);
				Agb.addActionListener (this);
				Ag.add(Agb);

			A.addSeparator();		

			// Quitter.
			Ah = new JMenuItem (" Quitter", Isys_quit);
			Ah.addActionListener (this);
			A.add(Ah);


		// Utilisation.
		B = new JMenu ("Utilisation");
		B.setMnemonic('U');
		barreMenus.add(B);

			// Cr�ation d'un groupe de JRadio.
			ButtonGroup groupe = new ButtonGroup();			
			
			// Nouvelle recherche.
			Ba = new JRadioButton ("Nouvelle recherche", false);
			Ba.addActionListener (this);				
			B.add(Ba);
			groupe.add(Ba);

			// Gestion du Syst�me Expert.
			Bb = new JRadioButton ("Gestion du Système Expert.", true);
			Bb.addActionListener (this);				
			B.add(Bb);
			groupe.add(Bb);

		// Affichage.
		C = new JMenu ("Affichage");
		C.setMnemonic('A');
		barreMenus.add(C);

			// Les faits.
			Ca = new JCheckBox ("Base de faits", true);
			Ca.addActionListener (this);
			C.add(Ca);

			// Les r�gles.
			Cb = new JCheckBox ("Base de règles", true);
			Cb.addActionListener (this);
			C.add(Cb);

			// Les r�sultats.
			Cc = new JCheckBox ("Base de résultats", false);
			Cc.addActionListener (this);
			C.add(Cc);

		// Help.
		D = new JMenu ("Help");
		D.setMnemonic('H');
		barreMenus.add(D);

			// Docs.
			Da = new JMenuItem ("Documentation d'aide", Ihelp_doc);
			Da.addActionListener (this);
			D.add(Da);

			// About.
			Db = new JMenuItem ("A propos de S.E...", Ihelp_about);
			Db.addActionListener (this);
			D.add(Db);


		// Cr�ation de la barre d'outils.	
		barreOutils = new JToolBar();
		contenu.add(barreOutils, "North");
		barreOutils.setVisible(true);

			// Cr�ation des boutons.
			B_a = new JButton (Iaff_b);
			B_a.setToolTipText("Nouvelle recherche.");
			B_a.addActionListener (this);

			B_b = new JButton (Iaff_b);
			B_b.setToolTipText("Gestion du Système Expert.");
			B_b.addActionListener (this);

			B_c = new JButton (Iaff_b);
			B_c.setToolTipText("Afficher la base de faits");
			B_c.addActionListener (this);

			B_d = new JButton (Iaff_b);
			B_d.setToolTipText("Afficher la base de règles");
			B_d.addActionListener (this);

			B_e = new JButton (Iaff_b);
			B_e.setToolTipText("Afficher la base de résultats");
			B_e.addActionListener (this);

			// Ajout � la barre d'outils.
			barreOutils.add(B_a);
			barreOutils.add(B_b);
			barreOutils.add(B_c);
			barreOutils.add(B_d);
			barreOutils.add(B_e);
		
		
		// Les diff�rentes fen�tre suppl�mentaires.
		
			// Affichage des faits.
			boite4 = new AFaits(LF);
			
			// Affichage des r�gles.
			boite5 = new ARegles(LR);

			// Gestion des faits.
			String tab1[] = {"Ajouter un élément à la liste de faits", "Supprimer un élément de la liste de faits", "Renommer un fait de la liste de faits"};
			boite1 = new JDFaits(larg-358, 75, 350, 287, this, tab1, LR, LF);				
	
			// Gestion des r�gles.
			String tab2[] = {"Ajouter une règle", "Supprimer une règle", "Supprimer toutes les règles",
					        "Ajouter une liste de fait(s) à une règle", "Ajouter une liste de conséquence(s) à une règle",
					        "Suppression de fait(s) d'une règle", "Suppression de conséquence(s) d'une règle",
					        "Supprimer la liste de fait(s) d'une règle", "Supprimer la liste de conséquence(s) d'une règle"};
			
			boite2 = new JDRegles(larg-358, 365, 350, 371, this, tab2, LR, LF, boite4, boite5);	
			
			// Moteur d'inf�rence.
			boite3 = new JDMoteur(larg-358, 75, 350, 259, this, LR, LF);
			
	
		// Affichage de la fen�tre principale.
		setVisible(true);
		
		// S�lection des fen�tre secondaire � l'ouverture.
		boite1.setVoir(true);
		boite2.setVoir(true);
		boite3.setVoir(false);
		boite3.setVoirRes(false);
		boite4.setVoir(true);
		boite5.setVoir(true);	
		
 	////////////////////////////// Fonction lors de la fermetrue de la fen�tre :	
	addWindowListener (new WindowAdapter()
				{	public void windowClosing (WindowEvent e)
					{ System.exit(0);
					}
				});			
	}


	///////////////////////////////////////////// Actions lors d'une s�lection :
	public void actionPerformed (ActionEvent e)
	{
		// D�claration des variables.
		int choix;
		
		// Action sur le menu.
		Object source =	e.getSource();
		
		// Nouveau.
		if (source == Aa)
		{
			// Initialisation des bases.
			LF.InitListe();
			LR.InitListe();
			
			// Initialisation de NbSaves dans JDMoteur {Necessaire au
			// Remplissage de la matrice.
			boite3.setNbSaves(0);
			
			// Initialisation des adresses fichiers.
			reinitChemin();
			
			// S�lection des fen�tres.
			boite1.setVoir(true);
			boite2.setVoir(true);
			boite3.setVoir(false);
			boite3.setVoirRes(false);
			boite4.setVoir(true);
			boite5.setVoir(true);

			// Message d'inforamtion.
			JOptionPane.showMessageDialog(this, "Système Expert initialisé !", "Nouveau Système Expert", JOptionPane.INFORMATION_MESSAGE);
			setTitle("S.E - Système Expert [Nouveau]");
		}
		
		// Charger.
			// Syst�me Expert.
			if (source == Aba) {JDChargerSE(this);}
			// Base de faits.
			if (source == Abb) {JDChargerBF(this);}
			// Base de r�gles.
			if (source == Abc) {JDChargerBR(this);}
			
		// Fermer.
		if (source == Ac)
		{
			choix = JOptionPane.showConfirmDialog(this, "Voulez vous sauver le Système avant de le fermer ?");
			
			if (choix == 0)  // Sauver et quitter.
			{
				JDSauverSE(this);
				
				// Initialisation des bases.
				LF.setPremier (null);
				LR.setPremier (null);
				
				// Initialisation de NbSaves dans JDMoteur {Necessaire au
				// Remplissage de la matrice.
				boite3.setNbSaves(0);
				
				// Initialisation des adresses fichiers.
				reinitChemin();
	
				// S�lection des fen�tres.
				boite1.setVoir(false);
				boite2.setVoir(false);
				boite3.setVoir(false);
				boite3.setVoirRes(false);
				boite4.setVoir(false);
				boite5.setVoir(false);
				
				setTitle("S.E - Système Expert [Nouveau]");					
			}
			else
			{
				if (choix == 1)  // Fermer.
				{
					// Initialisation des bases.
					LF.InitListe();
					LR.InitListe();

					// Initialisation de NbSaves dans JDMoteur {Necessaire au
					// Remplissage de la matrice.
					boite3.setNbSaves(0);
					
					// Initialisation des adresses fichiers.
					reinitChemin();
		
					// S�lection des fen�tres.
					boite1.setVoir(false);
					boite2.setVoir(false);
					boite3.setVoir(false);
					boite3.setVoirRes(false);
					boite4.setVoir(false);
					boite5.setVoir(false);
					
					setTitle("S.E - Système Expert [Nouveau]");					
				}
			}
		}
		
		// Sauver.
			// Base de faits.
			if (source == Ada) {JDSauverBF(this);}
			// Base de r�gles.
			if (source == Adb) {JDSauverBR(this);}
			// Syst�me Expert.
			if (source == Ae) {JDSauverSE(this);}
			
		// Sauver sous.				
			// Base de faits.
			if (source == Afa) {JDSauverSousSE(this);}
			// Base de r�gles.
			if (source == Afb) {JDSauverSousBF(this);}
			// Syst�me Expert.
			if (source == Afc) {JDSauverSousBR(this);}
			
		// Quitter.
		if (source == Ah)
		{
			choix = JOptionPane.showConfirmDialog(this, "Voulez vous sauver le Système avant de quitter ?");
			
			if (choix == 0)  // Sauver et quitter.
			{
				JDSauverSE(this);
				System.exit(0);	
			}
			else
			{
				if (choix == 1)  // Quitter.
				{
					System.exit(0);
				}
			}
		}
		
		// Utilisation.
			// Nouvelle recherche.
			if ((source == Ba) || (source == B_a))
			{
				// Affichage ou non des fen�tres.
				boite1.setVoir(false);
				boite2.setVoir(false);
				boite3.setVoir(true);
				boite3.setVoirRes(true);
				boite4.setVoir(true);
				boite5.setVoir(false);
				
				// S�lection ou non des cases.
				Ca.setSelected(true);
				Cb.setSelected(false);
				Cc.setSelected(true);				
			}
			
			//Gestion du Syst�me Expert.
			if ((source == Bb) || (source == B_b))
			{
				// Affichage ou non des fen�tres.
				boite1.setVoir(true);
				boite2.setVoir(true);
				boite3.setVoir(false);
				boite3.setVoirRes(false);
				boite4.setVoir(true);
				boite5.setVoir(true);				

				// S�lection ou non des cases.
				Ca.setSelected(true);
				Cb.setSelected(true);
				Cc.setSelected(false);	
			}
			
		// Affichage.
			// Base de faits.
			if (source == Ca)
			{
				if (Ca.isSelected())
				{boite4.setVoir(true);}
				else
				{boite4.setVoir(false);}	
			}
			// Base de r�gles.
			if (source == Cb)
			{
				if (Cb.isSelected())
				{boite5.setVoir(true);}
				else
				{boite5.setVoir(false);}	
			}			
			// Base de r�sultats.			
			if (source == Cc)
			{
				if (Cc.isSelected())
				{boite3.setVoirRes(true);}
				else
				{boite3.setVoirRes(false);}	
			}
			
		// Help.
			// Docs.
			if (source == Da){}
			// About.
			if (source == Db)
			{			
				if (boite6 == null)
				{
					boite6 = new JDAbout(larg/2-200, 210, 350, 250, this);
				}
				else
				{
					boite6.setVoir(true);	
				}
			}
					
		
		// Bo�te � outils.
		if (source == B_c){boite4.setVoir(true);}
		if (source == B_d){boite5.setVoir(true);}
		if (source == B_e){boite3.setVoirRes(true);}	 
	}
	
/********************************************************** Fonctions du menu */	
	
	
	// Fonction * : void JDChargerBF(JFrame fen).
	// Cette fonction permet de charger une base de faits.
	void JDChargerBF(JFrame fen)
	{
		// D�claration des variables.
    	JDialog boite;
		String txt, repTmp, ficTmp;

    	// Cr�ation de la fen�tre.
		txt = JOptionPane.showInputDialog (fen, "Entrer l'adresse :", "Charger une base de faits ...", JOptionPane.QUESTION_MESSAGE);
		ficTmp = ExtraitNomfichier(txt);
		repTmp = ExtraitNomrepertoire(txt);
		
		
		// Chargement si validation.
		if (txt != null)
		{
			LF.InitListe();
			OuvrirFileFAI(repTmp, ficTmp, fen);			
		}
	}


	// Fonction * : void JDChargerBR(JFrame fen).
	// Cette fonction permet de charger une base de r�gles.
	void JDChargerBR(JFrame fen)
	{
		// D�claration des variables.
    	JDialog boite;
		String txt, repTmp, ficTmp;

    	// Cr�ation de la fen�tre.
		txt = JOptionPane.showInputDialog (fen, "Entrer l'adresse :", "Charger une base de règles ...", JOptionPane.QUESTION_MESSAGE);
		ficTmp = ExtraitNomfichier(txt);
		repTmp = ExtraitNomrepertoire(txt);
		
		// Chargement si validation.
		if (txt != null)
		{
			LR.InitListe();
			OuvrirFileRGL(repTmp, ficTmp, fen);			
		}
	}


	// Fonction * : void JDChargerSE(JFrame fen).
	// Cette fonction permet de charger le Syst�me Expert.
	void JDChargerSE(JFrame fen)
	{
		// D�claration des variables.
    	JDialog boite;
		String txt, repTmp, ficTmp;

    	// Cr�ation de la fen�tre.
		txt = JOptionPane.showInputDialog (fen, "Entrer l'adresse :", "Charger un Système Expert ...", JOptionPane.QUESTION_MESSAGE);
		ficTmp = ExtraitNomfichier(txt);
		repTmp = ExtraitNomrepertoire(txt);
		
		// Chargement si validation.
		if (txt != null)
		{
			LF.InitListe();
			LR.InitListe();
			
			OuvrirFilePRJ(repTmp, ficTmp, fen);
			setTitle("S.E - Système Expert " + "[" + ficTmp + "]");
		}
	}
	

	// Fonction * :	void JDSauverBF(JFrame fen).
	// Cette fonction permet de sauver la base de faits.
	void JDSauverBF(JFrame fen)
	{
		if (ficBF != null)
		{
			EnregistrerFileFAI(repBF, ficBF, fen);
		}
		else
		{
			JDSauverSousBF(fen);
		}								
	}
	
	
	// * Fonction : void JDSauverBR(JFrame fen).
	// Cette fonction permet de sauver la base de r�gles.
	void JDSauverBR(JFrame fen)
	{
		if (ficBR!= null)
		{
			EnregistrerFileRGL(repBR, ficBR, fen);
		}
		else
		{
			JDSauverSousBR(fen);
		}
	}
	

	// * Fonction :	void JDSauverSE(JFrame fen).
	// Cette fonction permet de sauver le Syst�me Expert.
	void JDSauverSE(JFrame fen)
	{
		if (ficSE != null)
		{
			EnregistrerFilePRJ(repSE, ficSE, fen);
			setTitle("S.E - Système Expert " + "[" + ficSE + "]");
		}
		else
		{
			JDSauverSousSE(fen);
		}		
	}	
	
	
	// * Fonction : void JDSauverSousSE(JFrame fen).
	// Cette fonction permet de sauver sous... le Syst�me Expert.
	void JDSauverSousSE(JFrame fen)
	{
		// D�claration des variables.
		String txt;
		String ficTmp, repTmp;

    	// Cr�ation de la fen�tre.
		txt = JOptionPane.showInputDialog (fen, "Entrer l'adresse :", "Sauver le Système Expert sous...", JOptionPane.QUESTION_MESSAGE);

		// Sauvegarde si validation.
		if (txt != null)
		{
			ficTmp = ExtraitNomfichier(txt);
			repTmp = ExtraitNomrepertoire(txt);
						
			EnregistrerFileFAI(repTmp, ficTmp, fen);
			EnregistrerFileRGL(repTmp, ficTmp, fen);
			EnregistrerFilePRJ(repTmp, ficTmp, fen);
			JOptionPane.showMessageDialog(fen, "Projet sauvegardé à l'adresse : " + repTmp + ficTmp + ".PRJ" );
			
			setTitle("S.E - Système Expert " + "[" + ficTmp + "]");
		}		
	}


	// * Fonction : void JDSauverSousBF(JFrame fen).
	// Cette fonction permet de sauver sous... la base de faits.
	void JDSauverSousBF(JFrame fen)
	{
		// D�claration des variables.
		String txt;
		String ficTmp, repTmp;

    	// Cr�ation de la fen�tre.
		txt = JOptionPane.showInputDialog (fen, "Entrer l'adresse :", "Sauver la base de faits sous...", JOptionPane.QUESTION_MESSAGE);	

		// Sauvegarde si validation.
		if (txt != null)
		{
			ficTmp = ExtraitNomfichier(txt);
			repTmp = ExtraitNomrepertoire(txt);
			
			EnregistrerFileFAI(repTmp, ficTmp, fen);
			JOptionPane.showMessageDialog(fen, "Base de faits sauvegardée à l'adresse : " + repTmp + ficTmp + ".FAI" );
		}
	}
	

	// * Fonction :	void JDSauverSousBR(JFrame fen).
	// Cette fonction permet de sauver sous... la base de r�gles.
	void JDSauverSousBR(JFrame fen)
	{
		// D�claration des variables.
		String txt;
		String ficTmp, repTmp;

    	// Cr�ation de la fen�tre.
		txt = JOptionPane.showInputDialog (fen, "Entrer l'adresse :", "Sauver la base de règles sous...", JOptionPane.QUESTION_MESSAGE);
		
		// Sauvegarde si validation.
		if (txt != null)
		{
			ficTmp = ExtraitNomfichier(txt);
			repTmp = ExtraitNomrepertoire(txt);
			
			EnregistrerFileRGL(repTmp, ficTmp, fen);
			JOptionPane.showMessageDialog(fen, "Base de règles sauvegardée à l'adresse : " + repTmp + ficTmp + ".RGL" );
		}
	}	
		
		
/*********************************************************** Fonctions avales */
	
	
	// * Fonction : void OuvrirFilePRJ(String Repertoire, String Fichier, JFrame fen).
	// Cette fonction permet d'ouvrir le fichier "Projet".
	void OuvrirFilePRJ(String Repertoire, String Fichier, JFrame fen)
	{
		String repTmp, ficTmp;
		String FileF, FileR, Chemin;
		
		try
		{
			if (!A1extension(Fichier))
				Fichier = Fichier + ".PRJ";
			  								
			Chemin = Repertoire + Fichier;
			
			// Ouverture du fichier "Projet".
			BufferedReader FichierProj = new BufferedReader (new FileReader (Chemin));
			
			// Mise en m�moire de l'emplacement du fichier de faits.
			FileF = FichierProj.readLine();
			ficTmp = ExtraitNomfichier(FileF);
			repTmp = ExtraitNomrepertoire(FileF);
			
			
			OuvrirFileFAI(repTmp, ficTmp, fen);						
			System.out.println("Fait : " + FileF);
			
			// Mise en m�moire de l'emplacement du fichier de r�gles.
			FileR = FichierProj.readLine();
			ficTmp = ExtraitNomfichier(FileR);
			repTmp = ExtraitNomrepertoire(FileR);			
			
			
			OuvrirFileRGL(repTmp, ficTmp, fen);				
			System.out.println("Regles : " + FileR);			
			
			// Fermeture du fichier.
			FichierProj.close();
			
			repSE = Repertoire;				
			ficSE = Fichier;
			
		}
		catch(IOException err)
		{
			JOptionPane.showMessageDialog(fen, "Le fichier PRJ n'est pas un fichier valide !", "Erreur !", JOptionPane.ERROR_MESSAGE);	
		}			
	}
	

	// * Fonction : void OuvrirFileFAI(String Repertoire, String Fichier, JFrame fen).
	// Cette fonction permet d'ouvrir le fichier "Faits".
	void OuvrirFileFAI(String Repertoire, String Fichier, JFrame fen)
	{
		Faits F;
		char charTmp;
		String strTmp, strTmp2, Chemin;
		int numTmp, numTmp2;
		 
		try
		{
			
			if (!A1extension(Fichier))
			  Fichier = Fichier + ".FAI";
				
			Chemin = Repertoire + Fichier;
						
			// Ouverture du fichier "faits".
			BufferedReader FichierFAI = new BufferedReader (new FileReader (Chemin));
			
			do 
			{
				// R�cup�ration de la ligne.
				strTmp = FichierFAI.readLine();	
				
				// Si la ligne n'est pas vide.
				if (strTmp != null)
				{ 
					// Initialisation des variables.
					strTmp2 = "";
					numTmp = 0;
					charTmp = 'A';
					
					// R�cup�ration du num�ro.
					while (charTmp!=' ')
					{
						charTmp=strTmp.charAt(numTmp);
						
						if (charTmp!=' ')
						{
							strTmp2	= strTmp2 + charTmp;
						}
						numTmp++;	
					}
		
					numTmp2=Integer.parseInt(strTmp2);
					
					// Initialisation des variables.
					strTmp2 = "";
					charTmp = 'A';			
					
					// R�cup�ration de la d�scription.
					while (charTmp!='.')
					{
						charTmp=strTmp.charAt(numTmp);
						
						if (charTmp!='.')
						{
							strTmp2	= strTmp2 + charTmp;
						}
						numTmp++;	
					}			
					
					// On cr�e le nouvel �l�ment.
					F = new Faits();
		
					// Affectation des propri�t�s.
					F.setNum(numTmp2);
					F.setDesc(strTmp2);
						
					// On ajoute le fait cr�� � la liste de faits.
					LF.AjouterFait(F);
				}
			}
			while (strTmp != null);	
			
			// Fermeture du fichier.
			FichierFAI.close();
			
			repBF = Repertoire;				
			ficBF = Fichier;
		}
		catch(IOException err)
		{
			JOptionPane.showMessageDialog(fen, "Le fichier FAI n'est pas un fichier valide !", "Erreur !", JOptionPane.ERROR_MESSAGE);	
		}			
	}


	// * Fonction : void OuvrirFileRGL(String Repertoire, String Fichier, JFrame fen).
	// Cette fonction permet d'ouvrir le fichier "R�gles".
	void OuvrirFileRGL(String Repertoire, String Fichier, JFrame fen)
	{
		LFaits ListeF, ListeC;
		Regles R;
		char charTmp;
		String strTmp, strTmp2, strTmp3, Chemin;
		int numTmp, numTmp2;		
		 
		try
		{
			if (!A1extension(Fichier))
			  Fichier = Fichier + ".RGL";
				
			Chemin = Repertoire + Fichier;
			
			// Ouverture du fichier "r�gles".
			BufferedReader FichierRGL = new BufferedReader (new FileReader (Chemin));
			
			do 
			{
				// R�cup�ration de la ligne.
				strTmp = FichierRGL.readLine();	
				
				// Si la ligne n'est pas vide.
				if (strTmp != null)
				{ 
					// Initialisation des variables.
					strTmp2 = "";
					numTmp = 0;
					charTmp = 'A';
					
					// R�cup�ration du num�ro de la r�gle.
					while (charTmp!=':')
					{
						charTmp=strTmp.charAt(numTmp);
						
						if (charTmp!=':')
						{
							strTmp2	= strTmp2 + charTmp;
						}
						numTmp++;	
					}

					numTmp2 = Integer.parseInt(strTmp2);

					// Initialisation des variables.
					strTmp2 = "";
					charTmp = 'A';
					
					// R�cup�ration des faits.
					while (charTmp!='=')
					{
						charTmp=strTmp.charAt(numTmp);
						
						if (charTmp!='=')
						{
							strTmp2	= strTmp2 + charTmp;
						}
						numTmp++;	
					}
		
					strTmp3 = strTmp2;
					
					// Initialisation des variables.
					strTmp2 = "";
					charTmp = 'A';			
					
					// R�cup�ration de cons�quences.
					while (charTmp!='.')
					{
						charTmp=strTmp.charAt(numTmp);
						
						if (charTmp!='.')
						{
							strTmp2	= strTmp2 + charTmp;
						}
						numTmp++;	
					}			
					
					// On cr�e deux nouvelles listes de faits.
					ListeF = new LFaits();
					ListeC = new LFaits();
		
					// On ins�re les �l�ments.
					ListeF.InsertListe(LF, strTmp3);
					ListeC.InsertListe(LF, strTmp2);
						
					// On cr�e la nouvelle r�gle.
					R = new Regles();
						
					// Affectation des propri�t�s.
					R.setNum(numTmp2);
					R.setLFait(ListeF);
					R.setLCons(ListeC);
	
					// On l'ajoute � la liste de r�gles.
					LR.Ajouter_Regle(R);
				}
			}
			while (strTmp != null);	
			
			// Fermeture du fichier.
			FichierRGL.close();
			
			repBR = Repertoire;				
			ficBR = Fichier;
		}
		catch(IOException err)
		{
			JOptionPane.showMessageDialog(fen, "Le fichier RGL n'est pas un fichier valide !", "Erreur !", JOptionPane.ERROR_MESSAGE);	
		}			
	}


	// * Foncion : void EnregistrerFilePRJ(String Repertoire, String Fichier, JFrame fen).
	// Cette fonction permet d'enregistrer le fichier "Projet".
	void EnregistrerFilePRJ(String Repertoire, String Fichier, JFrame fen)
	{
		String repTmp, ficTmp;
		String FileF, FileR, Chemin;
		
		try
		{
			if (!A1extension(Fichier))
				Fichier = Fichier + ".PRJ";
			  								
			Chemin = Repertoire + Fichier;
			
			// Ouverture du fichier "faits" en ecriture.
			BufferedWriter FichierPRJ = new BufferedWriter (new FileWriter (Chemin));
			
			FichierPRJ.write(repBF + ficBF);
			FichierPRJ.newLine();
			FichierPRJ.write(repBR + ficBR);
			FichierPRJ.newLine();
			
			// Sauvegarde des bases.
			JDSauverBF(fen);
			JDSauverBR(fen);		
							
			// Fermeture du fichier.
			FichierPRJ.close();	
			
			repSE = Repertoire;				
			ficSE = Fichier;
		}
		catch(IOException err)
		{
			JOptionPane.showMessageDialog(fen, "Le fichier PRJ n'est pas un fichier valide !", "Erreur !", JOptionPane.ERROR_MESSAGE);	
		}			
	}
	

	// * Fonction : void EnregistrerFileFAI(String Repertoire, String Fichier, JFrame fen).
	// Cette fonction permet d'enregistrer le fichier "Faits".
	void EnregistrerFileFAI(String Repertoire, String Fichier, JFrame fen)
	{
		
		Faits F;
		String Chemin;
		
		if (!A1extension(Fichier))
			  Fichier = Fichier + ".FAI";
				
		Chemin = Repertoire + Fichier;				
				 
		try
		{
			// Ouverture du fichier "faits" en ecriture.
			BufferedWriter FichierFAI = new BufferedWriter (new FileWriter (Chemin));
			
			F = LF.getPremier();
			
			while(F != null)
			{
				FichierFAI.write(F.getNum() + " " + F.getDesc() + ".");
				FichierFAI.newLine();
				F = F.getSuivant();
			}
			
			// Fermeture du fichier.
			FichierFAI.close();
			
			repBF = Repertoire;				
			ficBF = Fichier;
		}
		catch(IOException err)
		{
			JOptionPane.showMessageDialog(fen, "Le fichier FAI n'est pas un fichier valide !", "Erreur !", JOptionPane.ERROR_MESSAGE);	
		}
		
	}

	
	// * Fonction : void EnregistrerFileRGL(String Repertoire, String Fichier, JFrame fen).
	// Cette fonction permet d'enregistrer le fichier "Regles".
	void EnregistrerFileRGL(String Repertoire, String Fichier, JFrame fen)
	{
		
		Regles R;
		Faits F;
		String Chemin;		
				 
		try
		{
			if (!A1extension(Fichier))
			  Fichier = Fichier + ".RGL";
				
			Chemin = Repertoire + Fichier;
			
			// Ouverture du fichier "r�gles" en ecriture.
			BufferedWriter FichierRGL = new BufferedWriter (new FileWriter (Chemin));
			
			R = LR.getPremier();
			
			while(R != null)
			{
				
				// Enregistrement de la liste de fait initiaux
				FichierRGL.write(R.getNum() + ":");
				F = R.getLFait().getPremier();
				
				while(F != null)
				{
					FichierRGL.write(F.getNum() + ";");										
					F = F.getSuivant();
				}
				
				// Enregistrement de la liste de fait consequences
				FichierRGL.write("=");
				F = R.getLCons().getPremier();
								
				while(F != null)
				{
					FichierRGL.write(F.getNum() + ";");															
					F = F.getSuivant();
				}
				FichierRGL.write(".");
				FichierRGL.newLine();				
				R = R.getSuivant();
				
			}										
			
			// Fermeture du fichier.
			FichierRGL.close();
			
			repBR = Repertoire;				
			ficBR = Fichier;
		}
		catch(IOException err)
		{
			JOptionPane.showMessageDialog(fen, "Le fichier RGL n'est pas un fichier valide !", "Erreur !", JOptionPane.ERROR_MESSAGE);	
		}				
		
	}
	
	
	// * Fonction : String ExtraitNomfichier(String S).
	// Cette fonction permet d'extraire le nom du fichier d'une adresse compl�te.
	String ExtraitNomfichier(String S)
	{		
		int numTmp=0, numTmp2, fin=1;
		char charTmp;
		String strrep="", strfic="";
		int nbslash=0;		
			
		while(numTmp < S.length())
		{
			// On r�cup�re le carat�re.
			charTmp = S.charAt(numTmp);

			if (charTmp == '/')
			{
				
				nbslash++;				
				if (nbslash > 0)
				{
					
					strrep = strrep + strfic;
					strfic = "";
				}
				else
				{
					// On r�alise une concat�nation.
					strfic = strfic + charTmp;
				}
			}
			else
			{
				strfic = strfic + charTmp;						
			}
			numTmp++;
		}
		return strfic;
	}
	

	// * Fonction : String ExtraitNomrepertoire(String S).
	// Cette fonction permet d'extraire le nom du repertoire d'une adresse
	// Compl�te.
	String ExtraitNomrepertoire(String S)
	{		
		int numTmp=0, nbslash=0;
		char charTmp;
		String strrep="", strfic="";		
			
		while(numTmp < S.length())
		{
			// On r�cup�re le carat�re.
			charTmp = S.charAt(numTmp);						

			if (charTmp == '/')
			{
				nbslash++;
				if (nbslash > 0)
				{
					strrep = strrep + strfic + charTmp;					
					strfic = "";
				}
				else
				{
					// On r�alise une concat�nation.
					strfic = strfic + charTmp;
				}
			}
			else
			{
				strfic = strfic + charTmp;						
			}
			numTmp++;
		}
		return strrep;
	}
	
	
	// * Fonction : boolean A1extension(String S).
	// Cette fonction retourne "true" si le fichier a une extension.
	boolean A1extension(String S)
	{
		boolean reponse=false;
		int numTmp=0;
		char charTmp;		
			
		while((numTmp < S.length()) && (!reponse))
		{
			// On r�cup�re le carat�re.
			charTmp = S.charAt(numTmp);

			if (charTmp == '.')
			{
				reponse = true;
			}
			numTmp++;
		}
		return reponse;		
	}
	
	
	// * Fonction : void reinitChemin().
	// Cette fonction permet de r�initialiser les chemin des fichiers.
	void reinitChemin()
	{
		repSE = null;
		ficSE = null;
		repBF = null;
		ficBF = null;
		repBR = null;
		ficBR = null;
	}
}



///////////////////////////////////////////////////////////////////// Panneaux :

class panneau extends JPanel
{
	private ImageIcon Ifond;
				
	public panneau()
	{
		// Image de fond.
		Ifond = new ImageIcon ("img/fond2.jpeg");				
	}
		
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.drawImage (Ifond.getImage(), 0, 0, null);
				
		g.setColor (Color.black);	
		g.drawString ("Bienvenue dans S.E - Système Expert.", 20, 20);			
	}	
}