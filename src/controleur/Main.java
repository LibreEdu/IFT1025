package controleur;

import modele.Repertoire;
import vue.FrmMain;

/**
 * Le controleur
 * 
 * @author Alexandre Pachot
 */
public class Main {
	
	private FrmMain frmMain;

	public static void main(String[] args) {
		new Main();
	}
	
	/**
	 * Demande de la frame principale de la liste des personnes
	 * 
	 * @param Role "Client", "Employe" ou "Directeur"
	 */
	public static void demandeFrmMainListePersonnes(String Role) {
		// https://www.youtube.com/watch?v=0DqadsyS5c8
		// Données de la table
		String[][] data = Repertoire.personneData(Role);
		String[] entete = Repertoire.personneEntete();	
		FrmMain.afficheListe(data, entete);
	}
	
	/**
	 * Constructeur
	 */
	public Main() {
		frmMain = new FrmMain();
		frmMain.frame.setVisible(true);
	}

}
