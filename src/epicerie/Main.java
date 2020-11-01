package epicerie;

import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		@SuppressWarnings("unused")
		Magasin magasin = new Magasin("magasin.ca");
		
		Repertoire rep = new Repertoire();
		
		Directeur d = new Directeur("Valériane", "Vukosavljević", "1994-04-29", true);
		rep.add(d);

		Employe e = new Employe("Éric-Emmanuel", "De Morgan", "1960-03-28", false);
		rep.add(e);

		Client c = new Client("Eric-Emmanuel", "DeMorgan", "1906-06-27", false);
		rep.add(c);

		Client c2 = new Client("Éric-Emmanuel", "De Morgane", "1966-09-26", false);
		rep.add(c2);

		Client c3 = new Client("Lénaïc", "Le Breton", "0005-09-08", true);
		rep.add(c3);
		
		Client c4 = new Client("Sofía", "de Borbón y Ortiz", "2007-04-29", true);
		rep.add(c4);

		Client c5 = new Client("Françoise", "Ça", "1999-02-31", true);
		rep.add(c5);

		//System.out.println(rep.toString());
		
		//System.out.println(d.equals(d));
		//System.out.println(d.equals(e));
	}

}
