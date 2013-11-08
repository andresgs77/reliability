package rdf;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import control.SnapshotEval;
import control.UserBehaviour;

@XmlRootElement
@XmlType (propOrder={"ro","stabilityValue","listElements","listUsers"})
public class ItemStability {
	
	private String ro;
	private String stabilityValue;
	private List<ItemSnapshot> listElements;
	private List<ItemUser> listUsers;
	
	public String getRo() {
		return ro;
	}
	public void setRo(String ro) {
		this.ro = ro;
	}
	public String getStabilityValue() {
		return stabilityValue;
	}
	public void setStabilityValue(String stabilityValue) {
		this.stabilityValue = stabilityValue;
	}
	public List<ItemSnapshot> getListElements() {
		return listElements;
	}
	public void setListElements(List<ItemSnapshot> listElements) {
		this.listElements = listElements;
	}
	
	public List<ItemUser> getListUsers() {
		return listUsers;
	}
	public void setListUsers(List<ItemUser> listUsers) {
		this.listUsers = listUsers;
	}
	
	public void fillInfo(String name, double val, ArrayList<SnapshotEval> list){
		setRo(name);
		setStabilityValue(Double.toString(val));
		ArrayList<ItemSnapshot> l=new ArrayList<ItemSnapshot>();
		
		for(SnapshotEval s: list){
			ItemSnapshot item=new ItemSnapshot();
			item.setUri(s.getUri());
			item.setValue(Double.toString(s.getValue()));
			item.setDate(s.getDate());
			l.add(item);
		}
		setListElements(l);
	}
	public void fillUsers(ArrayList<UserBehaviour> list) {
		ArrayList<ItemUser> l=new ArrayList<ItemUser>();
		
		for(UserBehaviour u: list){
			ItemUser item=new ItemUser();
			item.setUser(u.getUser());
			item.setProbPositive(u.getProbGood());
			item.setProbNegative(u.getProbBad());
			item.setImpactPositive(u.getPositiveImpact());
			item.setImpactNegative(u.getNegativeImpact());
			l.add(item);
		}
		setListUsers(l);
	}
	

}
