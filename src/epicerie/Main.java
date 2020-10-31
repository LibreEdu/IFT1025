package epicerie;

import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		Repertoire rep = new Repertoire();
		
		Client tt = new Client("Thomas", "Tremblay", false, "2000-01-01");
		rep.ajouter(tt);
		
		Employe ed = new Employe("Emma", "Durand", true, "1993-12-13");
		rep.ajouter(ed);
		
		Directeur wd = new Directeur("Willam", "Dupond", false, "1995-04-01");
		rep.ajouter(wd);
		
		System.out.println(rep.toString());
	}

}
