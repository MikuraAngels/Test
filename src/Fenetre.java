import javax.swing.*;

import java.awt.*;
import java.awt.event.* ;
import java.sql.Connection;
import java.util.ArrayList;

public class Fenetre extends JFrame implements ActionListener{
	private JMenuBar menu;
	private JMenu menuParametre;
	private JMenuItem menuRevenirAccueil;
	private JMenuItem menuQuitter;
	private JMenu menuContact;
	private JMenuItem menuAjouterContact;
	private JMenuItem menuVoirContacts;
	
	private JPanel panel;
	private JLabel lblBienvenue1;
	private JLabel lblBienvenue2;
	
	private JPanel panel1;
	private JLabel lblNom;
	private JTextField jtfNom;
	private JLabel lblPrenom;
	private JTextField jtfPrenom;
	private JLabel lblEmail;
	private JTextField jtfEmail;
	private JLabel lblCommentaire;
	private JTextArea jtaCommentaire;
	private JButton btnValider;
	private JLabel lblReussite;
	
	private JPanel panel2;
	private JLabel lblTest;
	private JTable tableau;
	private JScrollPane scrollpane;
	private String[] entetes = {"Nom", "Prénom", "Email", "Commentaire"};
	private Object[][] test;
	
	
	Fenetre(ArrayList<Contact> mesContacts){
		this.setTitle("Accueil");//Titre de la fenêtre.
		/* Fermeture de la fenêtre lorsque l'on clique sur la croix
		  (sinon la fenêtre sera fermée mais le programme toujours en cours d'exécution).*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(220, 320);// Determine la taille de la fenêtre.
		this.setResizable(false);// On interdit le redimensionnement de la fenêtre.
		
		/* Création de la barre de Menu */
		menu = new JMenuBar();
		menuParametre = new JMenu("Paramètre");
		menuRevenirAccueil = new JMenuItem ("Revenir à l'accueil");
		menuQuitter = new JMenuItem ("Quitter");
		menuContact = new JMenu("Contact");	
		menuAjouterContact = new JMenuItem ("Ajouter un contact");
		menuVoirContacts = new JMenuItem ("Voir ses contacts");
		menuParametre.add(menuRevenirAccueil);
		menuParametre.add(menuQuitter);
		menuContact.add(menuAjouterContact);
		menuContact.add(menuVoirContacts);
		menu.add(menuParametre);
		menu.add(menuContact);
		this.setJMenuBar(menu);
		
		/* Panel de bienvenue */
		panel = new JPanel();
		this.getContentPane().add(panel);
		
		lblBienvenue1 = new JLabel ("Veuillez faire votre");
		lblBienvenue2 = new JLabel ("choix dans le menu !");
		panel.add(lblBienvenue1);
		panel.add(lblBienvenue2);
		
		/* Création du premier panel qui permet d'ajouter les contacts  */
		panel1 = new JPanel();
		/* Contenu du panel */
		lblNom = new JLabel("Nom      ");
		jtfNom = new JTextField();
		jtfNom.setPreferredSize(new Dimension(150, 30));
		lblPrenom = new JLabel("Prenom");
		jtfPrenom = new JTextField();
		jtfPrenom.setPreferredSize(new Dimension(150, 30));
		lblEmail = new JLabel("E-mail    ");
		jtfEmail = new JTextField();
		jtfEmail.setPreferredSize(new Dimension(150, 30));
		lblCommentaire = new JLabel("Commentaire");
		jtaCommentaire = new JTextArea();
		jtaCommentaire.setPreferredSize(new Dimension(150, 100));
		btnValider = new JButton("Valider");
		lblReussite = new JLabel("");
		
		/* Ajout du contenu au panel  */
		panel1.add(lblNom);
		panel1.add(jtfNom);
		panel1.add(lblPrenom);
		panel1.add(jtfPrenom);
		panel1.add(lblEmail);
		panel1.add(jtfEmail);
		panel1.add(lblCommentaire);
		panel1.add(jtaCommentaire);
		panel1.add(btnValider);
		panel1.add(lblReussite);
		
		
		/* Création du second panel qui permet voir les contacts */
		panel2 = new JPanel();
				
		test = new Object[mesContacts.size()][4];
		
		for(int i=0;  i < mesContacts.size(); i++){
			test[i][0]=mesContacts.get(i).getNom();	
			test[i][1]=mesContacts.get(i).getPrenom();	
			test[i][2]=mesContacts.get(i).getEmail();	
			test[i][3]=mesContacts.get(i).getCommentaire();	
		} 
		
		tableau = new JTable(test, entetes);
		scrollpane = new JScrollPane(tableau);
		scrollpane.setPreferredSize(new Dimension(420,260));
		panel2.add(scrollpane);
		
		/* Action Listener */
		menuRevenirAccueil.addActionListener(this);
		menuQuitter.addActionListener(this);
		menuAjouterContact.addActionListener(this);
		menuVoirContacts.addActionListener(this);
		btnValider.addActionListener(this);

	    this.setLocationRelativeTo(null); // Permet d'avoir la fenêtre au milieu de l'écran
	    this.setVisible(true); // Permet de voir la fenêtre
	   
	   
	   
	}
	
	/* Création des Action Listener pour le menu */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuRevenirAccueil){
			this.setVisible(false);
			this.remove(panel1);
			this.remove(panel2);
			this.getContentPane().add(panel);
			setTitle("Accueil");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(220, 320);// Determine la taille de la fenêtre.
			this.setVisible(true);
		}
		if(e.getSource() == menuQuitter){
			this.dispose();
		}
		
		if(e.getSource() == menuAjouterContact){
			this.setVisible(false);
			this.remove(panel);
			this.remove(panel2);
			this.getContentPane().add(panel1);
			setTitle("Ajouter contact");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(220, 320);// Determine la taille de la fenêtre.
			this.setVisible(true);
		}
		if(e.getSource() == menuVoirContacts){
			this.setVisible(false);
			this.remove(panel);
			this.remove(panel1);
			this.getContentPane().add(panel2, BorderLayout.CENTER);

			setTitle("Mes contatcs");
			this.setSize(450, 320);// Determine la taille de la fenêtre.
			this.setVisible(true);
		}
		if(e.getSource() == btnValider){
			ConnexBDD.ajouterContact(jtfNom.getText(),jtfPrenom.getText(),jtfEmail.getText(),jtaCommentaire.getText());
			lblReussite.setText("Ajout réussi !");
		}
	}
	
}
