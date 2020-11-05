package epicerie;

public abstract class Produit {
	
	private Directeur directeur;
	private static int compteur;
	private int id;
	
	public Produit(){
		this.id=compteur;
		compteur++;
	}
	
	public void setDirecteur(Directeur dir) {
		this.directeur=dir;
	}

	public Directeur getDirecteur() {
		return directeur;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return id+ " [Ajouter par " + directeur.getPrenom() + " " 
				+ directeur.getNom() + "] ";
	}
	
	abstract public boolean equals(Produit p);
	
}
