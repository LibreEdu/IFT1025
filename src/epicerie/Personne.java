package epicerie;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public abstract class Personne implements Comparable<Personne> {
	
	private String prenom;
	private String nom;
	private LocalDate ddn; // Date de naissance
	protected boolean feminin;
	private String courriel;
	private String role;
	private int id;
	private static int compteur;

	public Personne(String prenom, String nom, String date, boolean feminin) {
		int nbCourriels;
		
		this.prenom = prenom;
		this.nom = nom;
		
		//https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ddn = LocalDate.parse(date, dtf);
		
		this.feminin = feminin;
		
		
		// On remplace 1 ou plusieurs espaces par rien
		// https://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
		
		// Puis on supprime les signes diacritiques (accent, tréma et cédille)
		// https://stackoverflow.com/questions/1453171/remove-diacritical-marks-%c5%84-%c7%b9-%c5%88-%c3%b1-%e1%b9%85-%c5%86-%e1%b9%87-%e1%b9%8b-%e1%b9%89-%cc%88-%c9%b2-%c6%9e-%e1%b6%87-%c9%b3-%c8%b5-from-unicode-chars
		
		prenom = stripDiacritics(prenom.replaceAll("\\s+",""));
		nom = stripDiacritics(nom.replaceAll("\\s+",""));

		
		nbCourriels = Repertoire.searchEmail(prenom + "." + nom);
		if (nbCourriels == 0) {
			courriel = prenom + "." + nom + "@" + Magasin.getTld();
		} else {
			courriel = prenom + "." + nom + nbCourriels + "@" + Magasin.getTld();
		}
		
		// class epicerie.Client => Client
		role = this.getClass().toString().replaceAll(".+\\.","");
		
		id = compteur++;
		
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LocalDate getDdn() {
		return ddn;
	}

	public void setDdn(LocalDate ddn) {
		this.ddn = ddn;
	}

	public boolean isFeminin() {
		return feminin;
	}

	public void setFeminin(boolean feminin) {
		this.feminin = feminin;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMMM yyyy");
		DecimalFormat df = new DecimalFormat("000,000");
		String f = feminin ? "e" : "";
		return df.format(id) + ", " + prenom + " " + nom + ", né" + f + " le "
			+ ddn.format(dtf) + ", " + courriel + ", classe " + role;
	}

	public int compareTo (Personne personne) {
		// On compare les noms, s’ils sont identiques on compare les prénoms
		int n = nom.compareTo(personne.nom);
		return n == 0 ? prenom.compareTo(personne.prenom) : n;
	}
	
	public static final Pattern DIACRITICS_AND_FRIENDS
    	= Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

	private static String stripDiacritics(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
	    return str;
	}
	
	public boolean equals(Personne personne) {
		if (id == personne.getId()) {
			return true ;
		} else {
			return false;
		}
	}

}
