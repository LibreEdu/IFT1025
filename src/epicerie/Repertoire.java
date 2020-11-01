package epicerie;

import java.util.ArrayList;

public class Repertoire {
	private static ArrayList<Personne> repertoire = new ArrayList<Personne>();
	
	public void add(Personne personne) {
		repertoire.add(personne);
	}

	public String toString() {
		String result = "";
		for(Personne personne : repertoire) {
			result += personne.toString() + "\n";
		}
		return result;
	}
	
	public static int searchEmail(String debutCourriel) {
		int compteur = 0;
		
		for(Personne personne : repertoire) {
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
		return repertoire.indexOf(personne);
	}
	
	public boolean remove(Personne personne) {
		return repertoire.remove(personne);
	}
	
	public ArrayList<Personne> search(String searchedString) {
		ArrayList<Personne> liste = new ArrayList<Personne>();
		for(Personne personne : repertoire) {
			if (personne.getNom().startsWith(searchedString)) {
				liste.add(personne);
			}			
		}
		return liste;
	}

}
