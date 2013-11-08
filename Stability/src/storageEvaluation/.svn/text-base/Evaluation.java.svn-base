package storageEvaluation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import checklistStorage.CheckedRule;

public class Evaluation {
	
	private Calendar date;
	private String evalClass;
	private double completeness;
	private double stability;
	private double reliability;
	private ArrayList<CheckedRule> rules;
	
	
	public Evaluation(){
		rules=new ArrayList<CheckedRule>();
	}
	
	
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar calendar) {
		Calendar c= new GregorianCalendar();
		c.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		c.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		this.date = c;
	}
	public String getEvalClass() {
		return evalClass;
	}
	public void setEvalClass(String evalClass) {
		this.evalClass = evalClass;
	}
	public double getCompleteness() {
		return completeness;
	}
	public void setCompleteness(double completeness) {
		this.completeness = completeness;
	}
	public double getStability() {
		return stability;
	}
	public void setStability(double stability) {
		this.stability = stability;
	}
	public double getReliability() {
		return reliability;
	}
	public void setReliability(double reliability) {
		this.reliability = reliability;
	}
	public ArrayList<CheckedRule> getRules() {
		return rules;
	}
	public void addRule(CheckedRule rule) {
		rules.add(rule);
	}

	public void setRules(ArrayList<CheckedRule> rules2) {
		rules=rules2;
		
	}

}
