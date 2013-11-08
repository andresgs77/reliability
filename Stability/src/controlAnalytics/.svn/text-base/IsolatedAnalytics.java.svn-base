package controlAnalytics;

import java.util.Scanner;

import snapshots.EndPoint;
import snapshots.Queries;
import snapshots.VarExtractor;

public class IsolatedAnalytics {
	
	private String uri;
	private String author;
	private double totalChanges;
	private int totalAdditions;
	private int totalModifications;
	private int totalRemovals;
	
	private double quantityImpact;
	
	private double relativeAdditions;
	private double relativeModifications;
	private double relativeRemovals;
	
	public IsolatedAnalytics(String url) {
		uri=url;
		totalChanges=0;
	}

	public void calculateChanges(EndPoint endPoint, Queries query, VarExtractor extractor) {
		//calcula additions
		totalAdditions=queryChanges(endPoint,query,extractor,"Addition");
		//calcula modifications
		totalModifications=queryChanges(endPoint,query,extractor,"Modification");
		//calcula removals
		totalRemovals=queryChanges(endPoint,query,extractor,"Removal");
		//cambios totales
		totalChanges=totalAdditions+totalModifications+totalRemovals;
		//calcula relativos
		if (totalChanges!=0){
		relativeAdditions=totalAdditions/totalChanges;
		relativeModifications=totalModifications/totalChanges;
		relativeRemovals=totalRemovals/totalChanges;
		}
		
		//metemos al autor
		fillAuthor(endPoint,query,extractor);
	}

	private void fillAuthor(EndPoint endPoint, Queries query,
			VarExtractor extractor) {
		String rdf = endPoint.runQuery(query.getSnapshotAuthor(uri));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "author")) {
					line = scanner.nextLine();
					String author= extractor.complexExtract(line,"literal");
					this.author=author;
				}
			}
		}
	}

	private int queryChanges(EndPoint endPoint, Queries query,
			VarExtractor extractor, String type) {
		int value=0;
		String rdf = endPoint.runQuery(query.getSnapshotNumChanges(uri,type));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "changes")) {
					line = scanner.nextLine();
					String change= extractor.DatatypeIntegerExtract(line);
					value= Integer.parseInt(change);
				}
			}
		}
		return value;
	}

	
	//// GETTERS ////////////////////////////
	public String getUri() {
		return uri;
	}

	public String getAuthor() {
		return author;
	}

	public double getTotalChanges() {
		return totalChanges;
	}

	public int getTotalAdditions() {
		return totalAdditions;
	}

	public int getTotalModifications() {
		return totalModifications;
	}

	public int getTotalRemovals() {
		return totalRemovals;
	}

	public double getRelativeAdditions() {
		return relativeAdditions;
	}

	public double getRelativeModifications() {
		return relativeModifications;
	}

	public double getRelativeRemovals() {
		return relativeRemovals;
	}
	
	public double getQuantityImpact() {
		return quantityImpact;
	}
	////////////////////////////////////////

	public void setQuantityImpact(double totalChanges) {
		quantityImpact=0;
		if (totalChanges!=0)
		quantityImpact=this.totalChanges/totalChanges;
	}
}
