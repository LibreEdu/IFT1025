package epicerie;

public class Meuble extends Produit {
	
	private String type;
	private double hauteurMax;
	private double prix;
	
	public Meuble(String type, double hauteurMax, double prix) {
		this.type=type;
		this.hauteurMax=hauteurMax;
		this.prix=prix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getHauteurMax() {
		return hauteurMax;
	}

	public void setHauteurMax(double hauteurMax) {
		this.hauteurMax = hauteurMax;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public boolean equals(Produit meuble) {
		if(!(meuble instanceof Meuble)) {
			return false;
		} else if(this.type.equals(((Meuble) meuble).type)
				&& this.hauteurMax == ((Meuble) meuble).hauteurMax 
				&& this.prix == ((Meuble) meuble).prix) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return super.toString()+ "Meuble: " + type + " d'une hauteur maximale de: " 
				+ hauteurMax + " cm ("+ prix + " $)";
	}

}
