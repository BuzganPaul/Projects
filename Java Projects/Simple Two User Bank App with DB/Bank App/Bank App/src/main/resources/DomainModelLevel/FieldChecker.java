package DomainModelLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FieldChecker {
	
	public static boolean allDigit(String str)
	{
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                return false;
            }
        }
        return true;
	}
	
	public static boolean noDigit(String str)
	{
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
	}
	
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    
    public static String getDate()
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	Date date = new Date();
    	String auxi = dateFormat.format(date).toString();
    	auxi.replace('-', '/');
    	return auxi;
    }
    
    public static int getID()
    {
    	Random rand = new Random();
    	int rand_int = rand.nextInt(1000);
    	return rand_int;
    }

}
