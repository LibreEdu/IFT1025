package vue;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;

import controleur.Main;
import modele.Data;
import modele.Repertoire;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class FrmMain {

	private int x = 0;
	private int y = 0;
	private int largeur = 1024;
	private int hauteur = 768;
	Data data = new Data();
	
	public JFrame frame;
	private JTable tablePersonne;
	private Main controle;
	private JScrollPane scrollPanePersonne;
	private JRadioButton rdbtnClient;
	private JRadioButton rdbtnEmploye;
	private JRadioButton rdbtnDirecteur;


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
		scrollPanePersonne("Client");
		rdbtnEmploye.setSelected(false);
		rdbtnDirecteur.setSelected(false);
	}
	
	/**
	 * Clic sur le bouton radio Client
	 */
	private void rdbtnEmploye() {
		scrollPanePersonne("Employe");
		rdbtnClient.setSelected(false);
		rdbtnDirecteur.setSelected(false);
	}
	
	/**
	 * Clic sur le bouton radio Client
	 */
	private void rdbtnDirecteur() {
		scrollPanePersonne("Directeur");
		rdbtnClient.setSelected(false);
		rdbtnEmploye.setSelected(false);
	}
	
	/**
	 * Remplissage du tableau
	 */
	private void scrollPanePersonne(String Role) {
		String[][] data = Repertoire.personneData(Role);
		String[] entete = Repertoire.personneEntete();
		tablePersonne = new JTable(data, entete);
		scrollPanePersonne.setViewportView(tablePersonne);
	}

	/**
	 * Bouton Détail
	 */
	private void btnDetail() {
		int row = tablePersonne.getSelectedRow();
		int id = Integer.parseInt((String) tablePersonne.getValueAt(row, 0));
		System.out.println(id);	
	}
	/**
	 * Initialize the contents of the frame.
	 * @param controle Le controleur
	 */
	private void initialize(Main controle) {
		this.controle = controle;
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
		scrollPanePersonne.setBounds(6, 6, 650, 688);
		panelPersonnes.add(scrollPanePersonne);
		
		
		rdbtnClient = new JRadioButton("Client.e.s");
		rdbtnClient.setBounds(668, 6, 141, 23);
		panelPersonnes.add(rdbtnClient);
		rdbtnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnClient();
			}
		});
		
		rdbtnEmploye = new JRadioButton("Employé.e.s");
		rdbtnEmploye.setBounds(668, 41, 141, 23);
		panelPersonnes.add(rdbtnEmploye);
		rdbtnEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnEmploye();
			}
		});
		
		rdbtnDirecteur = new JRadioButton("Directeur.trice.s");
		rdbtnDirecteur.setBounds(668, 76, 141, 23);
		panelPersonnes.add(rdbtnDirecteur);
		rdbtnDirecteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDirecteur();
			}
		});
	
		// Par défaut, on sélectionne le radio bouton Client
		rdbtnClient.setSelected(true);
		
		JButton btnDetail = new JButton("Détail");
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetail();
			}
		});
		btnDetail.setBounds(668, 129, 117, 29);
		panelPersonnes.add(btnDetail);
		// Et on affiche les données correspondantes
		rdbtnClient();
		
		
		// Onglet Produit
		JPanel panelProduits = new JPanel();
		tabbedPane.addTab("Produits", null, panelProduits, null);

	}
}