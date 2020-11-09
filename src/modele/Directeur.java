package modele;

public class Directeur extends Employe {

	public Directeur(String prenom, String nom, String date) {
		super(prenom, nom, date);
	}

	public String toString() {
		return this.getPrenom() + " " + this.getNom();
	}
	
	public void addProduit(Produit p) {
		p.setDirecteur(this);
		Repertoire.add(p);
	}
	
	public void delProduit(Produit p) {
		Repertoire.remove(p);
	}
	
}