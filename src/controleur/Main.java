package controleur;

import modele.Repertoire;
//import java.awt.EventQueue;
import vue.FrmMain;

public class Main {
	
	// Attributs
	private FrmMain frmMain;

	public static void main(String[] args) {
		new Main();
	}
	
	public void demandeFrmMainListePersonnes(String Role) {
		// https://www.youtube.com/watch?v=0DqadsyS5c8
		// Données de la table
		String[][] data = Repertoire.personneData(Role);
		String[] entete = Repertoire.personneEntete();	
		//frmMain.afficheListe(data, entete);

	}
	
	/*
	 * Constructeur
	 */
	public Main() {
		frmMain = new FrmMain(this);
		frmMain.frame.setVisible(true);
	}

}
