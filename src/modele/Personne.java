package modele;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Classe abstraite qui représente une personne
 * 
 * @author Alexandre Pachot
 * @author Jeanne Laflamme
 */
public abstract class Personne implements Comparable<Personne> {
	
	private int id;
	private static int compteur;
	private String prenom;
	private String nom;
	private LocalDate ddn; // Date de naissance
	private String courriel;
	private String role;
	protected float solde = 0;
	private ArrayList<Produit> produitsPrefs = new ArrayList<Produit>();

	public static final Pattern DIACRITICS_AND_FRIENDS
    	= Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");



	/**
	 * Constructeur, permet de créer une nouvelle personne s
	 * 
	 * @param prenom   prénom
	 * @param nom      nom
	 * @param date     date de naissance au format aaaa-mm-jj
	 */
	public Personne(String prenom, String nom, String date) {
		
		this.prenom = prenom;
		this.nom = nom;
		
		//https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ddn = LocalDate.parse(date, dtf);
		
		courriel = genererCourriel(prenom, nom);

		// class epicerie.Client => Client
		role = this.getClass().toString().replaceAll(".+\\.","");
		
		id = compteur++;
		
		Repertoire.add(this);
	}
	
	public static String genererCourriel(String prenom, String nom) {
		int nbCourriels;
		
		// On remplace 1 ou plusieurs espaces par rien
		// https://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
		// Puis on supprime les signes diacritiques (accent, tréma et cédille)
		
		prenom = stripDiacritics(prenom.replaceAll("\\s+",""));
		nom = stripDiacritics(nom.replaceAll("\\s+",""));

		
		nbCourriels = Repertoire.searchEmail(prenom + "." + nom);
		
		if (nbCourriels == 0) {
			return prenom + "." + nom + "@" + Magasin.getTld();
		} else {
			return prenom + "." + nom + nbCourriels + "@" + Magasin.getTld();
		}
	}
	
	/**
	 * Renvoie le prénom de la personne
	 * 
	 * @return le prénom de la personne
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Attribue une nouvelle valeur au prénom de la personne
	 * 
	 * @param prenom	le nouveau prénom de la personne
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Renvoie le nom de la personne
	 * 
	 * @return le nom de la personne
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Attribue une nouvelle valeur au nom de la personne
	 * 
	 * @param nom	le nom de la personne
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie la date de naissance de la personne
	 * 
	 * @return la date de naissance de la personne
	 */
	public LocalDate getDdn() {
		return ddn;
	}

	/**
	 * Attribue une nouvelle valeur à la date de naissance de la personne
	 * 
	 * @param ddn	la date de naissance de la personne
	 */
	public void setDdn(LocalDate ddn) {
		this.ddn = ddn;
	}

	/**
	 * Renvoie le courriel de la personne
	 * 
	 * @return le courriel de la personne
	 */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * Attribue une nouvelle valeur au courriel de la personne
	 * 
	 * @param courriel	le courriel de la personne
	 */
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	/**
	 * Renvoie l’identifiant de la personne
	 * 
	 * @return l’identifiant de la personne
	 */
	public int getId() {
		return id;
	}
	
	public void addProduitPref(Produit p) {
		produitsPrefs.add(p);
	}
	
	public void delProduitPref(Produit prod) {
		produitsPrefs.remove(prod);
	}
	
	public Produit[] getProduitsPrefs() {
		Produit[] result = new Produit[produitsPrefs.size()];
		int i = 0;
		for(Produit produit: produitsPrefs) {
			result[i++]=produit;
		}
		return result;
	}

	public float getSolde() {
		return solde;
	}

	protected void addMoney(float solde) {
		this.solde += solde;
	}
	
	public String getRole() {
		return role;
	}

	/**
	 * Compare le nom et le prénom de deux personnes
	 * 
	 * @param personne	la personne avec laquelle on veut comparer
	 * @return 0 si les personnes ont le même nom et prénom, un nombre positif
	 * ou négatif dépendamment du tri
	 */
	public int compareTo (Personne personne) {
		int n = nom.compareTo(personne.nom);
		return n == 0 ? prenom.compareTo(personne.prenom) : n;
	}
	
	/**
	 * Enlève les signes diacritiques
	 * 
	 * https://stackoverflow.com/questions/1453171/remove-diacritical-marks-%c5%84-%c7%b9-%c5%88-%c3%b1-%e1%b9%85-%c5%86-%e1%b9%87-%e1%b9%8b-%e1%b9%89-%cc%88-%c9%b2-%c6%9e-%e1%b6%87-%c9%b3-%c8%b5-from-unicode-chars
	 * 
	 * @param str	chaine de caractères pouvant contenir des signes diacritiques
	 * @return chaine de caractères nettoyée des signes diacritiques
	 */
	private static String stripDiacritics(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
	    return str;
	}
	
	/**
	 * Indique si deux personnes ont le même identifiant
	 * 
	 * @param personne	la personne avec laquelle on veut comparer
	 * @return vrai si les personnes ont le même identifiant, faux sinon
	 */
	public boolean equals(Personne personne) {
		if (id == personne.getId()) {
			return true ;
		} else {
			return false;
		}
	}
}
