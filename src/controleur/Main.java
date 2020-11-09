package controleur;

import modele.Repertoire;
import vue.FrmMain;

public class Main {
	
	// Attributs
	private FrmMain frmMain;

	public static void main(String[] args) {
		new Main();
	}
	
	public static void demandeFrmMainListePersonnes(String Role) {
		// https://www.youtube.com/watch?v=0DqadsyS5c8
		// Données de la table
		String[][] data = Repertoire.personneData(Role);
		String[] entete = Repertoire.personneEntete();	
		FrmMain.afficheListe(data, entete);
	}
	
	/*
	 * Constructeur
	 */
	public Main() {
		frmMain = new FrmMain();
		frmMain.frame.setVisible(true);
	}

}
