package epicerie;

import java.text.ParseException;

public class Client extends Personne{

	public Client(String prenom, String nom, String date) throws ParseException {
		super(prenom, nom, date);
	}

	public String toString() {
		return super.toString() + ", Client";
	}
}
