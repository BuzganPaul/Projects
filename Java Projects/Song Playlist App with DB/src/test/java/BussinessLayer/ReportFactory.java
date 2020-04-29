package BussinessLayer;

import java.util.ArrayList;

public class ReportFactory {
	
	   public Report getReport(String type, String path){
		      if(type == null){
		         return null;
		      }		
		      if(type.equalsIgnoreCase("txt")){
		         return new ReportTXT(path);
		         
		      } else if(type.equalsIgnoreCase("pdf")){
		         return new ReportPDF(path);
		      }
		      
		      return null;
		   }

}
