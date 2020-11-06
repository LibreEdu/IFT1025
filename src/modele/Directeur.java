package modele;

import java.text.ParseException;

public class Directeur extends Employe {

	public Directeur(String prenom, String nom, String date, boolean feminin) {
		super(prenom, nom, date, feminin);
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