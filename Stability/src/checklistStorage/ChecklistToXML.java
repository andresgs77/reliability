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

public class ChecklistToXML {
	
	private ArrayList<CheckedRule> checkedRules;
	private String link;
	private String type;
	private String eval;
	private String evalClass;
	
	public ChecklistToXML(String ro, String type,String link){
	
		String endPoint="http://calatola.man.poznan.pl/roevaluate/evaluate/trafficlight_json?RO=";
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

		    this.type=type;
		    this.link=link;
			parse(response);
			eval=formatResults();
	}
	

	private void parse(String response) {
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
	
	private String formatResults() {
		String rules=getRules();
		String evaluation=getEval(rules);
		return evaluation;
	}


	private String getEval(String rules) {
		String text="<eval result=\""+evalClass+"\">\n";
		text=text+"<date>"+getDate()+"</date>\n";
		text=text+"<rules>\n";
		text=text+rules;
		text=text+"</rules>\n</eval>\n";
		return text;
	}


	private String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy,M,d,H,m");
		//get current date time with Date()
		Date d = new Date();
		
		/*Calendar c = Calendar.getInstance(); 
		c.setTime(d); 
		c.add(Calendar.DATE, 13+timeAux*7);
		d = c.getTime();*/
		
		String date = dateFormat.format(d);
		return date;
	}


	private String getRules() {
		String text="";
		
		for (CheckedRule c: checkedRules){
			String rule="<rule type=\""+c.getType()+"\" pass=\""+c.getCheck()+"\">";
			rule=rule+c.getItem()+"</rule>";
			text=text+rule+"\n";
		}
		return text;
	}
	
	public String getXML(){
		return eval;
	}


}
