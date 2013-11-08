package rdf;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import controlAnalytics.IsolatedAnalytics;

@XmlRootElement
@XmlType (propOrder={"snapshot"})
public class ListSnapshotsAnal {
	
	private List<ItemSnapshotAnal> snapshot;

	public List<ItemSnapshotAnal> getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(List<ItemSnapshotAnal> snapshot) {
		this.snapshot = snapshot;
	}

	public void fillSnapshots(ArrayList<IsolatedAnalytics> snaps) {
		ArrayList<ItemSnapshotAnal> list=new ArrayList<ItemSnapshotAnal>();
		for (IsolatedAnalytics s: snaps){
			ItemSnapshotAnal it=new ItemSnapshotAnal();
			it.fillInfo(s);
			list.add(it);
		}
		setSnapshot(list);
	}
	
	
	
	

}
