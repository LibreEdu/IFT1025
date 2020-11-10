package modele;

/**
 * Le magasin
 * 
 * @author Alexandre Pachot
 */
public class Magasin {
	private static String tld;

	public Magasin(String tld) {
		Magasin.tld = tld;
	}

	public static String getTld() {
		return tld;
	}

}
