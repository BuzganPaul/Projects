package Client.BussinessLayer;


public class ReportFactory {
	
	   public static Report getReport(String type, String path){
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
