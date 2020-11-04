package epicerie;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

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
		itemListePersonnes.setIcon(new ImageIcon("/src/epicerie/img/list.png"));
		menuPersonnes.add(itemListePersonnes);
		
		JMenuItem itemAjouterPersonnes = new JMenuItem("Ajouter");
		menuPersonnes.add(itemAjouterPersonnes);
		
		JMenu menuProduits = new JMenu("Produits");
		menuBar.add(menuProduits);

		JMenuItem itemListeProduits = new JMenuItem("Liste");
		menuProduits.add(itemListeProduits);
		
		JMenuItem itemAjouterProduits = new JMenuItem("Ajouter");
		menuProduits.add(itemAjouterProduits);
	}

}
