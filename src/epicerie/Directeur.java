package epicerie;

import java.text.ParseException;

public class Directeur extends Employe{

	public Directeur(String prenom, String nom, boolean feminin, String date) throws ParseException {
		super(prenom, nom, feminin, date);
	}

	public String toString() {
		String f = feminin ? "rice" : "eur";
		return super.toString() + " et direct" + f;
	}
}