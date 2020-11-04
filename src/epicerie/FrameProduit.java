package epicerie;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class FrameProduit extends JFrame {
	
	private static JButton ajouter;
	private static JTextField colorField, nameField, weightField;
	private static JTextField typeField, priceField, heightField;
	private static JLabel colorLabel, nameLabel, weightLabel;
	private static JLabel typeLabel, priceLabel, heightLabel;
	private static JRadioButton alimRadio, meubleRadio;
	private static JTextArea produitsDispos;
	

	
	public FrameProduit() {
		
		JPanel pnl = new JPanel();
		SpringLayout lyt = new SpringLayout();
		
		// Composantes du formulaire pour ajouter un aliment
		
		alimRadio = new JRadioButton("Aliment");
		
		nameField = new JTextField(25);
		colorField = new JTextField(25);
		weightField = new JTextField(25);
		
		nameLabel = new JLabel("Nom: ");
		colorLabel = new JLabel("Couleur: ");
		weightLabel = new JLabel("Poids: ");
		
		pnl.setSize(200, 200);
		pnl.setLayout(lyt);
		
		pnl.add(alimRadio);
		pnl.add(nameField);
		pnl.add(colorField);
		pnl.add(weightField);
		pnl.add(nameLabel);
		pnl.add(colorLabel);
		pnl.add(weightLabel);
		
		// Composantes du formulaire pour ajouter un meuble
		
		meubleRadio = new JRadioButton("Meuble");
		
		typeField = new JTextField(25);
		priceField = new JTextField(25);
		heightField = new JTextField(25);
		
		typeLabel = new JLabel("Type: ");
		priceLabel = new JLabel("Prix: ");
		heightLabel = new JLabel("Hauteur max: ");
		
		pnl.setSize(200, 200);
		pnl.setLayout(lyt);
		
		pnl.add(meubleRadio);
		pnl.add(typeField);
		pnl.add(priceField);
		pnl.add(heightField);
		pnl.add(typeLabel);
		pnl.add(priceLabel);
		pnl.add(heightLabel);
		
		// Boutton ajouter et liste des produits disponibles
		
		ajouter = new JButton("Ajouter les produits");
		produitsDispos = new JTextArea("Produits disponibles: ",10,50);
		
		pnl.add(ajouter);
		pnl.add(produitsDispos);
		
		
		// Placer les composantes dans le panel
		
		lyt.putConstraint(SpringLayout.WEST, nameLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, nameField , 5, SpringLayout.EAST, nameLabel);
		
		lyt.putConstraint(SpringLayout.WEST, colorLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, colorField , 5, SpringLayout.EAST, colorLabel);
		
		lyt.putConstraint(SpringLayout.WEST, weightLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, weightField , 5, SpringLayout.EAST, weightLabel);
		
		lyt.putConstraint(SpringLayout.WEST, typeLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, typeField , 5, SpringLayout.EAST, typeLabel);
		
		lyt.putConstraint(SpringLayout.WEST, priceLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, priceField , 5, SpringLayout.EAST, priceLabel);
		
		lyt.putConstraint(SpringLayout.WEST, heightLabel , 5, SpringLayout.WEST, pnl);
		lyt.putConstraint(SpringLayout.WEST, heightField , 5, SpringLayout.EAST, heightLabel);
		
		lyt.putConstraint(SpringLayout.WEST, ajouter , 5, SpringLayout.WEST, pnl);
		
		lyt.putConstraint(SpringLayout.WEST, produitsDispos , 5, SpringLayout.WEST, pnl);
		
		lyt.putConstraint(SpringLayout.NORTH, alimRadio, 5, SpringLayout.NORTH, pnl);
		
		lyt.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.SOUTH, alimRadio);
		lyt.putConstraint(SpringLayout.NORTH, nameField, 5, SpringLayout.SOUTH, alimRadio);
		
		lyt.putConstraint(SpringLayout.NORTH, colorLabel, 5, SpringLayout.SOUTH, nameField);
		lyt.putConstraint(SpringLayout.NORTH, colorField, 5, SpringLayout.SOUTH, nameField);
		
		lyt.putConstraint(SpringLayout.NORTH, weightLabel, 5, SpringLayout.SOUTH, colorField);
		lyt.putConstraint(SpringLayout.NORTH, weightField, 5, SpringLayout.SOUTH, colorField);
		
		lyt.putConstraint(SpringLayout.NORTH, meubleRadio, 5, SpringLayout.SOUTH, weightField);
		
		lyt.putConstraint(SpringLayout.NORTH, typeLabel, 5, SpringLayout.SOUTH, meubleRadio);
		lyt.putConstraint(SpringLayout.NORTH, typeField, 5, SpringLayout.SOUTH, meubleRadio);
		
		lyt.putConstraint(SpringLayout.NORTH, priceLabel, 5, SpringLayout.SOUTH, typeField);
		lyt.putConstraint(SpringLayout.NORTH, priceField, 5, SpringLayout.SOUTH, typeField);
		
		lyt.putConstraint(SpringLayout.NORTH, heightLabel, 5, SpringLayout.SOUTH, priceField);
		lyt.putConstraint(SpringLayout.NORTH, heightField, 5, SpringLayout.SOUTH, priceField);
		
		lyt.putConstraint(SpringLayout.NORTH, ajouter, 10, SpringLayout.SOUTH, heightField);
		
		lyt.putConstraint(SpringLayout.NORTH, produitsDispos, 10, SpringLayout.SOUTH, ajouter);
		

		// Ajouter le panel dans le frame
		add(pnl);
		
		// Gestion des listeners
		
		/*
		
		ajouter.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
										
					}
		});*/
	}
	
	
	public static void main(String[] args) {
		FrameProduit frame = new FrameProduit();
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}

