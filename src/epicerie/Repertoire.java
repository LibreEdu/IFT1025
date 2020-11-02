package epicerie;

import java.util.ArrayList;

public class Repertoire {
	private static ArrayList<Personne> personnes = new ArrayList<Personne>();
	private static ArrayList<Produit> produits = new ArrayList<Produit>();
	
	public void add(Personne personne) {
		personnes.add(personne);
	}

	public void add(Produit produit) {
		produits.add(produit);
	}

	public String toString() {
		String result = "";
		for(Personne personne : personnes) {
			result += personne.toString() + "\n";
		}
		for(Produit produit : produits) {
			result += produit.toString() + "\n";
		}
		return result;
	}
	
	public static int searchEmail(String debutCourriel) {
		int compteur = 0;
		
		for(Personne personne : personnes) {
			String pCourriel = personne.getCourriel();
			
			// On remplace 0 ou plus chiffres (d*), le @ 
			// et 1 ou plus caractères (.+) par rien
			pCourriel = pCourriel.replaceAll("\\d*@.+","");
			
			if ( debutCourriel.equals(pCourriel)) {
				compteur++;
			}			
		}	
		return compteur;
	}
	
	public int indexOf(Personne personne) {
		return personnes.indexOf(personne);
	}
	
	public int indexOf(Produit produit) {
		return produits.indexOf(produit);
	}
	
	public boolean remove(Personne personne) {
		return personnes.remove(personne);
	}
	
	public boolean remove(Produit produit) {
		return produits.remove(produit);
	}
	
	public ArrayList<Personne> search(String searchedString) {
		ArrayList<Personne> liste = new ArrayList<Personne>();
		for(Personne personne : personnes) {
			if (personne.getNom().startsWith(searchedString)) {
				liste.add(personne);
			}			
		}
		return liste;
	}
}
