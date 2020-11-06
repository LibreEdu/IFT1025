package modele;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public final class Repertoire {
	private static ArrayList<Personne> personnes = new ArrayList<Personne>();
	private static ArrayList<Produit> produits = new ArrayList<Produit>();
	private static int nbClient;
	private static int nbEmploye;
	private static int nbDirecteur;
	
	public static void add(Personne personne) {
		personnes.add(personne);
		switch(personne.getRole()) {
			case "Client":
				Repertoire.nbClient++;
				break;
			case "Employe":
				Repertoire.nbEmploye++;
				break;
			case "Directeur":
				Repertoire.nbDirecteur++;
				break;
		}
	}

	public static void add(Produit produit) {
		produits.add(produit);
	}

	public String toString() {
		String result = "";
		for(Personne personne : personnes) {
			result += personne.toString() + "\n";
		}
		//for(Produit produit : produits) {
		//	result += produit.toString() + "\n";
		//}
		return result;
	}
	
	public static String personneToString() {
		String result = "Personnes enregistrées:\n";
		for(Personne personne : personnes) {
			result += personne.toString() + "\n";
		}
		return result;
	}
	
	public static String[][] personneData(String typeOfPersonne) {
		int length = 0;
		switch(typeOfPersonne) {
			case "Client":
				length = Repertoire.nbClient;
				break;
			case "Employe":
				length = Repertoire.nbEmploye;
				break;
			case "Directeur":
				length = Repertoire.nbDirecteur;
				break;
		}
		
		String[][] result = new String[length][4];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int i = 0;
		
		for(Personne personne : personnes) {
			if (personne.getRole().equals(typeOfPersonne)) {
				//System.out.println("toutou");
				String[] row = new String[4];
				row[0] = Integer.toString(personne.getId());
				//System.out.println(row[0]);
				row[1] = personne.getNom();
				row[2] = personne.getPrenom();
				row[3] = personne.getDdn().format(dtf);
				result[i++] = row;
			}
		}
		return result;
	}
	
	public static String[] personneEntete() {
		String[] result = {"Identifiant", "Nom", "Prénom", "Date de naissance"};
		return result;
	}
	
	public static String produitToString() {
		String result = "Produits disponibles:\n";
		for(Produit produit : produits) {
			result += produit.toString() + "\n";
		}
		return result;
	}
	
	public static int searchEmail(String debutCourriel) {
		int compteur = 0;
		
		for(Personne personne : personnes) {
			String pCourriel = personne.getCourriel();
			
			// On remplace 0 ou plus chiffres (d*), le @ 
			// et 1 ou plus caractères (.+) par rien
			pCourriel = pCourriel.replaceAll("\\d*@.+","");
			
			if ( debutCourriel.equals(pCourriel)) {
				compteur++;
			}			
		}	
		return compteur;
	}
	
	public static int indexOf(Personne personne) {
		return personnes.indexOf(personne);
	}
	
	public static int indexOf(Produit produit) {
		return produits.indexOf(produit);
	}
	
	public static boolean remove(Personne personne) {
		return personnes.remove(personne);
	}
	
	public static boolean remove(Produit produit) {
		return produits.remove(produit);
	}
	
	public static ArrayList<Personne> search(String searchedString) {
		ArrayList<Personne> liste = new ArrayList<Personne>();
		for(Personne personne : personnes) {
			if (personne.getNom().startsWith(searchedString)) {
				liste.add(personne);
			}			
		}
		return liste;
	}
	
}
