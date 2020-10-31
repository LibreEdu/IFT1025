package epicerie;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Personne {
	
	private String prenom;
	private String nom;
	protected boolean feminin;
	private Date ddn; // Date de naissance
	private int id;
	private static int compteur;

	public Personne(String prenom, String nom, boolean feminin, String date) {
		this.prenom = prenom;
		this.nom = nom;
		this.feminin = feminin;
		
		// http://tutorials.jenkov.com/java-internationalization/simpledateformat.html
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ddn = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			System.out.println("La date n’est pas au format aaaa-mm-jj");
			e.printStackTrace();
		}
		
		id = compteur++;
		
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
		DecimalFormat df = new DecimalFormat("000,000");
		String f = feminin ? "e" : "";
		return df.format(id) + ", " + prenom + " " + nom + ", né" + f + " le "
			+ sdf.format(ddn);
	}

}
