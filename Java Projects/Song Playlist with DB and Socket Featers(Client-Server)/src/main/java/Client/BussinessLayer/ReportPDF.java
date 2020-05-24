package Client.BussinessLayer;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportPDF implements Report {

	private String path;

	public ReportPDF(String pathAux) {
		this.path = pathAux;
	}

	@Override
	public void generate(ArrayList<String> toPrint) {

		try {
			
			PDDocument doc = new PDDocument();
			

			PDPage page = new PDPage();
			PDPageContentStream contentStream = new PDPageContentStream(doc, page);

			contentStream.beginText();


			contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);

			
			contentStream.setLeading(14.5f);
			contentStream.newLineAtOffset(25, 725);
			


			contentStream.showText("Playlist:");
			contentStream.newLine();
			
			for(String aux : toPrint)
			{
				contentStream.showText(aux);
				contentStream.newLine();
			}

			contentStream.endText();

			System.out.println("Content added");

			contentStream.close();
			doc.addPage(page);

			doc.save(path);
			doc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("PDF created");

	}

}
