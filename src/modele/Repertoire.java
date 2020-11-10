package modele;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public final class Repertoire {
	private static ArrayList<Personne> personnes = new ArrayList<Personne>();
	private static ArrayList<Produit> produits = new ArrayList<Produit>();
	private static int nbClient;
	private static int nbEmploye;
	private static int nbDirecteur;
	private static int nbAlim;
	private static int nbMeuble;
	
	/**
	 * Ajoute une personne à la liste des personnes, et met à jour les compteurs
	 * 
	 * @param personne Personne à ajouter au répertoire
	 */
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

	/**
	 * Ajoute un produit à la liste des produits
	 * 
	 * @param produit produit à ajouter
	 */
	public static void add(Produit produit) {
		produits.add(produit);
		if(produit instanceof Aliment) {
			Repertoire.nbAlim++;
		} else if(produit instanceof Meuble) {
			Repertoire.nbMeuble++;
		}
	}
	
	/**
	 * Retourne un tableau contenant tous les produits
	 * 	 
	 * @return tableau de produits
	 */
	public static Produit[] getProduits() {
		Produit[] result = new Produit[produits.size()];
		int i = 0;
		for(Produit produit: produits) {
			result[i++]=produit;
		}
		return result;
	}
	
	/**
	 * Retourne la liste des personnes
	 * 	 
	 * @return ArrayList de personnes
	 */
	public static ArrayList<Personne> getListePersonne() {
		return personnes;
	}
	
	
	/**
	 * Renvoie un tableau de clients, d’employés ou de directeurs,
	 * afin de remplir la liste dans la vue
	 * 
	 * @param typeOfPersonne "Client", "Employe" ou "Directeur"
	 * @return Liste de clients, d’employés ou de directeurs
	 */
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
				String[] row = new String[4];
				row[0] = Integer.toString(personne.getId());
				row[1] = personne.getPrenom();
				row[2] = personne.getNom();
				row[3] = personne.getDdn().format(dtf);
				result[i++] = row;
			}
		}
		return result;
	}
	
	/**
	 * Renvoie l’entête du tableau de personnes
	 * 
	 * @return l’entête du tableau de personnes
	 */
	public static String[] personneEntete() {
		String[] result = {"Identifiant", "Prénom", "Nom", "Date de naissance"};
		return result;
	}
	
	/**
	 * Renvoie l’objet Personne pour l’id donné
	 * 
	 * @param id Identifiant de la personne
	 * 
	 * @return Personne cprrespondant à l’identifiant
	 */
	public static Personne getPersonne(int id) {
		for(Personne personne : personnes) {
			if (personne.getId() == id) {
				return personne;
			}			
		}
		return null;
	}
	
	/**
	 * Renvoie un tableau d'aliments ou de meubles,
	 * afin de remplir la liste dans la vue
	 * 
	 * @param typeOfProduit "Aliment" ou "Meuble"
	 * @return Liste d'aliments ou de meubles
	 */
	public static String[][] produitData(String typeOfProduit) {
		int length = 0;
		int i = 0;
		String[][] result = new String[0][0];
		
		switch(typeOfProduit) {
			case "Aliment":
				length = Repertoire.nbAlim;
				String[][] result1 = new String[length][5];
				
				for(Produit produit : produits) {
					if (produit.getSorte().equals("Aliment")) {
						String[] row = new String[5];
						row[0] = ""+produit.getId();
						row[1] = ((Aliment)produit).getNom();
						row[2] = ((Aliment)produit).getCouleur();
						row[3] = Double.toString(((Aliment)produit).getPoids());
						row[4] = produit.getDirecteur().toString();
						result1[i++] = row;
					}
				}
				return result1;
			case "Meuble":
				length = Repertoire.nbMeuble;
				String[][] result2 = new String[length][5];
				
				for(Produit produit : produits) {
					if (produit.getSorte().equals("Meuble")) {
						String[] row = new String[5];
						row[0] = ""+produit.getId();
						row[1] = ((Meuble)produit).getType();
						row[2] = Double.toString(((Meuble)produit).getHauteurMax());
						row[3] = Double.toString(((Meuble)produit).getPrix());
						row[4] = produit.getDirecteur().toString();
						result2[i++] = row;
					}
				}
				return result2;
		}
		return result;
	}
	
	/**
	 * Renvoie l’entête du tableau de produits
	 * 
	 * @param typeOfProduit "Aliment" ou "Meuble"
	 * @return l’entête du tableau de produits
	 */
	public static String[] produitEntete(String typeOfProduit) {
		String[] result = new String[5];
		result[0]="Id";
		switch(typeOfProduit) {
		case "Aliment":
			result[1]="Nom";
			result[2]="Couleur";
			result[3]="Poids";
			result[4]="Ajouté par";
			break;
		case "Meuble":
			result[1]="Type";
			result[2]="Hauteur Maximale";
			result[3]="Prix";
			result[4]="Ajouté par";
			break;
	}
		
		return result;
	}
	
	/**
	 * Retourne un tableau contenant tous les directeurs
	 * 
	 * @return Tableau de Directeurs
	 */
	public static Directeur[] getListeDir() {
		Directeur[] result = new Directeur[nbDirecteur];
		int i = 0;
		for(Personne personne : personnes) {
			if (personne.getRole().equals("Directeur")) {
				result[i++] = (Directeur) personne;
			}
		}
		return result;
	}
	

	/**
	 * Compte le nombre de courriel
	 * 
	 * @param debutCourriel <prenom.nom>
	 * 
	 * @return le nombre de courriel qui commence par <prenom.nom>
	 */
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
	
	/**
	 * L’indexe de l’objet Personne dans la liste de personnes
	 * 
	 * @param personne La personne dont on cherche l’index
	 * 
	 * @return L’index de la Personne
	 */
	public static int indexOf(Personne personne) {
		return personnes.indexOf(personne);
	}
	
	/**
	 * L’indexe de l’objet Produit dans la liste de produits
	 * 
	 * @param produit Le produit dont on cherche l’index
	 * 
	 * @return L’index du produit
	 */
	public static int indexOf(Produit produit) {
		return produits.indexOf(produit);
	}
	
	/**
	 * Supprime une personne de la liste de personnes
	 * 
	 * @param personne La personne à supprimer
	 * 
	 * @return
	 */
	public static boolean remove(Personne personne) {
		if (personne instanceof Client) {
			nbClient--;
		} else if(personne instanceof Employe) {
			nbEmploye--;
		} else if(personne instanceof Directeur) {
			nbDirecteur--;
		}
		return personnes.remove(personne);
	}
	
	/**
	 * Supprime un produit de la liste de produits
	 * 
	 * @param produit produit à supprimer
	 * 
	 * @return
	 */
	public static boolean remove(Produit produit) {
		if (produit instanceof Aliment) {
			nbAlim--;
		} else if(produit instanceof Meuble) {
			nbMeuble--;
		}
		return produits.remove(produit);
	}
	
	/**
	 * Retourne la liste de personnes dont le nom ou le prenom commence par searchedString
	 * 
	 * @param searchedString La chaine de caractères que l’on recherche
	 * 
	 * @return Liste de personnes
	 */
	public static ArrayList<Personne> searchByNameStartsWith(String searchedString) {
		ArrayList<Personne> liste = new ArrayList<Personne>();
		for(Personne personne : personnes) {
			if (personne.getNom().startsWith(searchedString)
					|| personne.getPrenom().startsWith(searchedString)) {
				liste.add(personne);
			}			
		}
		return liste;
	}
	
	/**
	 * Renvoie un tableau de clients, d’employés et de directeurs,
	 * qui provient d'une liste
	 * 
	 * @param ArrayListe de Personnes
	 * @return Tableau d'informations des personnes
	 */
	public static String[][] personneData(ArrayList<Personne> liste) {
		
		String[][] result = new String[liste.size()][4];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int i = 0;
		
		for(Personne personne : liste) {
			String[] row = new String[4];
			row[0] = Integer.toString(personne.getId());
			row[1] = personne.getPrenom();
			row[2] = personne.getNom();
			row[3] = personne.getDdn().format(dtf);
			result[i++] = row;
		}
		return result;
	}
	

	/**
	 * Retourne l’objet Personne dont l’identifiant est id
	 * 
	 * @param id identifiant de la personne cherchée
	 * 
	 * @return personne dont l’identifiant est id
	 */
	public static Personne searchById(int id) {
		for(Personne personne : personnes) {
			if (personne.getId() == id) {
				return personne;
			}	
		}
		return null;		
	}
	
	/**
	 * Retourne l’objet Produit dont l’identifiant est id
	 * 
	 * @param id identifiant du produit cherché
	 * 
	 * @return produit dont l’identifiant est id
	 */
	public static Produit searchByIdProd(int id) {
		for(Produit produit : produits) {
			if (produit.getId() == id) {
				return produit;
			}	
		}
		return null;		
	}
}
