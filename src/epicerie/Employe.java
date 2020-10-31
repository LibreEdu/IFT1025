package epicerie;

import java.text.ParseException;

public class Employe extends Personne{

	public Employe(String prenom, String nom, boolean feminin, String date) throws ParseException {
		super(prenom, nom, feminin, date);
	}

	public String toString() {
		String f = feminin ? "e" : "";
		return super.toString() + ", employé" + f;
	}
}
