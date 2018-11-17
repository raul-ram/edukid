package varios;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;



public abstract class Utils {
	
	public static SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
	public static DecimalFormat numberFormat=new DecimalFormat("#0.00",new DecimalFormatSymbols(new Locale("es")));
	
	public static String capitalize(String str){
		if(str==null||str.length()<1)return str;
		String pals[]=str.toLowerCase().split("\\W+");
		str="";
		for (String s : pals) {
			String l=s.substring(0,1);
			str+=s.replaceFirst(l,l.toUpperCase())+" ";
		}
		return str.substring(0,str.lastIndexOf(" "));
	}
	
	public static void setHeaders(HttpServletResponse resp){
		resp.setHeader("Pragma", "no-cache"); 
		resp.setHeader("Cache-Control", "no-cache"); 
		resp.setHeader("Cache-Control", "no-store"); 
		resp.setHeader("Cache-Control", "max-age=-1"); 
		resp.setHeader("Cache-Control", "must-revalidate");
		resp.setDateHeader("Expires",-1);
	}
	
	
	public static java.sql.Date formatDate(String d){
		return java.sql.Date.valueOf(d.substring(6,10)+"-"+d.substring(3,5)+"-"+d.substring(0,2));
	}
}

