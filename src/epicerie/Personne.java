package epicerie;

public abstract class Personne {
	
	String prenom;
	String nom;

	public Personne(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public String toString() {
		return prenom + " " + nom;
	}

}
