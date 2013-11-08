package atom;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.atom.Link;

public class AtomFeedLinkDAO {
	
public List<Link> addLinks(String roUri) {
		String relService="http://sandbox.wf4ever-project.org/decayMonitoring/rest/getReliability?RO=";
		String relVisual="http://sandbox.wf4ever-project.org/decayMonitoring/visual.html?id=";
		List<Link> links = new ArrayList<Link>();
		Link service=new Link();
	    service.setTitle("Reliability Service");
	    service.setHref(relService+roUri);
	    service.setRel("complete_trace");
	    Link visual=new Link();
	    visual.setTitle("Monitoring Tool");
	    visual.setHref(relVisual+roUri);
	    visual.setRel("user_interface");
	    links.add(service);
	    links.add(visual);
		
		return links;
	}
	

}
