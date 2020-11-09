package vue;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import controleur.Main;
import modele.Aliment;
import modele.Meuble;
import modele.Produit;
import modele.Data;
import modele.Personne;
import modele.Directeur;
import modele.Repertoire;


public class FrmMain {

	private static int x = 0;
	private static int y = 0;
	private static int largeur = 1024;
	private static int hauteur = 768;
	
	public static JFrame frame;
	private static JTable tablePersonne;
	private static JTable tableProduit;
	private static JScrollPane scrollPanePersonne;
	private static JScrollPane scrollPaneProduit;
	private static JRadioButton rdbtnClient;
	private static JRadioButton rdbtnEmploye;
	private static JRadioButton rdbtnDirecteur;
	JRadioButton rdbtnAlim;
	JRadioButton rdbtnMeuble;
	private static JButton btnDetail;
	private static JPanel panelDetail;
	private static JTextField textFieldId;
	private static JComboBox comboBoxRole;
	private static JComboBox comboBoxListeDir;
	private static JTextField textFieldPrenom;
	private static JTextField textFieldNom;
	private static JTextField textFieldCourriel;
	private static JDateChooser dateChooserDdn;
	private static JTextField textFieldSolde;
	private static JTextField textFieldSoldeAjout;
	private static JButton btnAjouterSolde;
	private static JButton btnEnregistrerFiche;
	private static JTextField textFieldRecherche;
	private static JButton btnRechercher;
	private static JTable table;
	private static JTable table_1;
	private static Directeur[] listeDir;
	private static Directeur directeur;
	private JRadioButton rdbtnAddAlim;
	private JRadioButton rdbtnAddMeuble;
	private JLabel lblNewLabel;
	private JLabel lblCouleur;
	private JLabel lblPoids;
	private JTextField txtNom;
	private JTextField txtPoids;
	private JTextField txtCouleur;
	private JTextField txtType;
	private JTextField txtHauteur;
	private JTextField txtPrix;
	


	/**
	 * Create the application.
	 */
	public FrmMain() {
		new Data();
		initialize();
	}
	
	/**
	 * Clic sur le bouton radio Client
	 */
	private static void rdbtnClient() {
		demandeListe("Client");
		
		// On déselectionne les deux autres boutons radios
		rdbtnEmploye.setSelected(false);
		rdbtnDirecteur.setSelected(false);
		
		// On désactive le bouton détail
		btnDetail.setEnabled(false);
		panelDetail.setVisible(false);
	}
	
	/**
	 * Clic sur le bouton radio Client
	 */
	private static void rdbtnEmploye() {
		demandeListe("Employe");
		
		// On déselectionne les deux autres boutons radios
		rdbtnClient.setSelected(false);
		rdbtnDirecteur.setSelected(false);
		
		// On désactive le bouton détail
		btnDetail.setEnabled(false);
		panelDetail.setVisible(false);
	}
	
	/**
	 * Clic sur le bouton radio Client
	 */
	private static void rdbtnDirecteur() {
		demandeListe("Directeur");
		
		// On déselectionne les deux autres boutons radios
		rdbtnClient.setSelected(false);
		rdbtnEmploye.setSelected(false);
		
		// On désactive le bouton détail
		btnDetail.setEnabled(false);
		panelDetail.setVisible(false);
	}
	
	/**
	 * Demande la liste des personnes
	 */
	private static void demandeListe(String Role) {
		Main.demandeFrmMainListePersonnes(Role);
		//afficheListe(Role);
	}
	
	/**
	 * Affiche la liste des personnes selon leur roles
	 * 
	 * @param Role Nom de la classe à afficher : "Client", "Employe", "Directeur"
	 */
	public static void afficheListe(String[][] data, String[] entete) {
	//public static void afficheListe(String Role) {
		//String[][] data = Repertoire.personneData(Role);
		//String[] entete = Repertoire.personneEntete();
		tablePersonne = new JTable(data, entete);
		
		// Lorsque une personne de la liste est sélectionnée,
		// le bouton « Détail » devient actif
		// https://stackoverflow.com/questions/10128064/jtable-selected-row-click-event
		tablePersonne.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				btnDetail.setEnabled(true);
				
				// On rafraichit le Détail de la personne au besoin
				if (panelDetail.isVisible()) {
					btnDetail();
				}
			}
			
		});
		
		// Largeur des colonnes : colonne Identifiant
		TableColumn col = tablePersonne.getColumnModel().getColumn(0);
        col.setPreferredWidth(0); 
		// colonne Date de naissance
        col = tablePersonne.getColumnModel().getColumn(3);
        col.setPreferredWidth(30);
		
        scrollPanePersonne.setViewportView(tablePersonne);
	}
	

	/**
	 * Affiche le détail de la personne
	 */
	private static void btnDetail() {
		// On récupère le # de la ligne sélectionnée
		int row = tablePersonne.getSelectedRow();
		
		// On récupère le Id de la personne
		// Si aucune ligne est sélectionnée, row == -1
		if (row >= 0) {
			int id = Integer.parseInt((String) tablePersonne.getValueAt(row, 0));
			Personne personne = Repertoire.searchById(id);
			textFieldId.setText("" + personne.getId());
			switch(personne.getRole()) {
				case "Client" :
					comboBoxRole.setSelectedIndex(0);
					break;
				case "Employe" :
					comboBoxRole.setSelectedIndex(1);
					break;
				case "Directeur" :
					comboBoxRole.setSelectedIndex(2);
					break;
			}
			textFieldPrenom.setText(personne.getPrenom());
			textFieldNom.setText(personne.getNom());
			textFieldCourriel.setText(personne.getCourriel());
			
			LocalDate Ddn = personne.getDdn();
			// https://stackoverflow.com/questions/22929237/convert-java-time-localdate-into-java-util-date-type
			Date date = Date.from(Ddn.atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateChooserDdn.setDate(date);
			
			DecimalFormat sf = new DecimalFormat("#,##0.00");
			textFieldSolde.setText(sf.format(personne.getSolde()));
		}
		panelDetail.setVisible(true);
	}
	
	/**
	 * Enregistre les données de la personne, pas l’ajout de solde
	 */
	private static void btnEnregistrerFiche() {
		
	}
	
	/**
	 * Clic sur le bouton radio Aliments
	 */
	private void rdbtnAlim() {
		afficheListeProduits("Aliment");
		
		// On déselectionne l'autre bouton radio
		rdbtnMeuble.setSelected(false);
		
	}
	
	/**
	 * Clic sur le bouton radio Meubles
	 */
	private void rdbtnMeuble() {
		afficheListeProduits("Meuble");
		
		// On déselectionne l'autre bouton radio
		rdbtnAlim.setSelected(false);
		
	}
	
	public static void afficheListeProduits(String sorte) {
		String[][] data = Repertoire.produitData(sorte);
		String[] entete = Repertoire.produitEntete(sorte);
		tableProduit = new JTable(data, entete);
		
        scrollPaneProduit.setViewportView(tableProduit);
	}
	
	/**
	 * Clic sur le bouton radio Aliment dans la section ajouter un produit
	 */
	private void rdbtnAddAlim() {
		
		// On déselectionne l'autre bouton radio
		rdbtnAddMeuble.setSelected(false);
		
		
		// On rend les champs de textes des attributs d'aliments éditable
		txtNom.setEditable(true);
		txtCouleur.setEditable(true);
		txtPoids.setEditable(true);
		
		// On rend les champs de textes des attributs de meubles pas éditable
		txtType.setEditable(false);
		txtHauteur.setEditable(false);
		txtPrix.setEditable(false);
		
		txtType.setText("");
		txtHauteur.setText("");
		txtPrix.setText("");
		
	}
	
	/**
	 * Clic sur le bouton radio Meuble dans la section ajouter un produit
	 */
	private void rdbtnAddMeuble() {
		
		// On déselectionne l'autre bouton radio
		rdbtnAddAlim.setSelected(false);
		
		
		// On rend les champs de textes des attributs d'aliments pas éditable
		txtNom.setEditable(false);
		txtCouleur.setEditable(false);
		txtPoids.setEditable(false);
		
		txtNom.setText("");
		txtCouleur.setText("");
		txtPoids.setText("");
		
		// On rend les champs de textes des attributs de meubles éditable
		txtType.setEditable(true);
		txtHauteur.setEditable(true);
		txtPrix.setEditable(true);
		
	}
	
	/**
	 * Clic sur le bouton ajouter produit
	 */
	private void btnAjouterProd() {
		
		// Récupérer le directeur
		directeur = (Directeur) comboBoxListeDir.getSelectedItem();
		
		if(rdbtnAddAlim.isSelected()) {
			// Récupérer les informations
			String nom = txtNom.getText();
			String couleur = txtCouleur.getText();
			double poids = Double.parseDouble(txtPoids.getText()); // Gérer exception!!
			
			// Ajouter l'aliment
			Produit aliment = new Aliment(nom,couleur,poids);
			directeur.addProduit(aliment);
			
			// Vider les champs de textes
			txtNom.setText("");
			txtCouleur.setText("");
			txtPoids.setText("");
			
			// Si on est en train d'afficher les aliments, mettre à jour la liste
			if(rdbtnAlim.isSelected()) afficheListeProduits("Aliment");
			
		} else if(rdbtnAddMeuble.isSelected()) {
			// Récupérer les informations
			String type = txtType.getText();
			double hauteur = Double.parseDouble(txtHauteur.getText());
			double prix = Double.parseDouble(txtPrix.getText()); // Gérer exception!!
			
			// Ajouter le meuble
			Produit meuble = new Meuble(type,hauteur,prix);
			directeur.addProduit(meuble);
			
			// Vider les champs de textes
			txtType.setText("");
			txtPrix.setText("");
			txtHauteur.setText("");
			
			// Si on est en train d'afficher les meubles, mettre à jour la liste
			if(rdbtnMeuble.isSelected()) afficheListeProduits("Meuble");
		}
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @param controle Le controleur
	 */
	private void initialize() {
		//this.controle = controle;
		frame = new JFrame();
		frame.setBounds(x, y, largeur, hauteur);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Le contenant des deux onglets
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// Onglet Personnes
		JPanel panelPersonnes = new JPanel();
		tabbedPane.addTab("Personnes", null, panelPersonnes, null);
		panelPersonnes.setLayout(null);
		
		scrollPanePersonne = new JScrollPane();
		scrollPanePersonne.setBounds(6, 6, 500, 688);
		panelPersonnes.add(scrollPanePersonne);
		
		
		rdbtnClient = new JRadioButton("Client.e.s");
		rdbtnClient.setBounds(518, 6, 141, 23);
		panelPersonnes.add(rdbtnClient);
		rdbtnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnClient();
			}
		});
		
		rdbtnEmploye = new JRadioButton("Employé.e.s");
		rdbtnEmploye.setBounds(518, 29, 141, 23);
		panelPersonnes.add(rdbtnEmploye);
		rdbtnEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnEmploye();
			}
		});
		
		rdbtnDirecteur = new JRadioButton("Directeur.trice.s");
		rdbtnDirecteur.setBounds(518, 51, 141, 23);
		panelPersonnes.add(rdbtnDirecteur);
		rdbtnDirecteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDirecteur();
			}
		});
	
		// Par défaut, on sélectionne le radio bouton Client
		rdbtnClient.setSelected(true);
		
		textFieldRecherche = new JTextField();
		textFieldRecherche.setBounds(739, 2, 65, 26);
		textFieldRecherche.setColumns(10);
		panelPersonnes.add(textFieldRecherche);
		
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(804, 2, 117, 29);
		panelPersonnes.add(btnRechercher);
		
		btnDetail = new JButton("Détail");
		btnDetail.setEnabled(false);
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetail();
			}
		});
		btnDetail.setBounds(804, 28, 117, 29);
		panelPersonnes.add(btnDetail);
		
		JButton button = new JButton("Nouvelle personne");
		button.setBounds(804, 55, 141, 29);
		panelPersonnes.add(button);
		
		panelDetail = new JPanel();
		panelDetail.setBounds(518, 96, 479, 598);
		panelDetail.setVisible(false);
		panelDetail.setLayout(null);
		panelPersonnes.add(panelDetail);
		
		JLabel lblId = new JLabel("Identifiant");
		lblId.setBounds(6, 6, 72, 16);
		panelDetail.add(lblId);
		
		// Champs identifiant
		textFieldId = new JTextField();
		textFieldId.setBounds(77, 1, 44, 26);
		textFieldId.setHorizontalAlignment(JTextField.RIGHT);
		textFieldId.setEditable(false);
		textFieldId.setBackground(Color.LIGHT_GRAY);
		panelDetail.add(textFieldId);
		
		// https://baptiste-wicht.developpez.com/tutoriels/java/swing/debutant/?page=listes
		// https://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
		String[] Roles = new String[] {"Client", "Employe", "Directeur"};
		comboBoxRole = new JComboBox(Roles);
		comboBoxRole.setBounds(364, 2, 109, 27);
		panelDetail.add(comboBoxRole);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(6, 34, 61, 16);
		panelDetail.add(lblPrenom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(77, 29, 156, 26);
		panelDetail.add(textFieldPrenom);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(247, 34, 37, 16);
		panelDetail.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(282, 29, 191, 26);
		panelDetail.add(textFieldNom);
		
		JLabel lblCourriel = new JLabel("Courriel");
		lblCourriel.setBounds(6, 60, 61, 16);
		panelDetail.add(lblCourriel);
		
		textFieldCourriel = new JTextField();
		textFieldCourriel.setBounds(77, 55, 396, 26);
		textFieldCourriel.setEditable(false);
		textFieldCourriel.setBackground(Color.LIGHT_GRAY);
		panelDetail.add(textFieldCourriel);
		
		JLabel lblDdn = new JLabel("Né.e le");
		lblDdn.setBounds(6, 85, 52, 16);
		panelDetail.add(lblDdn);
		
		JLabel lblSolde = new JLabel("Solde");
		lblSolde.setBounds(6, 112, 44, 16);
		panelDetail.add(lblSolde);
		
		textFieldSolde = new JTextField();
		textFieldSolde.setBounds(77, 107, 72, 26);
		textFieldSolde.setHorizontalAlignment(JTextField.RIGHT);
		textFieldSolde.setEditable(false);
		textFieldSolde.setBackground(Color.LIGHT_GRAY);
		panelDetail.add(textFieldSolde);
		
		textFieldSoldeAjout = new JTextField();
		textFieldSoldeAjout.setBounds(155, 107, 52, 26);
		textFieldSoldeAjout.setHorizontalAlignment(JTextField.RIGHT);
		panelDetail.add(textFieldSoldeAjout);
		
		
		btnAjouterSolde = new JButton("← Ajouter $");
		btnAjouterSolde.setBounds(205, 107, 109, 29);
		panelDetail.add(btnAjouterSolde);
		
		btnEnregistrerFiche = new JButton("Enregistrer fiche");
		btnEnregistrerFiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEnregistrerFiche();
			}
		});
		btnEnregistrerFiche.setBounds(205, 80, 141, 29);
		panelDetail.add(btnEnregistrerFiche);
		
		table_1 = new JTable();
		table_1.setBounds(16, 177, 446, 385);
		panelDetail.add(table_1);
		
		dateChooserDdn = new JDateChooser();
		dateChooserDdn.setBounds(77, 80, 119, 26);
		panelDetail.add(dateChooserDdn);
		
		table = new JTable();
		table.setBounds(505, 357, 1, 1);
		panelPersonnes.add(table);
		
		// Et on affiche les données correspondantes
		rdbtnClient();
		
		
		// Onglet Produit
		JPanel panelProduits = new JPanel();
		tabbedPane.addTab("Produits", null, panelProduits, null);
		panelProduits.setLayout(null);
		
		scrollPaneProduit = new JScrollPane();
		scrollPaneProduit.setBounds(6, 6, 500, 688);
		panelProduits.add(scrollPaneProduit);
		
		
		rdbtnAlim = new JRadioButton("Aliments");
		rdbtnAlim.setBounds(512, 9, 111, 23);
		panelProduits.add(rdbtnAlim);
		rdbtnAlim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAlim();
			}
		});
		
		rdbtnMeuble = new JRadioButton("Meubles");
		rdbtnMeuble.setBounds(512, 35, 111, 23);
		panelProduits.add(rdbtnMeuble);
		rdbtnMeuble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMeuble();
			}
		});
		
		JLabel lblAjoutProd = new JLabel("Ajouter un produit");
		lblAjoutProd.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAjoutProd.setBounds(516, 85, 171, 23);
		panelProduits.add(lblAjoutProd);
		
		JLabel lblNomDir = new JLabel("Directeur :");
		lblNomDir.setBounds(516, 119, 87, 14);
		panelProduits.add(lblNomDir);
		
		listeDir = Repertoire.getListeDir();
		comboBoxListeDir = new JComboBox(listeDir);
		comboBoxListeDir.setBounds(613, 115, 121, 22);
		panelProduits.add(comboBoxListeDir);
		
		
		// Composantes du formulaire pour ajouter un produit
		rdbtnAddAlim = new JRadioButton("Aliment");
		rdbtnAddAlim.setBounds(512, 140, 111, 23);
		panelProduits.add(rdbtnAddAlim);
		rdbtnAddAlim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAddAlim();
			}
		});
		
		rdbtnAddMeuble = new JRadioButton("Meuble");
		rdbtnAddMeuble.setBounds(724, 140, 111, 23);
		panelProduits.add(rdbtnAddMeuble);
		rdbtnAddMeuble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAddMeuble();
			}
		});
		
		lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(516, 170, 49, 14);
		panelProduits.add(lblNewLabel);
		
		lblCouleur = new JLabel("Couleur :");
		lblCouleur.setBounds(516, 197, 65, 14);
		panelProduits.add(lblCouleur);
		
		lblPoids = new JLabel("Poids :");
		lblPoids.setBounds(516, 227, 49, 14);
		panelProduits.add(lblPoids);
		
		txtNom = new JTextField();
		txtNom.setEditable(false);
		txtNom.setBounds(556, 170, 96, 20);
		panelProduits.add(txtNom);
		txtNom.setColumns(10);
		
		txtPoids = new JTextField();
		txtPoids.setEditable(false);
		txtPoids.setBounds(568, 224, 96, 20);
		panelProduits.add(txtPoids);
		txtPoids.setColumns(10);
		
		txtCouleur = new JTextField();
		txtCouleur.setEditable(false);
		txtCouleur.setBounds(591, 194, 96, 20);
		panelProduits.add(txtCouleur);
		txtCouleur.setColumns(10);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(724, 170, 49, 14);
		panelProduits.add(lblType);
		
		JLabel lblHauteur = new JLabel("Hauteur Maximale :");
		lblHauteur.setBounds(724, 197, 134, 14);
		panelProduits.add(lblHauteur);
		
		JLabel lblPrix = new JLabel("Prix :");
		lblPrix.setBounds(724, 227, 49, 14);
		panelProduits.add(lblPrix);
		
		txtType = new JTextField();
		txtType.setEditable(false);
		txtType.setBounds(772, 167, 96, 20);
		panelProduits.add(txtType);
		txtType.setColumns(10);
		
		txtHauteur = new JTextField();
		txtHauteur.setEditable(false);
		txtHauteur.setBounds(842, 194, 96, 20);
		panelProduits.add(txtHauteur);
		txtHauteur.setColumns(10);
		
		txtPrix = new JTextField();
		txtPrix.setEditable(false);
		txtPrix.setBounds(762, 224, 96, 20);
		panelProduits.add(txtPrix);
		txtPrix.setColumns(10);
		
		JButton btnAjouterProd = new JButton("Ajouter le produit");
		btnAjouterProd.setBounds(643, 272, 157, 23);
		panelProduits.add(btnAjouterProd);
		btnAjouterProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjouterProd();
			}
		});
		
		
		// Affichage des données 
		rdbtnAlim.setSelected(true);
		rdbtnAlim();

	}
}