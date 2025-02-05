package modele;

/**
 * Les données : création de clients, produits...
 * 
 * @author Jeanne Laflamme
 * @author Alexandre Pachot
 */
public final class Data {
	public Magasin magasin = new Magasin("magasin.ca");
	public Repertoire repertoire = new Repertoire();
	public Directeur directeur = new Directeur("Helen", "Walton", "2007-04-19");
	public Directeur directeur2 = new Directeur("Sam", "Walton", "1992-04-07");
	public Employe employe = new Employe("Valériane", "Vukosavljević", "1994-04-29");
	public Employe employe2 = new Employe("Lénaïc", "Le Breton", "0005-09-08");
	public Client client = new Client("Eric-Emmanuel", "DeMorgan", "1906-06-27");
	public Client client2 = new Client("Éric-Emmanuel", "De Morgan", "1960-03-28");
	public Client client3 = new Client("Éric-Emmanuel", "De Morgane", "1966-09-26");
	public Client client4 = new Client("Sofía", "de Borbón y Ortiz", "2007-04-29");
	public Client client5 = new Client("Françoise", "Ça", "1999-02-31");
	public Produit alim1 = new Aliment("Broccoli","vert",2.5);
	public Produit meuble1 = new Meuble("Table",100,300);
	public Produit meuble2 = new Meuble("Chaise",150,75.99);

	public Data() {
		client4.addMoney(1000);
		client4.addProduitPref(alim1);
		client4.addProduitPref(meuble1);
		directeur.addProduit(alim1);
		directeur.addProduit(meuble1);
		directeur2.addProduit(meuble2);
	}

}
