package carshare;

import java.text.DecimalFormat;

public class Test {

	public static void main(String[] args) {
		String a = "3904.6987";
		Double ai = Double.parseDouble(a.substring(0, 2));
		Double bi = Double.parseDouble(a.substring(2, 9));
		Double c = ai+bi/60;
		DecimalFormat df = new DecimalFormat("######0.00000000");
		String jingdu =df.format(c);
		System.out.println(jingdu);
	}

}
  