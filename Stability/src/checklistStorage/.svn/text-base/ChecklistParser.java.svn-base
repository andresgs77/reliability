package checklistStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ChecklistParser {
	
	private ArrayList<CheckedRule> checkedRules;
	private String link;
	private String type;
	private String eval;
	private String evalClass;
	//private int timeAux;

	public ChecklistParser(String ro, String type,String link){
		// http://sandbox.wf4ever-project.org/roevaluate/evaluate/trafficlight_json?RO=http://sandbox.wf4ever-project.org/rodl/ROs/golden-exemplar-gk/&minim=checklist-runnable.rdf&purpose=Runnable
		String endPoint="http://calatola.man.poznan.pl/roevaluate/evaluate/trafficlight_json?RO=";
		//http://calatola.man.poznan.pl/
		//String endUri="&minim=checklist-runnable.rdf&purpose=Runnable";
		String endUri = "&minim=http://sandbox.wf4ever-project.org/rodl/ROs/Y2Demo-test/workflow-experiment-checklist.rdf&purpose=ready-to-release";
    	String requestURL=null;

		requestURL = endPoint + ro + endUri;
		System.out.println(requestURL);

			
		    String response = "";
		    BufferedReader rd = null;
		    try {
		        URL url = new URL(requestURL);
		        URLConnection conn2 = url.openConnection();
		        conn2.setRequestProperty("Accept", "application/rdf+xml");
		        rd = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
		        
		        String line;
		        while ((line = rd.readLine()) != null) {
		            //Process line...
		            response= response+line+"\n";
		        }
		    } catch (Exception e) {
		        System.out.println("Web request failed: "+ requestURL);
		    // Web request failed
		    } finally {
		        if (rd != null) {
		            try {
		                rd.close();
		            } catch (IOException ex) {
		                System.out.println("Problema al cerrar el objeto lector");
		            }
		        }
		    }


		    //timeAux=time;
		    this.type=type;
		    this.link=link;
		    //fakeCheckedRules();
			parse2(response); //el 1 es sin incluir may, must, should...
			eval=formatResults();
	}

	private String formatResults() {
		String desc=getEvalDescription();
		String date=getDate();
		String value=getValue2(); //el uno es con porcentajes
		String snp=getSnapshot();
		String eClass=getEvalClass();
		
		String response="{"+date+","+snp+","+value+","+eClass+","+desc+"}";
		return response;
	}

	private void parse(String response) {
		checkedRules = new ArrayList<CheckedRule>();
		String item="";
		String check="";
		if (response != null) {
			Scanner scanner = new Scanner(response);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				
				if (line.contains("evalresultclass")){
					if (line.contains("must"))evalClass="must";
						else if (line.contains("should"))evalClass="should";
							else evalClass="pass";
				}
				
				if (line.contains("itemlabel")) {
					String[] aux = line.split("\"");
					item=aux[aux.length-1];
				} else if (line.contains("itemsatisfied")) {
					String[] aux = line.split(" ");
					check=aux[aux.length-1];
					checkedRules.add(new CheckedRule(item,check));
				}
			}
		}

	}
	
	private void parse2(String response) {
		checkedRules = new ArrayList<CheckedRule>();
		String item="";
		String type="";
		String check="";
		if (response != null) {
			Scanner scanner = new Scanner(response);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				
				if (line.contains("evalresultclass")){
					if (line.contains("must"))evalClass="must";
						else if (line.contains("should"))evalClass="should";
							else evalClass="pass";
				}
				
				if (line.contains("itemlabel")) {
					String[] aux = line.split("\"");
					item=aux[aux.length-1];
				} else if (line.contains("hasMustRequirement")){
					type="must";
				} else if (line.contains("hasShouldRequirement")){
					type="should";
				} else if (line.contains("hasMayRequirement")){
					type="may";
				} else if (line.contains("itemsatisfied")) {
					String[] aux = line.split(" ");
					check=aux[aux.length-1];
					checkedRules.add(new CheckedRule(item,check,type));
				}
				
			}
		}

	}
	
	private String getEvalDescription(){
		String response ="\"desc\":{";
		int i=0;
		String aux ="";
		for (CheckedRule c: checkedRules){
			if (c.isChecked())aux=c.getCheck();
			else aux=c.getType();
			response = response + "\"R"+i+"\":[\""+c.getItem()+"\",\""+aux+"\"],";
			i++;
		}
		response = response.substring(0, response.length()-1);
		response = response + "}";
		return response;
	}
	
	private String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy,M,d,H,m");
		//get current date time with Date()
		Date d = new Date();
		
		/*Calendar c = Calendar.getInstance(); 
		c.setTime(d); 
		c.add(Calendar.DATE, 13+timeAux*7);
		d = c.getTime();*/
		
		String response ="\"date\":";
		String date = dateFormat.format(d);
		response = response + "\""+date+"\"";
		return response;
	}
	
	private String getValue(){
		String response ="\"value\":";
		double positive=0;
		for (CheckedRule c: checkedRules){
			if (c.isChecked()) positive++;
		}
		
		if (positive>0)positive = positive/checkedRules.size();
		positive = Math.round(positive*1000);
		positive = positive/1000;
		response = response + "\""+positive+"\"";
		return response;
	}
	
	private String getValue2(){
		String response ="\"value\":";
		double mustTotal=0;
		double shouldTotal=0;
		double mayTotal=0;
		double must=0;
		double should=0;
		double may=0;
		
		double positive=0;
		for (CheckedRule c: checkedRules){
			if (c.getType().equals("must")){
				mustTotal++;
				if (c.isChecked())must++;
			}else if (c.getType().equals("should")){
				shouldTotal++;
				if (c.isChecked())should++;
			}else if (c.getType().equals("may")){
				mayTotal++;
				if (c.isChecked())may++;
			}
			
		}
		
		double percentageMust=1;
		double percentageShould=1;
		double percentageMay=1;
		
		if (mustTotal>0) percentageMust=must/mustTotal;
		if (shouldTotal>0) percentageShould=should/shouldTotal;
		if (mayTotal>0) percentageMay=may/mayTotal;
		
		if (evalClass.equals("must")){
			positive=positive+percentageMust*0.5;
			positive=positive+percentageShould*0.07;
			positive=positive+percentageMay*0.03;
		} else if (evalClass.equals("should")){
			positive=positive+ 0.6;
			positive=positive+percentageShould*0.25;
			positive=positive+percentageMay*0.05;
		} else {
			positive=positive+ 0.6;
			positive=positive+ 0.3;
			positive=positive+percentageMay*0.1;
		}
		
		
		positive = Math.round(positive*1000);
		positive = positive/1000;
		response = response + "\""+positive+"\"";
		return response;
	}
	
	private String getSnapshot(){
		if (type==null)type="empty";
		
		String response ="\"link\":";
		response = response + "[\""+type+"\"";
		if (!type.equals("empty"))response=response + ",\""+link+"\"";
		response = response + "]";
		return response;
	}
	
	private String getEvalClass() {	
		String response ="\"evalClass\":";
		response = response + "\""+evalClass+"\"";
		return response;
	}

	
	public String getEval() {
		return eval;
	}
	
	private void fakeCheckedRules(){
		checkedRules = new ArrayList<CheckedRule>();
		
		evalClass="must";
		
		String m1 ="All workflow definitions are accessible";
		String m11="true";
		String m2="Workflow inputs are all accessible";
		String m21="true";
		String m3 ="Workflow inputs are described";
		String m31="true";
		String m4="Workflow description is present";
		String m41="true";
		String m5 ="Web service <http://rest.kegg.jp/get/{query}> is not accessible";
		//Web service <http://rest.kegg.jp/get/{query}> is not accessible
		//Web services used are all accessible
		String m51="false";
		
		
		String s1 ="Workflow design sketch is present";
		String s11="true";
		String s2="Experiment hypothesis is present";
		String s21="true";
		String s3 ="Experiment conclusions are present";
		String s31="true";

		checkedRules.add(new CheckedRule(s2,s21,"should"));
		checkedRules.add(new CheckedRule(s1,s11,"should"));
		checkedRules.add(new CheckedRule(m4,m41,"must"));
		checkedRules.add(new CheckedRule(m1,m11,"must"));
		checkedRules.add(new CheckedRule(m3,m31,"must"));
		checkedRules.add(new CheckedRule(m2,m21,"must"));
		checkedRules.add(new CheckedRule(m5,m51,"must"));
		checkedRules.add(new CheckedRule(s3,s31,"should"));
		
		
	}
}
