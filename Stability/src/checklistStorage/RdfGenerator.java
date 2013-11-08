package checklistStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class RdfGenerator {
	
	private ArrayList<CheckedRule> checkedRules;
	private String link;
	private String type;
	private String eval;
	private String evalClass;
	
	public RdfGenerator(String ro, String type,String link){
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
					parse(response); //el 1 es sin incluir may, must, should...
					eval=formatResults(ro);
		
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
	
	private String formatResults(String ro) {
		Model m = ModelFactory.createDefaultModel();
		String stability = "http://purl.org/wf4ever/stability#";
		String dcterms = "http://purl.org/dc/terms/";
		Resource hasEval = m.createResource( stability + "hasEvaluation" );
		 
		Property snap = m.createProperty( stability + "hasSnapshot" );
		Property arch = m.createProperty( stability + "hasArchive" );
		 
		Property value = m.createProperty( stability + "hasValue" );
		Property evalClass = m.createProperty( stability + "hasArchive" );
		Property rule = m.createProperty( stability + "hasRule" );
		Property text = m.createProperty( stability + "hasRuleText" );
		Property pass = m.createProperty( stability + "hasRulePass" );
		
		//Resource resourceEval = m.createResource
		 
		//m.add( ro, hasEval, x ).add( root, P, y ).add( y, Q, z );
		m.setNsPrefix( "stab", stability );
		m.write( System.out);
		 
		return null;
	}

}
