package epicerie;

import java.text.ParseException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws ParseException {
		Repertoire rep = new Repertoire();
		
		Client tt = new Client("Dominique", "Tremblay", "2000-01-01", false);
		rep.add(tt);

		Client tt2 = new Client("Dominique", "Tremblay", "1983-08-17", true);
		rep.add(tt2);

		Client am = new Client("Augustyn", "Mirys", "1700-01-01", false);
		rep.add(am);

		Employe ed = new Employe("Emma", "Durand", "1993-12-13", true);
		rep.add(ed);
		
		Directeur wd = new Directeur("Willam", "Dupond", "1995-04-01", false);
		rep.add(wd);
		
		//System.out.println(rep.toString());
		
		ArrayList<Personne> search = rep.Search("Dominique", "Tremblay");
		
		System.out.println(search.toString() + "\n");
	}

}
