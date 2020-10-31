package epicerie;

import java.text.ParseException;

public class Directeur extends Employe{

	public Directeur(String prenom, String nom, String date) throws ParseException {
		super(prenom, nom, date);
	}

	public String toString() {
		return super.toString() + " et directeur.trice";
	}
}