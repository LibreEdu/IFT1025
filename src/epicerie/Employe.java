package epicerie;

import java.text.ParseException;

public class Employe extends Personne{

	public Employe(String prenom, String nom, String date) throws ParseException {
		super(prenom, nom, date);
	}

	public String toString() {
		return super.toString() + ", employé.e";
	}
}
