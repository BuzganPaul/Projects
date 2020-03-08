package Main;

import java.io.IOException;

import Working.SavedData;

public class Main {

	public static void main(String[] args) throws IOException {
		SavedData test =  new SavedData();
		test.AddAll();
		test.printAll();
		test.diferentaOre();
		test.contorizareActivitati();
		test.cautareActivitati();
		test.filtreazaActivities();

	}

}
