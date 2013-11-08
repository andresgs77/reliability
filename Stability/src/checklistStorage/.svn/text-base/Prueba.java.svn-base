package checklistStorage;

import java.io.File;

public class Prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String ro= "http://sandbox.wf4ever-project.org/rodl/ROs/golden-exemplar-gk/";
		//String ro = "http://sandbox.wf4ever-project.org/rodl/ROs/Y2Demo-test/";
		/*
		 * 	http://sandbox.wf4ever-project.org/rodl/ROs/Y2DemoStep4/
			http://sandbox.wf4ever-project.org/rodl/ROs/Y2DemoStep7/
			http://sandbox.wf4ever-project.org/rodl/ROs/Y2DemoStep10/
			http://sandbox.wf4ever-project.org/rodl/ROs/Y2DemoStep13/
			http://sandbox.wf4ever-project.org/rodl/ROs/Y2DemoStep19/
			http://sandbox.wf4ever-project.org/rodl/ROs/Y2DemoStep27/
		 */
		
		String ro = "http://sandbox.wf4ever-project.org/rodl/ROs/Y2DemoStep27/";
		File f = new File("WebContent/traceDemo07LongTermnnn.txt");
		/*ChecklistParser p= new ChecklistParser(ro); 
		  if(f.exists()){
			  FileStorage fs = new FileStorage();
			  fs.add(f,p.getEval());
		  }else{
			  FileStorage fs= new FileStorage(f,p.getEval(),ro);
		  }*/
		int min=6;
		int max=50;
		int i=min;
		    try {
		        while (i<max) {
		        	ChecklistParser p= new ChecklistParser(ro,null,""); 
		  		  if(f.exists()){
		  			  FileStorage fs = new FileStorage();
		  			  fs.add(f,p.getEval());
		  		  }else{
		  			  FileStorage fs= new FileStorage(f,p.getEval(),ro);
		  		  }
		            //Thread.sleep(61 * 1000);
		            i++;
		            System.out.println(i);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	
	}

}
