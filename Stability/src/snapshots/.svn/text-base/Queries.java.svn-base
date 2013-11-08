package snapshots;

public class Queries {
	
	private String prefix= 	"PREFIX roevo: <http://purl.org/wf4ever/roevo#> "+
							"PREFIX foaf:<http://xmlns.com/foaf/0.1/> ";
	
	// OJO: cambio temporal, dos 't' en snapshotted at time
	public String getSnapshots(String liveRO){
		return prefix + "select ?snapshot  {?snapshot roevo:isSnapshotOf <"+liveRO+">. ?snapshot roevo:snapshotedAtTime ?when} ORDER BY ?when";
	}
	
	// OJO: cambio temporal, dos 't' en snapshotted at time
	public String getSnapshotTime(String snapshot){
		return prefix + "select ?when {<"+snapshot+"> roevo:snapshotedAtTime ?when}";
	}
	
	public String getArchiveTime(String archive){
		return prefix + "select ?when {<"+archive+"> roevo:archivedAtTime ?when}";
	}
	
	// OJO: cambio temporal, dos 't' en snapshotted by y hemos quitado el Was el principio
	public String getSnapshotAuthor(String snapshot){
		return prefix + "select ?author {<"+snapshot+"> roevo:snapshotedBy ?bnode. ?bnode foaf:name ?author }";
	}
	
	public String getArchiveAuthor(String archive){
		return prefix + "select ?author {<"+archive+"> roevo:archivedBy ?bnode. ?bnode foaf:name ?author }";
	}
	
	public String getSnapshotChanges(String snapshot, String type){
		return prefix + "select ?resource {<"+snapshot+"> roevo:wasChangedBy ?bnode . "+
					"?bnode roevo:hasChange ?bnode2. "+
					"?bnode2 a roevo:"+type+". "+
					"?bnode2 roevo:relatedResource ?resource}";
	}
	
	public String getSnapshotNumChanges(String snapshot, String type){
		return prefix + "select (COUNT(*) as ?changes) {<"+snapshot+"> roevo:wasChangedBy ?bnode . "+
					"?bnode roevo:hasChange ?bnode2. "+
					"?bnode2 a roevo:"+type+".}";
	}
	
	
	public String isSnapshot(String snapshot){
		return prefix + "ASK {<"+snapshot+"> a roevo:SnapshotRO}";
	}
	
	public String isArchived(String archived){
		return prefix + "ASK {<"+archived+"> a roevo:ArchivedRO}";
	}
	
	public String getLiveRoFromArchived(String archived){
		return prefix + "Select ?live where {<"+archived+"> roevo:isArchiveOf ?live}";
	}
	
	public String getLiveRoFromSnapshot(String snapshot){
		return prefix + "Select ?live where {<"+snapshot+"> roevo:isSnapshotOf ?live}";
	}


}
