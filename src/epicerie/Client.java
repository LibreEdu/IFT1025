package epicerie;

import java.text.ParseException;

public class Client extends Personne{

	public Client(String prenom, String nom, String date, boolean feminin)
			throws ParseException {
		super(prenom, nom, date, feminin);
	}

	public String toString() {
		String f = feminin ? "e" : "";
		return super.toString() + ", est un" + f + " client" + f;
	}
}
