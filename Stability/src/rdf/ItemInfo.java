package rdf;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType (propOrder={"ro","author","date","additions","modifications","removals"})
public class ItemInfo {
	
	private String ro;
	private String author;
	private String date;
	private ChangesList modifications;
	private ChangesList additions;
	private ChangesList removals;
	
	public String getRo() {
		return ro;
	}
	public void setRo(String ro) {
		this.ro = ro;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ChangesList getModifications() {
		return modifications;
	}
	public void setModifications(ChangesList modifications) {
		this.modifications = modifications;
	}
	public ChangesList getAdditions() {
		return additions;
	}
	public void setAdditions(ChangesList additions) {
		this.additions = additions;
	}
	public ChangesList getRemovals() {
		return removals;
	}
	public void setRemovals(ChangesList removals) {
		this.removals = removals;
	}
	
	
	//////////////////
	
	public void fillInfo(String ro, String author, String date){
		this.ro=ro;
		this.author=author;
		this.date=date;
		//
		additions=new ChangesList();
		modifications=new ChangesList();
		removals=new ChangesList();
		
	}
	
	public void fillAdditions(ArrayList<String> adds){
		if(adds!=null)
		additions.setResource(adds);
	}
	
	public void fillModifications(ArrayList<String> mods){
		if(mods!=null)
		modifications.setResource(mods);
	}
	
	public void fillRemovals(ArrayList<String> rems){
		if(rems!=null)
		removals.setResource(rems);
	}
	

}
