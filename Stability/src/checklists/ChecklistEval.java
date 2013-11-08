package checklists;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;


public class ChecklistEval {
	
	
	
	public ChecklistEval(){
		
	}
	
	public double getEvaluation(String ro, String minim, String purpose) throws IOException{
		//String urlBase = "http://andros.zoo.ox.ac.uk:8080/evaluate/checklist";
		String urlBase = "http://calatola.man.poznan.pl/roevaluate/evaluate/checklist";
		//String urlBase="http://andros.zoo.ox.ac.uk:8080/evaluate/checklist?RO=http%3A%2F%2Fandros.zoo.ox.ac.uk%2Fworkspace%2Fwf4ever-ro-catalogue%2Fv0.1%2Fsimple-requirements%2F&minim=http%3A%2F%2Fandros.zoo.ox.ac.uk%2Fworkspace%2Fwf4ever-ro-catalogue%2Fv0.1%2Fsimple-requirements%2Fsimple-requirements-minim.rdf&purpose=Runnable";
		//String urlBase= "http://calatola.man.poznan.pl/roevaluate/evaluate/checklist?RO=http://purl.org/net/wf4ever/ro/wf74-snapshot1&minim=simple-requirements-minim.rdf&purpose=Runnable";
		String urlRO= "?RO="+ro;
		String urlMinim= "&minim="+minim;
		String urlTarget= "";
		String urlPurpose= "&purpose="+purpose;
		String urlComplete=urlBase+urlRO+urlMinim+urlTarget+urlPurpose;
		URL url = new URL(urlComplete);

        //make connection
        URLConnection urlc = url.openConnection();
        urlc.setRequestProperty("Accept", "application/rdf+xml");
        
        //use post mode
        urlc.setDoOutput(true);
        urlc.setAllowUserInteraction(false);
        
        ChecklistCalculation chk=new ChecklistCalculation();
		
        return chk.getValue(urlComplete);
	}

}
