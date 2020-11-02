package epicerie;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Classe à partir de laquelle l’interface principale est lancée
 * 
 * @author Jeanne Laflamme
 * @author Alexandre Pachot
 * 
 */
public class Main {
	
	/**
	 * Méthode principale
	 * 
	 * @param args aucun
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Magasin magasin = new Magasin("magasin.ca");
		
		Repertoire repertoire = new Repertoire();
		
		Directeur directeur = new Directeur("Helen", "Walton", "2007-04-19", true);
		repertoire.add(directeur);

		Directeur directeur2 = new Directeur("Sam", "Walton", "1992-04-07", false);
		repertoire.add(directeur2);

		Employe employe = new Employe("Valériane", "Vukosavljević", "1994-04-29", true);
		repertoire.add(employe);

		Employe employe2 = new Employe("Éric-Emmanuel", "De Morgan", "1960-03-28", false);
		repertoire.add(employe2);

		Client client = new Client("Eric-Emmanuel", "DeMorgan", "1906-06-27", false);
		repertoire.add(client);

		Client client2 = new Client("Éric-Emmanuel", "De Morgane", "1966-09-26", false);
		repertoire.add(client2);

		Client client3 = new Client("Lénaïc", "Le Breton", "0005-09-08", true);
		repertoire.add(client3);
		
		Client client4 = new Client("Sofía", "de Borbón y Ortiz", "2007-04-29", true);
		repertoire.add(client4);

		Client client5 = new Client("Françoise", "Ça", "1999-02-31", true);
		repertoire.add(client5);

		employe.augmenterSolde(client, 1000.50);
		
		System.out.println(repertoire.toString());
		
		/*
		ArrayList<Personne> liste = repertoire.search("De ");
		for(Personne personne : liste) {
			System.out.println(personne.toString());
		}
		*/
		
	}

}
