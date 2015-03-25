package tianMap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	public static void main(String[] args) {
		
		String a = "09:30:00";
		String b = "11:20:20";
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			
			Date da = sdf.parse(a);
			Date db = sdf.parse(b);
			
			System.out.println(da.after(db));
		} catch (Exception e) {
			
		}
		
	}
}
