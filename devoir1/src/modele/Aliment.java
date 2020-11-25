package modele;

/**
 * Les aliments
 * 
 * @author Jeanne Laflamme
 */
public class Aliment extends Produit {
	private String couleur;
	private String nom;
	private double poids;
	
	public Aliment(String nom, String couleur, double poids) {
		this.couleur=couleur;
		this.nom=nom;
		this.poids=poids;
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

	public double getPoids() {
		return poids;
	}

	public void setPoid(double poids) {
		this.poids = poids;
	}

	public boolean equals(Produit alim) {
		if(!(alim instanceof Aliment)) {
			return false;
		} else if(this.nom.equals(((Aliment) alim).nom) && this.poids == ((Aliment) alim).poids
				&& this.couleur.equals(((Aliment) alim).couleur)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return nom + " " + couleur;
	}

}
