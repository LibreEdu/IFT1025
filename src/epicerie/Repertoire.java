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
	
	/*private int find(int id) {
		for(Personne personne : repertoire) {
			if (personne.getId() == id) {
				return 
			}			
		}
	}*/
	
	/* 
	public ArrayList<Personne> search(String prenom, String nom)
			throws ParseException {
		ArrayList<Personne> liste = new ArrayList<Personne>();
		Client searched = new Client(prenom, nom, "1900-01-01", false);
		for(Personne personne : repertoire) {
			if (personne.compareTo(searched) == 0) {
				liste.add(personne);
			}			
		}
		return liste;
	}
	
	public int size() {
		return repertoire.size();
	}
	*/
}
