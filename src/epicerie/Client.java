package epicerie;

public class Client extends Personne{

	public Client(String prenom, String nom) {
		super(prenom, nom);
	}

	public String toString() {
		return super.toString() + ", Client";
	}
}
