package controleur;

//import java.awt.EventQueue;
import vue.FrmMain;

public class Main {
	
	// Attributs
	private FrmMain frmMain;

	public static void main(String[] args) {
		new Main();
	}
	
	/*
	 * Constructeur
	 */
	public Main() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
					frmMain = new FrmMain(this);
					frmMain.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

}
