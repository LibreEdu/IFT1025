package modele;

public abstract class Produit {
	
	private Directeur directeur;
	private static int compteur;
	private int id;
	private String sorte;
	
	public Produit(){
		this.id=compteur;
		compteur++;
		
		sorte = this.getClass().toString().replaceAll(".+\\.","");
	}
	
	public void setDirecteur(Directeur dir) {
		this.directeur=dir;
	}

	public Directeur getDirecteur() {
		return directeur;
	}
	
	public int getId() {
		return id;
	}
	
	public String getSorte() {
		return sorte;
	}
	
	
	abstract public boolean equals(Produit p);
	
}
