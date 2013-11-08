package checklistStorage;

import java.io.File;
import java.util.ArrayList;

import storageEvaluation.Evaluation;

public class PruebaChecklistToXML {
	
	private ArrayList<Evaluation> evaluations;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String ro = "http://sandbox.wf4ever-project.org/rodl/ROs/Y2DemoStep27/";
		String ro= "http://sandbox.wf4ever-project.org/rodl/ROs/Pack401/";
		//File f = new File("WebContent/traceDemo07LongTermnnn.txt");
		File f = new File("pruebaXML3.txt");
		
		ChecklistToXML p= new ChecklistToXML(ro,null,""); 
		System.out.println(p.getXML());
		
		if(f.exists()){
			  XMLStorage xs = new XMLStorage();
			  xs.add(f,p.getXML());
		  }else{
			  XMLStorage xs= new XMLStorage(f,p.getXML(),ro);
		  }
		 
		/*LoadStorage ls=new LoadStorage();
		ls.LoadStoredXML("pruebaXML.txt");*/

	}

}
