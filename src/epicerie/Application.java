package epicerie;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class Application {
	private int x = 0;
	private int y = 0;
	private int largeur = 1280;
	private int hauteur = 1024;

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(x, y, largeur, hauteur);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuPersonnes = new JMenu("Personnes");
		menuBar.add(menuPersonnes);
		
		JMenuItem itemListePersonnes = new JMenuItem("Liste");
		itemListePersonnes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ii");
			}
		});
		// https://www.flaticon.com/free-icon/list_565700
		Image listIcon = new ImageIcon(this.getClass().getResource("/list.png")).getImage();
		itemListePersonnes.setIcon(new ImageIcon(listIcon));
		menuPersonnes.add(itemListePersonnes);
		
		JMenuItem itemAjouterPersonnes = new JMenuItem("Ajouter");
		// https://www.flaticon.com/free-icon/add_992651
		Image addIcon = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		itemAjouterPersonnes.setIcon(new ImageIcon(addIcon));
		menuPersonnes.add(itemAjouterPersonnes);
		
		JMenu menuProduits = new JMenu("Produits");
		menuBar.add(menuProduits);

		JMenuItem itemListeProduits = new JMenuItem("Liste");
		itemListeProduits.setIcon(new ImageIcon(listIcon));
		menuProduits.add(itemListeProduits);
		
		JMenuItem itemAjouterProduits = new JMenuItem("Ajouter");
		itemAjouterProduits.setIcon(new ImageIcon(addIcon));
		menuProduits.add(itemAjouterProduits);
	}

}
