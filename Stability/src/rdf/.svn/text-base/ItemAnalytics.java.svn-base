package rdf;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import controlAnalytics.GenerateAnalytics;

@XmlRootElement
@XmlType (propOrder={"liveRO","totals","relatives","snapshots","users"})
public class ItemAnalytics {
	
	private String liveRO;
	private Totals totals;
	private Relatives relatives;
	private ListSnapshotsAnal snapshots;
	private ListUsersAnal users;

	public String getLiveRO() {
		return liveRO;
	}
	public void setLiveRO(String liveRO) {
		this.liveRO = liveRO;
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
	
	public ListSnapshotsAnal getSnapshots() {
		return snapshots;
	}
	public void setSnapshots(ListSnapshotsAnal snapshots) {
		this.snapshots = snapshots;
	}
	
	public ListUsersAnal getUsers() {
		return users;
	}
	public void setUsers(ListUsersAnal users) {
		this.users = users;
	}
	
	
	public void fillLiveROInfo(GenerateAnalytics gA){
		liveRO=gA.getLiveRO();
		totals=new Totals();
		totals.setTotalChanges(gA.getTotalChanges());
		totals.setTotalAdditions(gA.getTotalAdditions());
		totals.setTotalModifications(gA.getTotalModifications());
		totals.setTotalRemovals(gA.getTotalRemovals());
		
		relatives=new Relatives();
		relatives.setRelativeAdditions(gA.getRelativeAdditions());
		relatives.setRelativeModifications(gA.getRelativeModifications());
		relatives.setRelativeRemovals(gA.getRelativeRemovals());
	}
	
	public void fillSnapshotsInfo(GenerateAnalytics gA){
		snapshots=new ListSnapshotsAnal();
		snapshots.fillSnapshots(gA.getSnapshots());
	}
	
	public void fillUsersInfo(GenerateAnalytics gA){
		users=new ListUsersAnal();
		users.fillUsers(gA.getUsers());
	}

}
