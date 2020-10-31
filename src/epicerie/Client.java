package epicerie;

import java.text.ParseException;

public class Client extends Personne{

	public Client(String prenom, String nom, boolean feminin, String date)
			throws ParseException {
		super(prenom, nom, feminin, date);
	}

	public String toString() {
		String f = feminin ? "e" : "";
		return super.toString() + ", client" + f;
	}
}
