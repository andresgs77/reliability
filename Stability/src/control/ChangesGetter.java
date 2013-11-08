package control;

import java.util.ArrayList;
import java.util.Scanner;

import snapshots.EndPoint;
import snapshots.Queries;
import snapshots.VarExtractor;

public class ChangesGetter {

	private String ro;
	private String date;
	private String author;
	private ArrayList<String> additions;
	private ArrayList<String> modifications;
	private ArrayList<String> removals;
	private boolean snapshot;
	
	private VarExtractor extractor;
	private Queries q;
	private EndPoint ep;
	
	public ChangesGetter(String ro){
		//inits
		extractor=new VarExtractor();
		q=new Queries();
		ep=new EndPoint();
		additions=new ArrayList<String>();
		modifications=new ArrayList<String>();
		removals=new ArrayList<String>();
		
		//start filling the information
		this.ro=ro;
		snapshot=false; //suponemos que es un archived
		isSnapshot(); //comprobamos si es snapshot
		fillDate();
		fillAuthor();

		//fill the additions
		fillChanges(additions,"Addition");
		//fill the modifications
		fillChanges(modifications,"Modification");
		//fill the removals
		fillChanges(removals,"Removal");
		
	}


	private void isSnapshot() {
		String rdf = ep.runQuery(q.isSnapshot(ro));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (line.indexOf("true")!=-1) {
					snapshot=true;
				}
			}
		}
		
	}


	private void fillChanges(ArrayList<String> list, String type) {
		String rdf = ep.runQuery(q.getSnapshotChanges(ro,type));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "resource")) {
					line = scanner.nextLine();
					String change= extractor.SimpleExtract(line);
					list.add(change);
				}
			}
		}
	}

	private void fillAuthor() {
		String rdf = "";
		if (snapshot) rdf = ep.runQuery(q.getSnapshotAuthor(ro));
			else rdf = ep.runQuery(q.getArchiveAuthor(ro));
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

	private void fillDate() {
		String rdf = "";
		if (snapshot) rdf = ep.runQuery(q.getSnapshotTime(ro));
			else rdf = ep.runQuery(q.getArchiveTime(ro));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "when")) {
					line = scanner.nextLine();
					String fecha= extractor.DatatypeExtract(line);
					this.date=fecha;
				}
			}
		}
	}


	public String getRo() {
		return ro;
	}


	public String getDate() {
		return date;
	}


	public String getAuthor() {
		return author;
	}


	public ArrayList<String> getAdditions() {
		return additions;
	}


	public ArrayList<String> getModifications() {
		return modifications;
	}


	public ArrayList<String> getRemovals() {
		return removals;
	}
	
	
	
}
