package Working;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class DataReading {
	
	public static void reading() throws IOException
	{
		String fileName = "Activities.txt";
        File file = new File(fileName);

        try (Stream<String> linesStream = Files.lines(file.toPath())) {
        	linesStream.forEach(line -> {
                String[] aux1=line.split("		", 3);
                MonitoredData aux2 = new MonitoredData(aux1[0], aux1[1], aux1[2].replaceAll("	", ""));
                SavedData.dataSaved.add(aux2);
        	});
        }
		
	}

}
