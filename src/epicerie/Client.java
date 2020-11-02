package epicerie;
import java.text.DecimalFormat;
import java.text.ParseException;

public class Client extends Personne{
	
	private float solde = 0;

	public Client(String prenom, String nom, String date, boolean feminin) {
		super(prenom, nom, date, feminin);
	}

	public float getSolde() {
		return solde;
	}

	public void addSolde(float solde) {
		this.solde += solde;
	}

	public String toString() {
		String f = feminin ? "e" : "";
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return super.toString() + ", est un" + f + " client" + f + ", solde = "
				+ df.format(solde);
	}
}
