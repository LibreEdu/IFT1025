package vue;

import controleur.Main;
import modele.Aliment;
import modele.Client;
import modele.Meuble;
import modele.Produit;
import modele.Data;
import modele.Personne;
import modele.Employe;
import modele.Directeur;
import modele.Repertoire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.Panel;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

/**
 * La frame principale
 * 
 * @author Jeanne Laflamme
 * @author Alexandre Pachot
 */
public class FrmMain {

	private static int x = 0;
	private static int y = 0;
	private static int largeur = 1024;
	private static int hauteur = 768;
	
	public JFrame frame;
	private static Directeur directeur;
	private static Directeur[] listeDir;
	private static JButton btnAjouterArgent;
	private static JButton btnAjoutProdPref;
	private static JButton btnDetail;
	private static JButton btnEnregistrerFiche;
	private static JButton btnRechercher;
	private static JButton btnRetirerArgent;
	private static JButton btnRetirerPersonne;
	private static JButton btnRetirerProdPref;
	private static JButton btnRetirerProd;
	private static JComboBox<Object> comboBoxListeDir;
	private static JComboBox<Object> comboBoxRole;
	private static JComboBox<Produit> comboBoxAjoutProdPref;
	private static JComboBox<Produit> comboBoxProdPref;
	private static JLabel lblAjoutProdPref;
	private static JLabel lblCouleur;
	private static JLabel lblNewLabel;
	private static JLabel lblPoids;
	private static JLabel lblProdPref;
	private static JLabel lblSolde;
	private static JPanel panelDetail;
	private static JRadioButton rdbtnAddAlim;
	private static JRadioButton rdbtnAddMeuble;
	private static JRadioButton rdbtnAlim;
	private static JRadioButton rdbtnClient;
	private static JRadioButton rdbtnDirecteur;
	private static JRadioButton rdbtnEmploye;
	private static JRadioButton rdbtnMeuble;
	private static JScrollPane scrollPanePersonne;
	private static JScrollPane scrollPaneProduit;
	private static JTable tablePersonne;
	private static JTable tableProduit;
	private static JTextArea erreurProduit;
	private static JTextField textFieldCourriel;
	private static JTextField textFieldDdn;
	private static JTextField textFieldId;
	private static JTextField textFieldNom;
	private static JTextField textFieldPrenom;
	private static JTextField textFieldRecherche;
	private static JTextField textFieldSolde;
	private static JTextField textFieldSoldeAjout;
	private static JTextField txtCouleur;
	private static JTextField txtHauteur;
	private static JTextField txtNom;
	private static JTextField txtPoids;
	private static JTextField txtPrix;
	private static JTextField txtType;
	private static Panel errorPanelProd;
	 
	/**
	 * Initialisation des données et de la frame principale
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
		btnRetirerPersonne.setEnabled(false);
		panelDetail.setVisible(false);
		btnRetirerArgent.setVisible(false);
	}
	
	/**
	 * Clic sur le bouton radio Employe
	 */
	private static void rdbtnEmploye() {
		demandeListe("Employe");
		
		// On déselectionne les deux autres boutons radios
		rdbtnClient.setSelected(false);
		rdbtnDirecteur.setSelected(false);
		
		// On désactive le bouton détail
		btnDetail.setEnabled(false);
		btnRetirerPersonne.setEnabled(false);
		panelDetail.setVisible(false);
	}
	
	/**
	 * Clic sur le bouton radio Directeur
	 */
	private static void rdbtnDirecteur() {
		demandeListe("Directeur");
		
		// On déselectionne les deux autres boutons radios
		rdbtnClient.setSelected(false);
		rdbtnEmploye.setSelected(false);
		
		// On désactive le bouton détail
		btnDetail.setEnabled(false);
		btnRetirerPersonne.setEnabled(false);
		panelDetail.setVisible(false);
	}
	
	/**
	 * Demande la liste des personnes
	 */
	private static void demandeListe(String Role) {
		Main.demandeFrmMainListePersonnes(Role);
	}
	
	/**
	 * Affiche la liste des personnes selon leur roles
	 * 
	 * @param data Tableau contenant les informations à afficher
	 * @param entete Tableau contenant les entêtes du tableau
	 */
	public static void afficheListe(String[][] data, String[] entete) {
		tablePersonne = new JTable(data, entete);
		
		// Lorsque une personne de la liste est sélectionnée,
		// le bouton « Détail » devient actif
		// https://stackoverflow.com/questions/10128064/jtable-selected-row-click-event
		tablePersonne.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				btnDetail.setEnabled(true);
				btnRetirerPersonne.setEnabled(true);
				
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
	 * Affiche les personnes dont le nom ou le prenom commence par la chaine entré
	 */
	
	private static void btnRechercher() {
		String query = textFieldRecherche.getText();
		ArrayList<Personne> liste = Repertoire.searchByNameStartsWith(query);
		
		String[][] data = Repertoire.personneData(liste);
		String[] entete = Repertoire.personneEntete();
		
		afficheListe(data,entete);
		
		// Déselectionner les boutons
		rdbtnClient.setSelected(false);
		rdbtnEmploye.setSelected(false);
		rdbtnDirecteur.setSelected(false);
	}
	
	/**
	 * Ajout d’une nouvelle personne
	 */
	private static void buttonNouvellePersonne() {
		
		// On affiche le panneau du détail de la personne
		panelDetail.setVisible(true);
		
		// On désactive la liste de personne
		tablePersonne.setEnabled(false);
		
		// On désactive la ligne sélectionnée
		tablePersonne.setRowSelectionAllowed(false);
		
		// On affiche la liste déroulante : Client, Employe, Directeur
		comboBoxRole.setVisible(true);
		
		// On cache le solde
		lblSolde.setVisible(false);
		textFieldSolde.setVisible(false);
		textFieldSoldeAjout.setVisible(false);
		btnAjouterArgent.setVisible(false);
		
		// et la liste des produits
		lblProdPref.setVisible(false);
		comboBoxProdPref.setVisible(false);
		lblAjoutProdPref.setVisible(false);
		comboBoxAjoutProdPref.setVisible(false);
		btnAjoutProdPref.setVisible(false);
		btnRetirerProdPref.setVisible(false);
		
		// Synchronisation entre le choix par défaut de la liste déroulante
		// et le bouton radio actif
		if (rdbtnClient.isSelected()) {
			comboBoxRole.setSelectedIndex(0);
		} else if (rdbtnEmploye.isSelected()) {
			comboBoxRole.setSelectedIndex(1);
		} else {
			comboBoxRole.setSelectedIndex(2);
		}
		
		// On vide le texte. Il est rempli si l’on vient de Détail
		textFieldId.setText("");
		textFieldPrenom.setText("");
		textFieldNom.setText("");
		textFieldCourriel.setText("");
		textFieldDdn.setText("");
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

			textFieldPrenom.setText(personne.getPrenom());
			textFieldNom.setText(personne.getNom());
			textFieldCourriel.setText(personne.getCourriel());
			
			LocalDate ddn = personne.getDdn();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			textFieldDdn.setText(ddn.format(dtf));
			
			DecimalFormat sf = new DecimalFormat("#,##0.00");
			textFieldSolde.setText(sf.format(personne.getSolde()));
			
			// Si ce n’est pas un client, on affiche le bouton Retirer $
			if (rdbtnClient.isSelected() == false) {
				btnRetirerArgent.setVisible(true);
			}
			
			comboBoxProdPref.setModel(new JComboBox<>(personne.arrayProduitsPrefs()).getModel());
			comboBoxAjoutProdPref.setModel(new JComboBox<>(Repertoire.getProduits()).getModel());
		}
		// On affiche le panneau du détail de la personne
		panelDetail.setVisible(true);
		
		// On active la liste de personne
		tablePersonne.setEnabled(true);
		
		// On active la ligne sélectionnée
		tablePersonne.setRowSelectionAllowed(true);
		
		// On cache la liste déroulante : Client, Employe, Directeur
		comboBoxRole.setVisible(false);
		
		// On affiche le solde
		lblSolde.setVisible(true);
		textFieldSolde.setVisible(true);
		textFieldSoldeAjout.setVisible(true);
		btnAjouterArgent.setVisible(true);
		
		// et la liste des produits
		lblProdPref.setVisible(true);
		comboBoxProdPref.setVisible(true);
		lblAjoutProdPref.setVisible(true);
		comboBoxAjoutProdPref.setVisible(true);
		btnAjoutProdPref.setVisible(true);
		btnRetirerProdPref.setVisible(true);
		
		textFieldSoldeAjout.setText("");
	}
	
	/**
	 * Supprime une personne
	 */
	private static void btnRetirerPersonne() {
		
		// On récupère le # de la ligne sélectionnée
		int row = tablePersonne.getSelectedRow();
		
		// On récupère le Id de la personne
		// Si aucune ligne est sélectionnée, row == -1
		if (row >= 0) {
			int id = Integer.parseInt((String) tablePersonne.getValueAt(row, 0));
			Personne personne = Repertoire.searchById(id);
			String msg = "Êtes-vous sûr de vouloir supprimer la personne #";
			msg += personne.getId() + " (";
			msg += personne.getPrenom() + " ";
			msg += personne.getNom() + ")";
			
			int input = JOptionPane.showConfirmDialog(null, msg);
			
			if (input == 0) {
				Repertoire.remove(personne);
				
				// On rafraichi l’affichage de la liste
				if (rdbtnClient.isSelected()) {
					demandeListe("Client");
				} else if (rdbtnEmploye.isSelected()) {
					demandeListe("Employe");
				} else {
					demandeListe("Directeur");
				}
				
				// Désactivation des boutons
				btnDetail.setEnabled(false);
				btnRetirerPersonne.setEnabled(false);
			}
		}
		
	}
	
	/**
	 * Vérification du champs date
	 */
	private static void textFieldDdnfocusLost() {
		// http://tutorials.jenkov.com/java-regex/pattern.html
		// https://www.regextester.com/96683
		String ddn = textFieldDdn.getText();
		String ddnPattern = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
		boolean matches = Pattern.matches(ddnPattern, ddn);
		if (matches == false) {
			JOptionPane.showMessageDialog(null, "La date doit être au format aaaa-mm-jj");
		}
	}
	
	/**
	 * Enregistre les données de la personne, pas l’ajout de solde
	 */
	private static void btnEnregistrerFiche() {
		if (textFieldPrenom.getText().equals("")
				|| textFieldNom.getText().equals("")
				|| textFieldDdn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Les trois champs sont obligatoires");
			return;
		}
		String id = textFieldId.getText();

		if (id.isEmpty()) {	// C’est un ajout
			String nom = textFieldNom.getText();
			String prenom = textFieldPrenom.getText();
			String ddn = textFieldDdn.getText();
			String role = (String) comboBoxRole.getSelectedItem();
			switch(role) {
				case "Client":
					new Client(prenom, nom, ddn);
					// On rafraichi l’affichage de la liste
					demandeListe("Client");
					// Sélection du radio bouton
					rdbtnClient.setSelected(true);
					rdbtnEmploye.setSelected(false);
					rdbtnDirecteur.setSelected(false);
					break;
				case "Employe":
					new Employe(prenom, nom, ddn);
					// On rafraichi l’affichage de la liste
					demandeListe("Employe");
					// Sélection du radio bouton
					rdbtnClient.setSelected(false);
					rdbtnEmploye.setSelected(true);
					rdbtnDirecteur.setSelected(false);
					break;
				case "Directeur":
					new Directeur(prenom, nom, ddn);
					// On rafraichi l’affichage de la liste
					demandeListe("Directeur");
					// Sélection du radio bouton
					rdbtnClient.setSelected(false);
					rdbtnEmploye.setSelected(false);
					rdbtnDirecteur.setSelected(true);
					// On l'ajoute dans la liste des directeurs qui peuvent gérer les produits
					comboBoxListeDir.setModel(new JComboBox<Object>(Repertoire.getListeDir()).getModel());
					break;
			}
			
			// On veut sélectionner sur la liste de gauche, la personne qu’on
			// vient de rajouter. Pour cela on récupère compteur qui correspond
			// à l’ID de la personne qu’on vient de rajouter, puis on chercher
			// quelle ligne de tablePersonne contient cet id
			int compteur = Personne.getCompteur();
			int row = 0;
			int id2;
			for (int i = 0; i < tablePersonne.getRowCount(); i++) {
				id2 = Integer.parseInt((String) tablePersonne.getValueAt(row, 0));
				if (id2 == compteur) {
					break;
				} else {
					row++;
				}
			}
			// On selectionne la ligne du tableau
			tablePersonne.setRowSelectionInterval(row, row);
			
		} else {		// C’est une modif
			Personne personne = Repertoire.getPersonne(Integer.parseInt(id));
			String nom = textFieldNom.getText();
			String prenom = textFieldPrenom.getText();
			personne.setNom(nom);
			personne.setPrenom(prenom);
			personne.setCourriel(Personne.genererCourriel(prenom, nom));
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate ddn = LocalDate.parse(textFieldDdn.getText(), dtf);
			personne.setDdn(ddn);
			
			// On rafraichi l’affichage de la liste
			if (rdbtnClient.isSelected()) {
				demandeListe("Client");
			} else if (rdbtnEmploye.isSelected()) {
				demandeListe("Employe");
			} else {
				demandeListe("Directeur");
			}
		}
	}
	
	/**
	 * Mise à jour du courriel lorsqu’on change le nom ou le prénom
	 */
	private static void textFieldNomPrenomFocusLost() {
		String prenom = textFieldPrenom.getText();
		String nom = textFieldNom.getText();
		textFieldCourriel.setText(Personne.genererCourriel(prenom, nom));
	}
	
	/**
	 * Ajoute un montant au solde
	 */
	private static void btnAjouterArgent() {
		if (textFieldSoldeAjout.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Champs vide");
			return;
		}
		float montant = 0;
		try {
			montant = Float.parseFloat(textFieldSoldeAjout.getText());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Donnée invalide");
			return;
		}
		
		if (montant > 0) {
			Personne personne = Repertoire.getPersonne(Integer.parseInt(textFieldId.getText()));
			personne.addMoney(montant);
			DecimalFormat sf = new DecimalFormat("#,##0.00");
			textFieldSolde.setText(sf.format(personne.getSolde()));
			textFieldSoldeAjout.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Montant incorrect");
		}
	}
	
	/**
	 * Retire un montant au solde
	 */
	private static void btnRetirerArgent() {
		if (textFieldSoldeAjout.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Champs vide");
			return;
		}
		float montant = 0;
		try {
			montant = Float.parseFloat(textFieldSoldeAjout.getText());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Donnée invalide");
			return;
		}
		
		if (montant > 0) {
			Employe employe = (Employe) Repertoire.getPersonne(Integer.parseInt(textFieldId.getText()));
			employe.substractMoney(montant);
			DecimalFormat sf = new DecimalFormat("#,##0.00");
			textFieldSolde.setText(sf.format(employe.getSolde()));
			textFieldSoldeAjout.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Montant incorrect");
		}
	}
	
	/**
	 * Clic sur le bouton radio Aliments
	 */
	private static void rdbtnAlim() {
		afficheListeProduits("Aliment");
		
		// On déselectionne l'autre bouton radio
		rdbtnMeuble.setSelected(false);
		
		// On désactive le boutton retirer produit
		btnRetirerProd.setEnabled(false);
	}
	
	/**
	 * Clic sur le bouton radio Meubles
	 */
	private static void rdbtnMeuble() {
		afficheListeProduits("Meuble");
		
		// On déselectionne l'autre bouton radio
		rdbtnAlim.setSelected(false);
		
		// On désactive le boutton retirer produit
		btnRetirerProd.setEnabled(false);
	}
	
	/**
	 * Retire un produit de la liste
	 */
	private static void btnRetirerProd() {
		
		// On récupère le # de la ligne sélectionnée
		int row = tableProduit.getSelectedRow();
		
		// Récupérer le directeur
		directeur = (Directeur) comboBoxListeDir.getSelectedItem();
		
		
		// On récupère le Id du produit
		// Si aucune ligne est sélectionnée, row == -1
		if (row >= 0) {
			int id = Integer.parseInt((String) tableProduit.getValueAt(row, 0));
			Produit produit = Repertoire.searchByIdProd(id);
			
			// On supprime le produit
			directeur.delProduit(produit);
			
			// On affiche la liste de produit mise à jour
			if(produit instanceof Aliment) {
				rdbtnAlim();
			} else if(produit instanceof Meuble) {
				rdbtnMeuble();
			}
			
			// On met à jour le comboBox de produits qui peuvent être mis dans les préférés
			comboBoxAjoutProdPref.setModel(new JComboBox<>(Repertoire.getProduits()).getModel());
			
			// On retire le produit des produits préférés de chaque personne
			for(Personne personne : Repertoire.getListePersonne()) {
				personne.delProduitPref(produit);
				
			}
			// On met à jour la liste de produits préférés de la personne sélectionné
			int rowPers = tablePersonne.getSelectedRow();
			if (rowPers >= 0) {
				int idPers = Integer.parseInt((String) tablePersonne.getValueAt(row, 0));
				Personne personne = Repertoire.searchById(idPers);
				comboBoxProdPref.setModel(new JComboBox<>(personne.arrayProduitsPrefs()).getModel());
			}
		}
		
		
	}
	
	/**
	 * Affiche la liste de produits selon leur catégorie
	 * 
	 * @param sorte de produit
	 */
	public static void afficheListeProduits(String sorte) {
		String[][] data = Repertoire.produitData(sorte);
		String[] entete = Repertoire.produitEntete(sorte);
		tableProduit = new JTable(data, entete);
		
		// Activer le boutton retirer un produit
		tableProduit.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				btnRetirerProd.setEnabled(true);
			}
			
		});
		
		// Largeur des colonnes : colonne Identifiant
		TableColumn col = tableProduit.getColumnModel().getColumn(0);
        col.setPreferredWidth(0);
		
        scrollPaneProduit.setViewportView(tableProduit);
	}
	
	/**
	 * Clic sur le bouton radio Aliment dans la section ajouter un produit
	 */
	private static void rdbtnAddAlim() {
		
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
	private static void rdbtnAddMeuble() {
		
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
	 * Clic sur le bouton ajouter (pour ajouter un produit préféré)
	 */
	private static void btnAjoutProdPref() {
		//On récupère le produit sélectionné
		Produit produit = (Produit) comboBoxAjoutProdPref.getSelectedItem();
		
		// On récupère le # de la ligne sélectionnée dans le tableau de personnes
		int row = tablePersonne.getSelectedRow();
		
		// On récupère le Id de la personne
		// Si aucune ligne est sélectionnée, row == -1
		if (row >= 0) {
			int id = Integer.parseInt((String) tablePersonne.getValueAt(row, 0));
			Personne personne = Repertoire.searchById(id);
			
			// On ajoute le produit
			if(!(personne.getProduitsPrefs().contains(produit))) {
				personne.addProduitPref(produit);
			}
			
			// On met à jour la liste
			comboBoxProdPref.setModel(new JComboBox<>(personne.arrayProduitsPrefs()).getModel());
		}
	}
	
	/**
	 * Clic sur le bouton retirer (pour retirer un produit préféré)
	 */
	private static void btnRetirerProdPref() {
		//On récupère le produit sélectionné
		Produit produit = (Produit) comboBoxProdPref.getSelectedItem();
		
		// On récupère le # de la ligne sélectionnée dans le tableau de personnes
		int row = tablePersonne.getSelectedRow();
		
		// On récupère le Id de la personne
		// Si aucune ligne est sélectionnée, row == -1
		if (row >= 0) {
			int id = Integer.parseInt((String) tablePersonne.getValueAt(row, 0));
			Personne personne = Repertoire.searchById(id);
			
			// On retire le produit
			personne.delProduitPref(produit);
			
			// On met à jour la liste
			comboBoxProdPref.setModel(new JComboBox<>(personne.arrayProduitsPrefs()).getModel());
		}
	}
	
	
	/**
	 * Clic sur le bouton ajouter produit
	 */
	private static void btnAjouterProd() {
		
		// Récupérer le directeur
		directeur = (Directeur) comboBoxListeDir.getSelectedItem();
		
		if(rdbtnAddAlim.isSelected()) {
			
			// vérifier que toutes les informations ont été entrés
			if(txtNom.getText().isEmpty() || txtCouleur.getText().isEmpty() 
					|| txtPoids.getText().isEmpty()) {
				erreurProduit.setText("Erreur: vous n'avez pas entré tous " +
					"les champs requis");
				errorPanelProd.setVisible(true);
			} else {
				// Récupérer les informations
				String nom = txtNom.getText();
				String couleur = txtCouleur.getText();
				double poids;
				try {
					poids = Double.parseDouble(txtPoids.getText());
				} catch(NumberFormatException e) {
					erreurProduit.setText("Erreur: Le poids entré doit être un nombre");
					errorPanelProd.setVisible(true);
					return;
				}
				
				//Ajouter l'aliment
				Produit aliment = new Aliment(nom,couleur,poids);
				directeur.addProduit(aliment);
				
				// Vider les champs de textes
				txtNom.setText("");
				txtCouleur.setText("");
				txtPoids.setText("");
				
				// Afficher  la liste des aliments mise à jour
				rdbtnAlim();
				
				errorPanelProd.setVisible(false);
			}
			
		} else if(rdbtnAddMeuble.isSelected()) {
			// vérifier que toutes les informations ont été entrés
			if(txtType.getText().isEmpty() || txtHauteur.getText().isEmpty() 
					|| txtPrix.getText().isEmpty()) {
				erreurProduit.setText("Erreur: vous n'avez pas entré tous " +
					"les champs requis");
				errorPanelProd.setVisible(true);
			} else {
				// Récupérer les informations
				String type = txtType.getText();
				double hauteur;
				try {
					hauteur = Double.parseDouble(txtHauteur.getText());
				} catch(NumberFormatException e) {
					erreurProduit.setText("Erreur: La hauteur maximale entré doit être un nombre");
					errorPanelProd.setVisible(true);
					return;
				}
				double prix;
				try {
					prix = Double.parseDouble(txtPrix.getText());
				} catch(NumberFormatException e) {
					erreurProduit.setText("Erreur: Le prix entré doit être un nombre");
					errorPanelProd.setVisible(true);
					return;
				}
				
				// Ajouter le meuble
				Produit meuble = new Meuble(type,hauteur,prix);
				directeur.addProduit(meuble);
				
				// Vider les champs de textes
				txtType.setText("");
				txtPrix.setText("");
				txtHauteur.setText("");
				
				// Afficher la liste des meubles mise à jour
				rdbtnMeuble();
				errorPanelProd.setVisible(false);
			}
		}
		// Mettre à jour la liste des produits qui peuvent être ajouter par les clients
		comboBoxAjoutProdPref.setModel(new JComboBox<>(Repertoire.getProduits()).getModel());
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @param controle Le controleur
	 */
	private void initialize() {
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
		textFieldRecherche.setToolTipText("Entrez le début d'un nom");
		textFieldRecherche.setBounds(739, 2, 65, 26);
		panelPersonnes.add(textFieldRecherche);
		
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(804, 2, 117, 29);
		panelPersonnes.add(btnRechercher);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRechercher();
			}
		});
		
		
		btnRetirerPersonne = new JButton("Supprimer");
		btnRetirerPersonne.setEnabled(false);
		btnRetirerPersonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRetirerPersonne();
			}
		});
		btnRetirerPersonne.setBounds(804, 55, 164, 29);
		panelPersonnes.add(btnRetirerPersonne);

		
		JButton buttonNouvellePersonne = new JButton("Nouvelle personne");
		buttonNouvellePersonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonNouvellePersonne();
			}
		});
		buttonNouvellePersonne.setBounds(804, 82, 164, 29);
		panelPersonnes.add(buttonNouvellePersonne);
		
		btnDetail = new JButton("Détails");
		btnDetail.setEnabled(false);
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetail();
			}
		});
		btnDetail.setBounds(804, 28, 117, 29);
		panelPersonnes.add(btnDetail);
		
		panelDetail = new JPanel();
		panelDetail.setBounds(518, 112, 479, 598);
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
		comboBoxRole = new JComboBox<Object>(Roles);
		comboBoxRole.setBounds(364, 2, 109, 27);
		panelDetail.add(comboBoxRole);
		comboBoxRole.setVisible(false);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(6, 34, 61, 16);
		panelDetail.add(lblPrenom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				textFieldNomPrenomFocusLost();
			}
		});
		textFieldPrenom.setBounds(77, 29, 156, 26);
		panelDetail.add(textFieldPrenom);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(247, 34, 37, 16);
		panelDetail.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				textFieldNomPrenomFocusLost();
			}
		});
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
		
		textFieldDdn = new JTextField();
		textFieldDdn.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				textFieldDdnfocusLost();
			}
		});
		textFieldDdn.setBounds(77, 80, 98, 26);
		panelDetail.add(textFieldDdn);
		
		lblSolde = new JLabel("Solde");
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
		
		
		btnAjouterArgent = new JButton("← Ajouter $");
		btnAjouterArgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjouterArgent();
			}
		});
		btnAjouterArgent.setBounds(205, 107, 109, 29);
		panelDetail.add(btnAjouterArgent);
		
		btnRetirerArgent = new JButton("← Retirer $");
		btnRetirerArgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRetirerArgent();
			}
		});
		btnRetirerArgent.setBounds(313, 107, 109, 29);
		panelDetail.add(btnRetirerArgent);
		btnRetirerArgent.setVisible(false);
		
		btnEnregistrerFiche = new JButton("Enregistrer fiche");
		btnEnregistrerFiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEnregistrerFiche();
			}
		});
		btnEnregistrerFiche.setBounds(205, 80, 141, 29);
		panelDetail.add(btnEnregistrerFiche);
		
		lblProdPref = new JLabel("Produits Préférés :");
		lblProdPref.setBounds(6, 181, 143, 14);
		panelDetail.add(lblProdPref);
		
		lblAjoutProdPref = new JLabel("Ajouter un produit :");
		lblAjoutProdPref.setBounds(6, 216, 143, 14);
		panelDetail.add(lblAjoutProdPref);
		
		comboBoxProdPref = new JComboBox<Produit>();
		comboBoxProdPref.setBounds(172, 177, 112, 22);
		panelDetail.add(comboBoxProdPref);
		
		comboBoxAjoutProdPref = new JComboBox<Produit>();
		comboBoxAjoutProdPref.setBounds(171, 212, 113, 22);
		panelDetail.add(comboBoxAjoutProdPref);
		
		btnAjoutProdPref = new JButton("Ajouter");
		btnAjoutProdPref.setBounds(313, 212, 89, 23);
		panelDetail.add(btnAjoutProdPref);
		btnAjoutProdPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjoutProdPref();
			}
		});
		
		btnRetirerProdPref = new JButton("Retirer");
		btnRetirerProdPref.setBounds(313, 177, 89, 23);
		panelDetail.add(btnRetirerProdPref);
		btnRetirerProdPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRetirerProdPref();
			}
		});
		
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
		rdbtnAlim.setBounds(512, 34, 111, 23);
		panelProduits.add(rdbtnAlim);
		rdbtnAlim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAlim();
			}
		});
		
		rdbtnMeuble = new JRadioButton("Meubles");
		rdbtnMeuble.setBounds(512, 55, 111, 23);
		panelProduits.add(rdbtnMeuble);
		rdbtnMeuble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMeuble();
			}
		});
		
		btnRetirerProd = new JButton("Retirer le produit");
		btnRetirerProd.setEnabled(false);
		btnRetirerProd.setBounds(516, 85, 160, 23);
		panelProduits.add(btnRetirerProd);
		btnRetirerProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRetirerProd();
			}
		});
		
		JLabel lblAjoutProd = new JLabel("Ajouter un produit");
		lblAjoutProd.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAjoutProd.setBounds(516, 110, 171, 23);
		panelProduits.add(lblAjoutProd);
		
		JLabel lblNomDir = new JLabel("Directeur :");
		lblNomDir.setBounds(516, 13, 87, 14);
		panelProduits.add(lblNomDir);
		
		listeDir = Repertoire.getListeDir();
		comboBoxListeDir = new JComboBox<Object>(listeDir);
		comboBoxListeDir.setBounds(591, 9, 121, 22);
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
		rdbtnAddMeuble.setBounds(720, 140, 111, 23);
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
		txtNom.setBounds(591, 167, 96, 20);
		panelProduits.add(txtNom);
		
		txtPoids = new JTextField();
		txtPoids.setEditable(false);
		txtPoids.setBounds(591, 224, 96, 20);
		panelProduits.add(txtPoids);
		
		txtCouleur = new JTextField();
		txtCouleur.setEditable(false);
		txtCouleur.setBounds(591, 194, 96, 20);
		panelProduits.add(txtCouleur);
		
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
		txtType.setBounds(852, 167, 96, 20);
		panelProduits.add(txtType);
		
		txtHauteur = new JTextField();
		txtHauteur.setEditable(false);
		txtHauteur.setBounds(852, 194, 96, 20);
		panelProduits.add(txtHauteur);
		
		txtPrix = new JTextField();
		txtPrix.setEditable(false);
		txtPrix.setBounds(852, 224, 96, 20);
		panelProduits.add(txtPrix);
		
		JButton btnAjouterProd = new JButton("Ajouter le produit");
		btnAjouterProd.setBounds(643, 272, 157, 23);
		panelProduits.add(btnAjouterProd);
		btnAjouterProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjouterProd();
			}
		});
		
		// Text Area qui affiche un message d'erreur si les attributs
		// d'un produit sont mal entré
		errorPanelProd = new Panel();
		errorPanelProd.setBounds(543, 314, 408, 73);
		panelProduits.add(errorPanelProd);
		errorPanelProd.setVisible(false);
		
		erreurProduit = new JTextArea();
		erreurProduit.setForeground(Color.RED);
		erreurProduit.setRows(2);
		errorPanelProd.add(erreurProduit);
			
		// Affichage des données 
		rdbtnAlim.setSelected(true);
		rdbtnAlim();
	}
}