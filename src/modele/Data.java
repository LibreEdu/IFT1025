package modele;

public final class Data {
	public Magasin magasin = new Magasin("magasin.ca");
	public Repertoire repertoire = new Repertoire();
	public Directeur directeur = new Directeur("Helen", "Walton", "2007-04-19");
	public Directeur directeur2 = new Directeur("Sam", "Walton", "1992-04-07");
	public Employe employe = new Employe("Valériane", "Vukosavljević", "1994-04-29");
	public Employe employe2 = new Employe("Éric-Emmanuel", "De Morgan", "1960-03-28");
	public Client client = new Client("Eric-Emmanuel", "DeMorgan", "1906-06-27");
	public Client client2 = new Client("Éric-Emmanuel", "De Morgane", "1966-09-26");
	public Client client3 = new Client("Lénaïc", "Le Breton", "0005-09-08");
	public Client client4 = new Client("Sofía", "de Borbón y Ortiz", "2007-04-29");
	public Client client5 = new Client("Françoise", "Ça", "1999-02-31");

	public Data() {
		Repertoire.add(directeur);
		Repertoire.add(directeur2);
		Repertoire.add(employe);
		Repertoire.add(employe2);
		Repertoire.add(client);
		Repertoire.add(client2);
		Repertoire.add(client3);
		Repertoire.add(client4);
		Repertoire.add(client5);
		//repertoire.toString();
		//System.out.println(repertoire.toString());
		//System.out.println(Repertoire.nbDirecteur);
		//String[][] data = Repertoire.personneData("Directeur");
		//System.out.println(data.length);
		for (int i = 0; i < 2; i++) {
			//String[] row = data[i];
			for (int j = 0; j < 4; j++) {
				//System.out.println(row[j]);
				//System.out.println(data[i][j]);
			}
		}
		
	}

}