package rdf;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import controlAnalytics.IsolatedAnalytics;

@XmlRootElement
@XmlType (propOrder={"uri","user","totals","relatives","quantityImpact"})
public class ItemSnapshotAnal {
	
	private String uri;
	private String user;
	private Totals totals;
	private Relatives relatives;
	private double quantityImpact;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
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
	
	public void fillInfo(IsolatedAnalytics s) {
		uri=s.getUri();
		user=s.getAuthor();
		quantityImpact=s.getQuantityImpact();
		
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
