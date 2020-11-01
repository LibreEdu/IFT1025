package epicerie;

import java.text.ParseException;
import java.util.ArrayList;

public class Repertoire {
	private ArrayList<Personne> repertoire = new ArrayList<Personne>();
	
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
	
	//@SuppressWarnings("null")
	public ArrayList<Personne> Search(String prenom, String nom)
			throws ParseException {
		ArrayList<Personne> liste = new ArrayList<Personne>();
		Client searched = new Client(prenom, nom, "1900-01-01", false);
		for(Personne personne : repertoire) {
			if (personne.compareTo(searched) == 0) {
				liste.add(personne);
				//System.out.println(personne.toString());
			}
			
		}
		return liste;
	}
	
}
