package epicerie;

public class Aliment extends Produit {
	
	private String couleur;
	private String nom;
	private float poid;
	
	public Aliment(Directeur ajoutPar, String couleur, String nom, float poid) {
		super(ajoutPar);
		this.couleur=couleur;
		this.nom=nom;
		this.poid=poid;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPoid() {
		return poid;
	}

	public void setPoid(float poid) {
		this.poid = poid;
	}

	public boolean equals(Aliment alim) {
		if(this.nom.equals(alim.nom) && this.couleur.equals(alim.couleur) 
				&& this.poid == alim.poid) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return super.toString()+ "Aliment: " + nom + " " + couleur 
				+ "("+ poid + " kg)";
	}

}
