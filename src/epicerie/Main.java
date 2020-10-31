package epicerie;

import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		Client tt = new Client("Thomas", "Tremblay", "2000-01-01");
		Employe ed = new Employe("Emma", "Durand", "1993-12-13");
		Directeur wd = new Directeur("Willam", "Dupond", "1995-04-01");
		System.out.println(tt.toString());
		System.out.println(ed.toString());
		System.out.println(wd.toString());
	}

}
