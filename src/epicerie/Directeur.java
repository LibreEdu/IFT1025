package epicerie;

import java.text.ParseException;

public class Directeur extends Employe{

	public Directeur(String prenom, String nom, String date, boolean feminin)
			throws ParseException {
		super(prenom, nom, date, feminin);
	}

	public String toString() {
		String f = feminin ? "rice" : "eur";
		return super.toString() + " et direct" + f;
	}
	
}