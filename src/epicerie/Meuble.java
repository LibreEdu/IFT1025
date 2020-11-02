package epicerie;

public class Meuble extends Produit {
	
	private String type;
	private float hauteurMax;
	private float prix;
	
	public Meuble(String type, float hauteurMax, float prix) {
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

	public float getHauteurMax() {
		return hauteurMax;
	}

	public void setHauteurMax(float hauteurMax) {
		this.hauteurMax = hauteurMax;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
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
		return super.toString()+ "Meuble: " + type + " hauteur maximale:" 
				+ hauteurMax + " cm " + "("+ prix + " $)";
	}

}
