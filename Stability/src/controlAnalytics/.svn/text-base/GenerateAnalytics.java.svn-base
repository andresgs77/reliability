package controlAnalytics;

import java.util.ArrayList;
import java.util.Scanner;

import snapshots.EndPoint;
import snapshots.Queries;
import snapshots.VarExtractor;

public class GenerateAnalytics {
	//Primero de todo capturar el número de cambios por snapshot.
	//Para eso vemos cuáles son los snapshots. Los recorremos, y obtenemos
	//los cambios de cada tipo. Con esa info podemos tener totales y relativos.
	private String liveRO;
	private ArrayList<IsolatedAnalytics> snapshots;
	private ArrayList<UserAnalytics> users;
	private double totalChanges;
	private int totalAdditions;
	private int totalModifications;
	private int totalRemovals;
	private double relativeAdditions;
	private double relativeModifications;
	private double relativeRemovals;
	
	private EndPoint endPoint;
	private Queries query;
	private VarExtractor extractor;
	
	public GenerateAnalytics(String liveRO){
		initExtractors();
		this.liveRO=liveRO;
		//obtenemos lista de snapshots
		initSnapshots();
		//recorremos lista de snapshots y calculamos sus modificaciones
		runSnapshots();
		//generamos info sobre el RO en general
		analyzeRO();
		//analizamos los usuarios
		analyzeUsers();
		
		//ESTO NO LO HACEMOS XQ NO APORTA NADA.
		//frecuencia de snapshots
		//básica fecha creación wf y fecha actual
		//analyzeFrequency();
		//compleja fecha desde el primer al último snapshot
		
		/*
		//prueba pinta
		System.out.println("RO:");
		System.out.println("liveRO= " +liveRO);
		System.out.println("liveRO TC= " +totalChanges);
		System.out.println("liveRO TA= " +totalAdditions);
		System.out.println("liveRO TM= " +totalModifications);
		System.out.println("liveRO TR= " +totalRemovals);
		System.out.println("liveRO RA= " +relativeAdditions);
		System.out.println("liveRO RM= " +relativeModifications);
		System.out.println("liveRO RR= " +relativeRemovals);
		System.out.println();
		System.out.println("Snapshots:");
		for(IsolatedAnalytics s: snapshots){
			System.out.println("snapshot= " +s.getUri());
			System.out.println("author= " +s.getAuthor());
			System.out.println("TC= " +s.getTotalChanges());
			System.out.println("TA= " +s.getTotalAdditions());
			System.out.println("TM= " +s.getTotalModifications());
			System.out.println("TR= " +s.getTotalRemovals());
			System.out.println("RA= " +s.getRelativeAdditions());
			System.out.println("RM= " +s.getRelativeModifications());
			System.out.println("RR= " +s.getRelativeRemovals());
			System.out.println("snapshot impact= " +s.getQuantityImpact());
			System.out.println();
		}
		System.out.println();
		System.out.println("Users:");
		for(UserAnalytics s: users){
			System.out.println("author= " +s.getUser());
			System.out.println("TC= " +s.getTotalChanges());
			System.out.println("TA= " +s.getTotalAdditions());
			System.out.println("TM= " +s.getTotalModifications());
			System.out.println("TR= " +s.getTotalRemovals());
			System.out.println("RA= " +s.getRelativeAdditions());
			System.out.println("RM= " +s.getRelativeModifications());
			System.out.println("RR= " +s.getRelativeRemovals());
			System.out.println("user impact= " +s.getUserChangesImpact());
			System.out.println("snapshots= " +s.getUserSnapshotsImpact());
			System.out.println();
		}
		*/
	}


	/*private void analyzeFrequency() {
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date=getRODate();
		Calendar actual = Calendar.getInstance();
		actual.getTime();
		Calendar roCreated = Calendar.getInstance();
		insertDate(date,roCreated);
		
		
	}
	}*/


	private String getRODate() {
		String date="";
		String rdf = endPoint.runQuery(query.getSnapshotAuthor(liveRO));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "date")) {
					line = scanner.nextLine();
					date= extractor.complexExtract(line,"literal");
				}
			}
		}
		return date;
	}


	private void analyzeUsers() {
		String author="";
		users= new ArrayList<UserAnalytics>();
		for(IsolatedAnalytics s: snapshots){
			author=s.getAuthor();
			if(!existsAuthor(author)){
				UserAnalytics newUser= new UserAnalytics(author,snapshots.size());
				users.add(newUser);
				}
			updateUser(author,s);
		}
		
	}


	private void updateUser(String author, IsolatedAnalytics s) {
		boolean found=false;
		int u=0;
		while (!found && u<users.size()){
			if(users.get(u).getUser().equals(author))found=true;
			else u++;
		}
		users.get(u).update(s);
	}


	private boolean existsAuthor(String author) {
		boolean exists=false;
		int u=0;
		while(!exists && u<users.size()){
			if (users.get(u).getUser().equals(author))exists=true;
			u++;
		}
		return exists;
	}


	private void analyzeRO() {
		totalChanges=0;
		for(IsolatedAnalytics s: snapshots){
			totalAdditions=totalAdditions+s.getTotalAdditions();
			totalModifications=totalModifications+s.getTotalModifications();
			totalRemovals=totalRemovals+s.getTotalRemovals();
		}
		totalChanges=totalAdditions+totalModifications+totalRemovals;
		if (totalChanges!=0){
			relativeAdditions=totalAdditions/totalChanges;
			relativeModifications=totalModifications/totalChanges;
			relativeRemovals=totalRemovals/totalChanges;
		}
		for(IsolatedAnalytics s: snapshots){
			s.setQuantityImpact(totalChanges);
		}
		
	}


	private void initExtractors() {
		extractor=new VarExtractor();
		query=new Queries();
		endPoint=new EndPoint();
	}

	private void initSnapshots() {
		snapshots=new ArrayList<IsolatedAnalytics>();
		String rdf = endPoint.runQuery(query.getSnapshots(liveRO));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (extractor.checkAppereance(line, "snapshot")) {
					line = scanner.nextLine();
					String url= extractor.complexExtract(line, "uri");
					IsolatedAnalytics anal=new IsolatedAnalytics(url);
					snapshots.add(anal);
				}
			}
		}
	}
	
	private void runSnapshots() {
		for(IsolatedAnalytics s: snapshots){
			s.calculateChanges(endPoint,query,extractor);
		}
	}


	////// GETTERS /////////////////////////
	public String getLiveRO() {
		return liveRO;
	}


	public ArrayList<IsolatedAnalytics> getSnapshots() {
		return snapshots;
	}


	public ArrayList<UserAnalytics> getUsers() {
		return users;
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
	

}
