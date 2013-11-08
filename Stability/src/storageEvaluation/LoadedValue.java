package storageEvaluation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import checklistStorage.CheckedRule;

public class LoadedValue {
	
	private Calendar dateCal;
	private String evalClass;
	private double completeness;
	private ArrayList<CheckedRule> rules;
	
	
	public LoadedValue(){
		rules=new ArrayList<CheckedRule>();
	}
	
	
	public ArrayList<CheckedRule> getRules() {
		return rules;
	}


	public void setCompleteness(double completeness) {
		this.completeness=completeness;
	}


	public String getEvalClass() {
		return evalClass;
	}


	public void setEvalClass(String evalClass) {
		this.evalClass=evalClass;
	}


	public void setDate(String dateString) {
		String[] d=dateString.split(",");
		dateCal = new GregorianCalendar();
		dateCal.set(Calendar.YEAR, Integer.parseInt(d[0]));
		dateCal.set(Calendar.MONTH, Integer.parseInt(d[1])-1);
		dateCal.set(Calendar.DATE, Integer.parseInt(d[2]));
		dateCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(d[3]));
		dateCal.set(Calendar.MINUTE, Integer.parseInt(d[4]));
	}


	public void addRule(CheckedRule c) {
		rules.add(c);
	}


	public Calendar getDate() {
		return dateCal;
	}


	public double getCompleteness() {
		return completeness;
	}

}
