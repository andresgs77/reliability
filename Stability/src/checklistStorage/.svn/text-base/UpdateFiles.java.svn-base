package checklistStorage;

import java.io.File;
import java.util.Scanner;

import snapshots.EndPoint;
import snapshots.Queries;

public class UpdateFiles {
	
	private EndPoint endpoint;
	private Queries q;
	
	public UpdateFiles(String ro){
		/*final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);*/
		endpoint=new EndPoint();
		q= new Queries();
		//check if it is a Live or Snapshot/Archived
		// if its a new live RO then we generate a file and evaluate it
		//if it is an archived/snapshot we both update the liveRO and create new file for snapshot
		String type=null;
		String link="";
		
		type=getType(ro);
		
		if (type==null)
			update(ro, type, link);
		else{ 
			//esto significaría que hay un archived o snapshot
			String live = getLiveRO(type,ro);//obtenemos la uri del liveRO
			update(live,type,ro);//actualizamos info del liveRO
			update(ro,null,"");//creamos nuevo docu para el snapshot/archived
		}
	}
	
	private String getLiveRO(String type,String ro) {
		String live="";
		String rdf =null;
		if (type.equals("Snapshot"))
			rdf = endpoint.runQuery(q.getLiveRoFromSnapshot(ro));
		else if (type.equals("Archived"))
			rdf = endpoint.runQuery(q.getLiveRoFromArchived(ro));
		
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (line.contains("<uri>")){
					live=line.substring(line.indexOf("<uri>")+5, line.indexOf("</uri>"));
				}
			}
		}
		
		return live;
	}

	private String getType(String ro) {
		String type=null;
		String rdf = endpoint.runQuery(q.isSnapshot(ro));
		if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (line.indexOf("true")!=-1) {
					type="Snapshot";
				}
			}
		}
		if (type==null){
			rdf = endpoint.runQuery(q.isArchived(ro));
			if (rdf != null) {
			Scanner scanner = new Scanner(rdf);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (line.indexOf("true")!=-1) {
					type="Archived";
				}
			}
		}
		}
		return type;
	}

	//principal
	private void update(String ro, String type,String link){
		String[] aux = ro.split("/");
		String fileName = aux[aux.length - 1];

		File f = new File("C:\\Documents and Settings\\agarrido\\workspace2\\Stability\\WebContent\\Traces\\"+fileName+".txt");

		try {
			//type = null and link="" if its not a snapshot/archived.
			ChecklistParser p = new ChecklistParser(ro, type, link);
			if (f.exists()) {
				FileStorage fs = new FileStorage();
				fs.add(f, p.getEval());
			} else {
				FileStorage fs = new FileStorage(f, p.getEval(), ro);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

}
