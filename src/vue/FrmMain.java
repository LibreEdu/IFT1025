package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JTabbedPane;

import controleur.Main;
import modele.Data;
import modele.Personne;
import modele.Repertoire;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmMain {

	private int x = 0;
	private int y = 0;
	private int largeur = 1024;
	private int hauteur = 768;
	Data data = new Data();
	
	public JFrame frame;
	private JTable tablePersonne;
	private JTable tableProduit;
	//private Main controle;
	private JScrollPane scrollPanePersonne;
	private JScrollPane scrollPaneProduit;
	private JRadioButton rdbtnClient;
	private JRadioButton rdbtnEmploye;
	private JRadioButton rdbtnDirecteur;
	JRadioButton rdbtnAlim;
	JRadioButton rdbtnMeuble;
	private JButton btnDetail;
	private JPanel panelDetail;
	private JTextField textFieldId;
	private JComboBox comboBoxRole;
	private JTextField textFieldPrenom;
	private JTextField textFieldNom;
	private JTextField textFieldCourriel;
	private JTextField textFieldDdn;
	private JTextField textFieldSolde;
	private JTextField textFieldSoldeAjout;
	private JButton btnAjouterSolde;
	private JButton btnEnregistrerFiche;
	private JTextField textFieldRecherche;
	private JButton btnRechercher;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	


	/**
	 * Create the application.
	 */
	public FrmMain(Main control) {
		initialize(control);
	}
	
	/**
	 * Clic sur le bouton radio Client
	 */
	private void rdbtnClient() {
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
	private void rdbtnEmploye() {
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
	private void rdbtnDirecteur() {
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
	private void demandeListe(String Role) {
		//controle.demandeFrmMainListePersonnes(Role);
		afficheListe(Role);
	}
	
	/**
	 * Affiche la liste des personnes selon leur roles
	 * 
	 * @param Role Nom de la classe à afficher : "Client", "Employe", "Directeur"
	 */
	// public void afficheListe(String[][] data, String[] entete) {
	public void afficheListe(String Role) {
		String[][] data = Repertoire.personneData(Role);
		String[] entete = Repertoire.personneEntete();
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
	 * Bouton Détail
	 */
	private void btnDetail() {
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
			textFieldPrenom.setText("" + personne.getPrenom());
			textFieldNom.setText("" + personne.getNom());
			textFieldCourriel.setText("" + personne.getCourriel());
			
		}
		panelDetail.setVisible(true);
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
	
	public void afficheListeProduits(String sorte) {
		String[][] data = Repertoire.produitData(sorte);
		String[] entete = Repertoire.produitEntete(sorte);
		tableProduit = new JTable(data, entete);
		
        scrollPaneProduit.setViewportView(tableProduit);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @param controle Le controleur
	 */
	private void initialize(Main controle) {
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
		panelPersonnes.add(textFieldRecherche);
		textFieldRecherche.setColumns(10);
		
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
		panelPersonnes.add(panelDetail);
		panelDetail.setVisible(false);
		panelDetail.setLayout(null);
		
		JLabel lblId = new JLabel("Identifiant");
		lblId.setBounds(6, 6, 72, 16);
		panelDetail.add(lblId);
		
		// Champs identifiant
		textFieldId = new JTextField();
		textFieldId.setBounds(77, 1, 44, 26);
		textFieldId.setColumns(10);
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
		textFieldPrenom.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(247, 34, 37, 16);
		panelDetail.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(282, 29, 191, 26);
		panelDetail.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblCourriel = new JLabel("Courriel");
		lblCourriel.setBounds(6, 60, 61, 16);
		panelDetail.add(lblCourriel);
		
		textFieldCourriel = new JTextField();
		textFieldCourriel.setBounds(77, 55, 396, 26);
		panelDetail.add(textFieldCourriel);
		textFieldCourriel.setColumns(10);
		
		JLabel lblDdn = new JLabel("Né.e le");
		lblDdn.setBounds(6, 85, 52, 16);
		panelDetail.add(lblDdn);
		
		// https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component
		textFieldDdn = new JTextField();
		textFieldDdn.setBounds(77, 80, 88, 26);
		panelDetail.add(textFieldDdn);
		textFieldDdn.setColumns(10);
		
		JLabel lblSolde = new JLabel("Solde");
		lblSolde.setBounds(6, 112, 61, 16);
		panelDetail.add(lblSolde);
		
		textFieldSolde = new JTextField();
		textFieldSolde.setBounds(77, 107, 72, 26);
		panelDetail.add(textFieldSolde);
		textFieldSolde.setColumns(10);
		
		textFieldSoldeAjout = new JTextField();
		textFieldSoldeAjout.setBounds(161, 107, 61, 26);
		panelDetail.add(textFieldSoldeAjout);
		textFieldSoldeAjout.setColumns(10);
		
		btnAjouterSolde = new JButton("← Ajouter $");
		btnAjouterSolde.setBounds(223, 107, 109, 29);
		panelDetail.add(btnAjouterSolde);
		
		btnEnregistrerFiche = new JButton("Enregistrer fiche");
		btnEnregistrerFiche.setBounds(332, 107, 141, 29);
		panelDetail.add(btnEnregistrerFiche);
		
		table_1 = new JTable();
		table_1.setBounds(16, 177, 446, 385);
		panelDetail.add(table_1);
		
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
		
		// Tableau qui affiche les produits
		table_2 = new JTable();
		table_2.setBounds(25, 22, 399, 630);
		panelProduits.add(table_2);
		
		// Et on affiche les données correspondantes
		rdbtnAlim.setSelected(true);
		rdbtnAlim();

	}
}