package rdf;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType (propOrder={"relativeAdditions","relativeModifications","relativeRemovals"})
public class Relatives {
	
	private double relativeAdditions;
	private double relativeModifications;
	private double relativeRemovals;
	
	public double getRelativeAdditions() {
		return relativeAdditions;
	}
	public void setRelativeAdditions(double relativeAdditions) {
		this.relativeAdditions = relativeAdditions;
	}
	public double getRelativeModifications() {
		return relativeModifications;
	}
	public void setRelativeModifications(double relativeModifications) {
		this.relativeModifications = relativeModifications;
	}
	public double getRelativeRemovals() {
		return relativeRemovals;
	}
	public void setRelativeRemovals(double relativeRemovals) {
		this.relativeRemovals = relativeRemovals;
	}

}
