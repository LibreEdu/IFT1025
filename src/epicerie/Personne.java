package epicerie;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Personne implements Comparable<Personne> {
	
	private String prenom;
	private String nom;
	private Date ddn; // Date de naissance
	protected boolean feminin;
	private int id;
	private static int compteur;

	public Personne(String prenom, String nom, String date, boolean feminin) {
		this.prenom = prenom;
		this.nom = nom;
		
		// http://tutorials.jenkov.com/java-internationalization/simpledateformat.html
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ddn = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			System.out.println("La date n’est pas au format aaaa-mm-jj");
			e.printStackTrace();
		}
		
		this.feminin = feminin;
		id = compteur++;
		
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
		DecimalFormat df = new DecimalFormat("000,000");
		String f = feminin ? "e" : "";
		return df.format(id) + ", " + prenom + " " + nom + ", né" + f + " le "
			+ sdf.format(ddn);
	}
	
	public int compareTo (Personne personne) {
		// On compare les noms, s’ils sont identiques on compare les prénoms
		int n = nom.compareTo(personne.nom);
		return n == 0 ? prenom.compareTo(personne.prenom) : n;
	}

}
