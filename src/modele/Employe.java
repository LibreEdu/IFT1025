package modele;

public class Employe extends Personne{

	public Employe(String prenom, String nom, String date) {
		super(prenom, nom, date);
	}
	
	public void substractMoney(float solde) {
		this.solde -= solde;
	}
}
