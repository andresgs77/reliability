package rdfReliability;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import checklistStorage.CheckedRule;

@XmlRootElement
@XmlType (propOrder={"date","evalresultclass","completeness","stability","reliability","checklistitems"})
public class ItemEvalReliability {
	
	private String date;
	private String evalresultclass;
	private double completeness;
	private double stability;
	private double reliability;
	private List<ItemRule> checklistitems;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEvalresultclass() {
		return evalresultclass;
	}
	public void setEvalresultclass(String evalresultclass) {
		this.evalresultclass = evalresultclass;
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
	public List<ItemRule> getChecklistitems() {
		return checklistitems;
	}
	public void setChecklistitems(List<ItemRule> checklistitems) {
		this.checklistitems = checklistitems;
	}
	
	public void fillRules(ArrayList<CheckedRule> c){
		List<ItemRule> listAux= new ArrayList<ItemRule>();
		for (CheckedRule cr: c){
			ItemRule it=new ItemRule();
			it.setItemlevel(cr.getType());
			it.setItemlabel(cr.getItem());
			it.setItemsatisfied(cr.isChecked());
			
			listAux.add(it);
		}
		
		this.setChecklistitems(listAux);
		
	}

}
