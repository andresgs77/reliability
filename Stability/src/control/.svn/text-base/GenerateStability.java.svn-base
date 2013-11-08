package control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import snapshots.EndPoint;
import snapshots.Queries;
import snapshots.VarExtractor;
import checklists.ChecklistEval;

public class GenerateStability {
	

	private String ro;
	private String minim;
	private String purpose;
	private double value;
	private ArrayList<SnapshotEval> snapshots;
	private ArrayList<UserBehaviour> users;
	private EndPoint endPoint;
	private Queries query;
	private VarExtractor extractor;
	
	
	public GenerateStability(String ro,String minim, String purpose){
		extractor=new VarExtractor();
		query=new Queries();
		endPoint=new EndPoint();
		this.ro=ro;
		//this.ro="http://www.wf4ever-project.com/fakeRO";
		this.minim=minim;
		this.purpose=purpose;
		snapshots=new ArrayList<SnapshotEval>();
		users=new ArrayList<UserBehaviour>();
	}
	
	//aquí debería meterse la info del minim y el checklist que se va a usar
	public void evaluate(){
		//de momento lo generamos random
		//hacer petición a minim (necesitamos RO, minim y purpose)
		//fillSnapshotsRandom();
		//fillSnapshotsHandCrafted();
		fillSnapshots();
		calculateValue();
		updateUsers();
	}
	
	private void updateUsers() {
		for (int i=1; i<snapshots.size(); i++){
			updateUser(snapshots.get(i).getUser(), snapshots.get(i-1).getValue()-snapshots.get(i).getValue());
		}
		
		for (UserBehaviour u: users){
			u.updateStatistics(snapshots.size());
		}
	}

	private void updateUser(String user, double d) {
		boolean found=false;
		int num=0;
		while (!found && num<users.size()){
			if (users.get(num).getUser().equals(user))
				found=true;
			else
				num++;
		}
		
		UserBehaviour u;
		if (!found) {
			u=new UserBehaviour(user);
			users.add(u);
			}
		else u=users.get(num);
		
		u.updateNums(d);	
	}

	private void fillSnapshots() {
		ChecklistEval chk=new ChecklistEval();
		String rdf = endPoint.runQuery(query.getSnapshots(ro));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "snapshot")) {
					line = scanner.nextLine();
					String url= extractor.complexExtract(line, "uri");
					addCase(chk,url,minim,purpose);
				}
			}
		}
	}
	

	/*private void fillSnapshotsHandCrafted() {
		ChecklistEval chk=new ChecklistEval();
		addCase(chk,ro,minim,purpose);
		addCase(chk,"http://andros.zoo.ox.ac.uk/aleix/ro-catalogue/v0.1/wf74-repeat-fail/",minim,"Repeatable");
		addCase(chk,"http://andros.zoo.ox.ac.uk/aleix/ro-catalogue/v0.1/wf74-repeat-fail/",minim,"Reviewable");
		
		
	}*/

	private void addCase(ChecklistEval chk, String snapshot, String minim, String purpose) {
		try {
			SnapshotEval s=new SnapshotEval(snapshot);
			// LLAMADA AL CHECKLIST
			//double value=chk.getEvaluation(ro, minim, purpose);
			//s.setValue(value);
			//
			fakeValue(s);
			//METIENDO FECHA FALSA
			//SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			//Calendar c1 = Calendar.getInstance();
			//s.setDate(formatDate.format(c1.getTime()));
			//
			meteFecha(snapshot,s);
			meteAutor(snapshot,s);
			snapshots.add(s);
		} catch (Exception e) {e.printStackTrace();}
		
	}

	private void meteAutor(String snapshot, SnapshotEval s) {
		String rdf = endPoint.runQuery(query.getSnapshotAuthor(snapshot));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "author")) {
					line = scanner.nextLine();
					String author= extractor.complexExtract(line,"literal");
					s.setUser(author);
				}
			}
		}
		
	}

	private void meteFecha(String snapshot, SnapshotEval s) {
		String rdf = endPoint.runQuery(query.getSnapshotTime(snapshot));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "when")) {
					line = scanner.nextLine();
					String fecha= extractor.DatatypeExtract(line);
					s.setDate(fecha);
				}
			}
		}
		
	}

	private void fillSnapshotsRandom() {
		Random r=new Random();
		int numSnapshots=r.nextInt(10)+1;
		String fakeUriRoot="http://www.wf4ever-project.com/fakeRO/snapshot/";
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c1 = Calendar.getInstance(); 
		
		for (int i=0; i<numSnapshots; i++){
			SnapshotEval s=new SnapshotEval(fakeUriRoot+i);
			c1.add(Calendar.DATE,3);//add days
			s.setDate(formatDate.format(c1.getTime()));
			fakeValue(s);
			snapshots.add(s);
		}
		
	}

	private void fakeValue(SnapshotEval s) {
		Random r=new Random();
		double value=r.nextDouble();
		s.setValue(value);
	}
	
	private void calculateValue() {
		double acum=0;
		double media=0;
		int cont=0;
		for(SnapshotEval s:snapshots){
			acum=acum+Math.pow(s.getValue(),2);
			media=media+s.getValue();
			cont++;
		}
		media=media/cont;
		acum=(acum/cont)-Math.pow(media, 2);
		acum=Math.sqrt(acum);
		acum=acum*2;
		this.value=1-acum;
		
	}
	
	public String getRo() {
		return ro;
	}

	public double getValue() {
		return value;
	}

	public ArrayList<SnapshotEval> getSnapshots() {
		return snapshots;
	}
	
	public ArrayList<UserBehaviour> getUsers(){
		return users;
	}

}
