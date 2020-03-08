

package Bussiness;
import DataAcces.*;
public class DBOperations {
	
	public static void addItem(Object object) {
        ModificareDB.addItem(object);
    }

    public static void editItem(Object object) {
    	ModificareDB.editItem(object);
    }

    public static void deleteItem(Object object) {
    	ModificareDB.deleteItem(object);
    }

    public static Object findItem(Object object) {
        return ModificareDB.findItem(object);
    }
	
	
}
