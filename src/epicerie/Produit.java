package epicerie;

public abstract class Produit {
	
	private Directeur ajoutPar;
	private static int compteur;
	private int id;
	
	public Produit(Directeur ajoutPar){
		this.id=compteur;
		this.ajoutPar=ajoutPar;
		compteur++;
	}

	public Directeur getAjoutPar() {
		return ajoutPar;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return id+ " [Ajouter par " + ajoutPar + "] ";
	}
	
}
