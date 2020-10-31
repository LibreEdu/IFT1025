package epicerie;

import java.util.ArrayList;

public class Repertoire {
	private ArrayList<Personne> repertoire = new ArrayList<Personne>();
	
	public void ajouter(Personne personne) {
		repertoire.add(personne);
	}

	public String toString() {
		String sortie = "";
		for(Personne personne : repertoire) {
			sortie += personne.toString() + "\n";
		}
		return sortie;
	}
}
