package epicerie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Personne {
	
	String prenom;
	String nom;
	Date ddn; // Date de naissance

	public Personne(String prenom, String nom, String date) {
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
		
	}
	
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy");
		return prenom + " " + nom + " né.e le "
			+ simpleDateFormat.format(ddn);
	}

}
