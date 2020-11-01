package epicerie;

import java.text.ParseException;

public class Employe extends Personne{

	public Employe(String prenom, String nom, String date, boolean feminin)
			throws ParseException {
		super(prenom, nom, date, feminin);
	}

	public String toString() {
		String f = feminin ? "e" : "";
		return super.toString() + ", employé" + f;
	}
}
