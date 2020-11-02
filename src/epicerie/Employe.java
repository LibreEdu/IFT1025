package epicerie;

public class Employe extends Personne{

	public Employe(String prenom, String nom, String date, boolean feminin) {
		super(prenom, nom, date, feminin);
	}

	public String toString() {
		String f = feminin ? "e" : "";
		return super.toString() + ", est un" + f + " employé" + f;
	}
	
	public void augmenterSolde(Client client, double montant) {
		client.addMoney((float)montant);
	}
}
