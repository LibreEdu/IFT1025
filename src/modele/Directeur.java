package modele;

public class Directeur extends Employe {

	public Directeur(String prenom, String nom, String date) {
		super(prenom, nom, date);
	}

	public String toString() {
		//String f = feminin ? "rice" : "eur";
		//return super.toString() + " et direct" + f;
		return super.toString();
	}
	
	public void addProduit(Produit p) {
		p.setDirecteur(this);
		Repertoire.add(p);
	}
	
	public void delProduit(Produit p) {
		Repertoire.remove(p);
	}
	
}