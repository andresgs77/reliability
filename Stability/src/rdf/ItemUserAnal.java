package rdf;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import controlAnalytics.UserAnalytics;

@XmlRootElement
@XmlType (propOrder={"user","totals","relatives","quantityImpact","numSnapshots"})
public class ItemUserAnal {
	
	private String user;
	private Totals totals;
	private Relatives relatives;
	private double quantityImpact;
	private double numSnapshots;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Totals getTotals() {
		return totals;
	}
	public void setTotals(Totals totals) {
		this.totals = totals;
	}
	public Relatives getRelatives() {
		return relatives;
	}
	public void setRelatives(Relatives relatives) {
		this.relatives = relatives;
	}
	public double getQuantityImpact() {
		return quantityImpact;
	}
	public void setQuantityImpact(double quantityImpact) {
		this.quantityImpact = quantityImpact;
	}
	public double getNumSnapshots() {
		return numSnapshots;
	}
	public void setNumSnapshots(double numSnapshots) {
		this.numSnapshots = numSnapshots;
	}
	
	public void fillInfo(UserAnalytics s) {
		user=s.getUser();
		quantityImpact=s.getUserChangesImpact();
		numSnapshots=s.getUserSnapshotsImpact();
		
		totals=new Totals();
		totals.setTotalChanges(s.getTotalChanges());
		totals.setTotalAdditions(s.getTotalAdditions());
		totals.setTotalModifications(s.getTotalModifications());
		totals.setTotalRemovals(s.getTotalRemovals());
		
		relatives=new Relatives();
		relatives.setRelativeAdditions(s.getRelativeAdditions());
		relatives.setRelativeModifications(s.getRelativeModifications());
		relatives.setRelativeRemovals(s.getRelativeRemovals());
	}

}
