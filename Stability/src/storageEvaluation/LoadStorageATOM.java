package storageEvaluation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import checklistStorage.CheckedRule;

public class LoadStorageATOM {
	
	private ArrayList<LoadedValue> evaluations;
	private ArrayList<Evaluation> finalEvaluations;
	private String roName;
	
	//clase encargada de cargar el xml de una traza
	
	public ArrayList<Evaluation> LoadStoredXML(String fileName, Calendar fromDate, Calendar toDate){
		evaluations = new ArrayList<LoadedValue>();
		Scanner scanner;
		try {
			String[] fileNameSplit=fileName.split("/");
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));
			scanner = new Scanner(new FileReader("traces/"+fileNameSplit[fileNameSplit.length-1]+".txt"));
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (line.contains("<eval evalresultclass="))
					analyzeEvaluation(scanner, line);
				else if (line.contains("<rouri>"))
					roName=(line.substring(line.indexOf(">")+1, line.lastIndexOf("<")));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		//calculate completeness for loaded values
		for(int i=0; i<evaluations.size(); i++)
			evaluations.get(i).setCompleteness(calulateCompleteness(evaluations.get(i)));
		
		//generate the whole trace
		CalcEvaluationATOM calc=new CalcEvaluationATOM(evaluations);
		
		try {
			finalEvaluations=calc.getCalculations(fromDate,toDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalEvaluations;
		//printPrueba();
		
	}

	private void printPrueba() {
		for (int i=0; i<finalEvaluations.size(); i++){
			System.out.println(finalEvaluations.get(i).getDate().getTime());
			System.out.println(finalEvaluations.get(i).getCompleteness());
			System.out.println(finalEvaluations.get(i).getStability());
			System.out.println(finalEvaluations.get(i).getReliability());
			System.out.println();
		}
		
	}

	private double calulateCompleteness(LoadedValue loadedValue) {
		double value=0;
		
		double mustTotal=0;
		double shouldTotal=0;
		double mayTotal=0;
		
		double mustOk=0;
		double shouldOk=0;
		double mayOk=0;
		
		for (CheckedRule c: loadedValue.getRules()){
			boolean pass= c.isChecked();
			
			if (c.getType().equals("must")){
				if (pass) mustOk++;
				mustTotal++;
			}
			if (c.getType().equals("should")){
				if (!pass) shouldOk++;
				shouldTotal++;
			}
			if (c.getType().equals("may")){
				if (!pass) mayOk++;
				mayTotal++;
			}
		}
		
		double percentageMust=1;
		double percentageShould=1;
		double percentageMay=1;
		
		if (mustTotal>0) percentageMust=mustOk/mustTotal;
		if (shouldTotal>0) percentageShould=shouldOk/shouldTotal;
		if (mayTotal>0) percentageMay=mayOk/mayTotal;
		
		String evalClass =loadedValue.getEvalClass();
		double positive=0;
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
		return positive;
	}

	private void analyzeEvaluation(Scanner scanner, String line) {
		LoadedValue evaluation = new LoadedValue();
		//resultado global de la evaluación
		String result=line.substring(line.indexOf("\"")+1, line.lastIndexOf("\""));
		evaluation.setEvalClass(result);
		
		//resultado fecha
		line=scanner.nextLine();
		String date=line.substring(line.indexOf(">")+1, line.lastIndexOf("<"));
		evaluation.setDate(date);
		
		//evaluar reglas
		analyzeRules(scanner, line, evaluation);
		
		evaluations.add(evaluation);
		
	}



	private void analyzeRules(Scanner scanner, String line,
			LoadedValue evaluation) {
		
		while (scanner.hasNextLine() & !line.contains("</checklistitems>")) {
			line = scanner.nextLine();
			if (line.contains("<checklistitem "))
				analyzeRule(line, evaluation);
		}
		
		
	}

	private void analyzeRule(String line, LoadedValue evaluation) {
		String item=line.substring(line.indexOf(">")+1, line.lastIndexOf("<"));
		String pass="";
		if (line.contains("\"true\""))pass="true"; else pass="false";
		
		String[] types=line.split("\"");
		String type=types[1];
		CheckedRule c= new CheckedRule(item, pass, type);
		
		evaluation.addRule(c);
		
	}

	public String getRoName() {
		return roName;
	}

	public ArrayList<String> getAllROs() {
		  String path = "traces/"; 
		  ArrayList<String> listFiles = new ArrayList<String>();
		  
		  String files;
		  File folder = new File(path);
		  File[] listOfFiles = folder.listFiles(); 
		 
		  for (int i = 0; i < listOfFiles.length; i++) 
		  {
		 
		   if (listOfFiles[i].isFile()) 
		   {
		   files = listOfFiles[i].getName();
		   listFiles.add("http://sandbox.wf4ever-project.org/rodl/ROs/"+files.substring(0,files.lastIndexOf("."))+"/");
		      }
		  }
		return listFiles;
	}




}
