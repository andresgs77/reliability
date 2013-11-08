package rdfReliability;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import storageEvaluation.Evaluation;

@XmlRootElement
@XmlType (propOrder={"rouri","completeness","stability","reliability","evaluations"})
public class ItemReliability {
	
	private String rouri;
	private double completeness;
	private double stability;
	private double reliability;
	private List<ItemEvalReliability> evaluations;

	public String getRouri() {
		return rouri;
	}

	public void setRouri(String rouri) {
		this.rouri = rouri;
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

	
	public void fillInfo(ArrayList<Evaluation> e, String name) {
		this.setCompleteness(e.get(e.size()-1).getCompleteness());
		this.setStability(e.get(e.size()-1).getStability());
		this.setReliability(e.get(e.size()-1).getReliability());
		this.setRouri(name);
		
		List<ItemEvalReliability> listAux= new ArrayList<ItemEvalReliability>();
		for (Evaluation ev: e){
			ItemEvalReliability it= new ItemEvalReliability();
			it.setCompleteness(ev.getCompleteness());
			it.setStability(ev.getStability());
			it.setReliability(ev.getReliability());
			it.setEvalresultclass(ev.getEvalClass());
			it.fillRules(ev.getRules());
			
			String year=Integer.toString(ev.getDate().get(Calendar.YEAR));
			String month=Integer.toString(ev.getDate().get(Calendar.MONTH));
			String day=Integer.toString(ev.getDate().get(Calendar.DAY_OF_MONTH));
			String hour=Integer.toString(ev.getDate().get(Calendar.HOUR_OF_DAY));
			String minute=Integer.toString(ev.getDate().get(Calendar.MINUTE));
			
			it.setDate(year+","+month+","+day+","+hour+","+minute);
			listAux.add(it);
		}
		this.setEvaluations(listAux);
		
	}

	public List<ItemEvalReliability> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<ItemEvalReliability> evaluations) {
		this.evaluations = evaluations;
	}

}
