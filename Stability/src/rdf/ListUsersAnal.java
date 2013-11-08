package rdf;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import controlAnalytics.UserAnalytics;

@XmlRootElement
@XmlType (propOrder={"user"})
public class ListUsersAnal {

	private List<ItemUserAnal> user;
	
	public List<ItemUserAnal> getUser() {
		return user;
	}

	public void setUser(List<ItemUserAnal> user) {
		this.user = user;
	}


	public void fillUsers(ArrayList<UserAnalytics> us) {
		ArrayList<ItemUserAnal> list=new ArrayList<ItemUserAnal>();
		for (UserAnalytics u: us){
			ItemUserAnal it=new ItemUserAnal();
			it.fillInfo(u);
			list.add(it);
		}
		setUser(list);
	}
}
