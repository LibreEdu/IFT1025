package epicerie;

import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		@SuppressWarnings("unused")
		Magasin magasin = new Magasin("magasin.ca");
		
		Repertoire rep = new Repertoire();
		
		Directeur v = new Directeur("Valériane", "Vukosavljević", "1994-04-29", true);
		rep.add(v);

		Employe e = new Employe("Éric-Emmanuel", "De Morgan", "1960-03-28", false);
		rep.add(e);

		Client e2 = new Client("Eric-Emmanuel", "DeMorgan", "1906-06-27", false);
		rep.add(e2);

		Client e3 = new Client("Éric-Emmanuel", "De Morgane", "1966-09-26", false);
		rep.add(e3);

		Client l = new Client("Lénaïc", "Le Breton", "1975-09-08", true);
		rep.add(l);
		
		Client s = new Client("Sofía", "de Borbón y Ortiz", "2007-04-29", true);
		rep.add(s);

		Client f = new Client("Françoise", "Ça", "1999-02-31", true);
		rep.add(f);

		System.out.println(rep.toString());
		
	}

}
