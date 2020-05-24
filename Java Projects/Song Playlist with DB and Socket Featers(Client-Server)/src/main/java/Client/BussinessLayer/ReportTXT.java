package Client.BussinessLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ReportTXT implements Report {

	private String path;
	private File file;

	public ReportTXT(String pathAux) {
		this.path = pathAux;
	}

	@Override
	public void generate(ArrayList<String> toPrint) {

		String line = "Playlist:";
		
		//path = pathAux.replaceAll("\\", "\\\\");
		
		file = new File(path);
		try {

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			
			bw.write(line);
			bw.write("\n");
			
			for(String aux : toPrint)
			{
				bw.write(aux);
				bw.write("\n");
			}
			
			bw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
