package epicerie;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class FramePersonne extends JFrame {
	
	private static JButton ajouter;
	private static JTextField dateField, firstNameField, lastNameField;
	private static JLabel dateLabel, firstNameLabel, lastNameLabel;
	private static JRadioButton clientRadio, employeRadio, directeurRadio;
	private static JRadioButton femmeRadio, hommeRadio;
	private static JTextArea personnesEnr;
	
	private static String listePersonne;
	
	public FramePersonne() {
		
		// Initialisation des variables
		
		listePersonne = Repertoire.personneToString();
		
		JPanel pnl = new JPanel();
		SpringLayout lyt = new SpringLayout();
		pnl.setSize(200, 200);
		pnl.setLayout(lyt);
		
		// Bouttons radios pour choisir le rôle
		
		clientRadio = new JRadioButton("Client");
		employeRadio = new JRadioButton("Employé");
		directeurRadio = new JRadioButton("Directeur");
		
		pnl.add(clientRadio);
		pnl.add(employeRadio);
		pnl.add(directeurRadio); 
		
		// Regrouper les bouttons pour pouvoir en cocher un seul
		
		ButtonGroup role =new ButtonGroup();    
		role.add(clientRadio);
		role.add(employeRadio);
		role.add(directeurRadio); 
		
		// Champs de textes pour entrer le pr�nom, le nom et la date de naissance
		
		firstNameField = new JTextField(25);
		lastNameField = new JTextField(25);
		dateField = new JTextField(10);
		
		firstNameLabel = new JLabel("Prénom: ");
		lastNameLabel = new JLabel("Nom: ");
		dateLabel = new JLabel("Date de naissance (aaaa-mm-jj): ");
		
		pnl.add(firstNameField);
		pnl.add(firstNameLabel);
		pnl.add(lastNameField);
		pnl.add(lastNameLabel);
		pnl.add(dateField);
		pnl.add(dateLabel);
		
		// Bouttons radios pour choisir le r�le
		
		femmeRadio = new JRadioButton("Femme");
		hommeRadio = new JRadioButton("Homme");
		
		pnl.add(femmeRadio);
		pnl.add(hommeRadio);
		
		// Regrouper les bouttons pour pouvoir en cocher un seul
		
		ButtonGroup sexe =new ButtonGroup();    
		sexe.add(femmeRadio);
		sexe.add(hommeRadio);
				
		// Boutton ajouter et liste des produits disponibles
		
		ajouter = new JButton("Ajouter la personne");
		personnesEnr = new JTextArea(listePersonne,10,50);
		
		pnl.add(ajouter);
		pnl.add(personnesEnr);
		
		
		// Placer les composantes dans le panel
		
		lyt.putConstraint(SpringLayout.WEST, firstNameLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, firstNameField , 5, SpringLayout.EAST, firstNameLabel);
		
		lyt.putConstraint(SpringLayout.WEST, lastNameLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, lastNameField , 5, SpringLayout.EAST, lastNameLabel);
		
		lyt.putConstraint(SpringLayout.WEST, dateLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, dateField , 5, SpringLayout.EAST, dateLabel);
		
		lyt.putConstraint(SpringLayout.WEST, clientRadio , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, employeRadio , 5, SpringLayout.EAST, clientRadio);
		lyt.putConstraint(SpringLayout.WEST, directeurRadio , 5, SpringLayout.EAST, employeRadio);
		
		lyt.putConstraint(SpringLayout.WEST, femmeRadio , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, hommeRadio , 5, SpringLayout.EAST, femmeRadio);
		
		lyt.putConstraint(SpringLayout.WEST, ajouter , 5, SpringLayout.WEST, pnl);
		
		lyt.putConstraint(SpringLayout.WEST, personnesEnr , 5, SpringLayout.WEST, pnl);
		
		lyt.putConstraint(SpringLayout.NORTH, clientRadio, 5, SpringLayout.NORTH, pnl);
		lyt.putConstraint(SpringLayout.NORTH, employeRadio, 5, SpringLayout.NORTH, pnl);
		lyt.putConstraint(SpringLayout.NORTH, directeurRadio, 5, SpringLayout.NORTH, pnl);
		
		lyt.putConstraint(SpringLayout.NORTH, firstNameLabel, 5, SpringLayout.SOUTH, clientRadio);
		lyt.putConstraint(SpringLayout.NORTH, firstNameField, 5, SpringLayout.SOUTH, clientRadio);
		
		lyt.putConstraint(SpringLayout.NORTH, lastNameLabel, 5, SpringLayout.SOUTH, firstNameField);
		lyt.putConstraint(SpringLayout.NORTH, lastNameField, 5, SpringLayout.SOUTH, firstNameField);
		
		lyt.putConstraint(SpringLayout.NORTH, dateLabel, 5, SpringLayout.SOUTH, lastNameField);
		lyt.putConstraint(SpringLayout.NORTH, dateField, 5, SpringLayout.SOUTH, lastNameField);
		
		lyt.putConstraint(SpringLayout.NORTH, femmeRadio, 5, SpringLayout.SOUTH, dateField);
		lyt.putConstraint(SpringLayout.NORTH, hommeRadio, 5, SpringLayout.SOUTH, dateField);
		
		lyt.putConstraint(SpringLayout.NORTH, ajouter, 10, SpringLayout.SOUTH, femmeRadio);
		
		lyt.putConstraint(SpringLayout.NORTH, personnesEnr, 10, SpringLayout.SOUTH, ajouter);
	
		add(pnl);
		
		// Gestion des listeners
		
		ajouter.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// R�cup�rer les informations
						String firstName = firstNameField.getText();
						String lastName = lastNameField.getText();
						String date = dateField.getText(); // Gérer exception!!
						boolean isFeminin = femmeRadio.isSelected(); // Quoi faire si rien n'est selectionn�?
						
						// Ajouter la personne au répertoire
						if(clientRadio.isSelected()) {
							Personne client = new Client(firstName,lastName,date,isFeminin);
							Repertoire.add(client);
						} else if(employeRadio.isSelected()) {
							Personne employe = new Employe(firstName,lastName,date,isFeminin);
							Repertoire.add(employe);
						} else if(directeurRadio.isSelected()) {
							Personne directeur = new Directeur(firstName,lastName,date,isFeminin);
							Repertoire.add(directeur);
						}
						
						// Vider les champs de textes
						firstNameField.setText("");
						lastNameField.setText("");
						dateField.setText("");
							
						// Mettre à jour la liste des personnes
						listePersonne = Repertoire.personneToString();
						personnesEnr.setText(listePersonne);
					}
		});
		
		
	}
	
	
	public static void main(String[] args) {
		FramePersonne frame = new FramePersonne();
		frame.setTitle("Ajouter une personne");
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}

