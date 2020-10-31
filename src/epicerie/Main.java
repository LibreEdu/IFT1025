package epicerie;

import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		Client tt = new Client("Thomas", "Tremblay", "2000-01-01");
		System.out.println(tt.toString());
	}

}
