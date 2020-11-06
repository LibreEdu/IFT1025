package modele;

public class Employe extends Personne{

	public Employe(String prenom, String nom, String date) {
		super(prenom, nom, date);
	}

	public String toString() {
		//String f = feminin ? "e" : "";
		//return super.toString() + ", est un" + f + " employé" + f;
		return super.toString();
	}
	
	public void augmenterSolde(Client client, double montant) {
		client.addMoney((float)montant);
	}
}
