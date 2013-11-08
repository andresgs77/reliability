package atom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import storageEvaluation.Evaluation;
import checklistStorage.CheckedRule;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Link;

public class AtomFeedEntryDAO {

	public List<Entry> addEntries(String roUri, ArrayList<Evaluation> evals) {
		
		List<Entry> entries = new ArrayList<Entry>();
		Date d= Calendar.getInstance().getTime();
		if (evals!=null){
		for (int i=0; i<evals.size(); i++){
			Evaluation ev=evals.get(i);
			Entry e=new Entry();
			
			if (i==0)
				e.setTitle("First RO evaluation results of the time interval");
			else{
				if (ev.getReliability()<=evals.get(i-1).getReliability())
					e.setTitle("Oops! RO Reliability has Decreased...");
				else e.setTitle("Good! RO Reliability has Increased!");
			}
			String year=Integer.toString(ev.getDate().get(Calendar.YEAR));
			String month=Integer.toString(ev.getDate().get(Calendar.MONTH));
			String day=Integer.toString(ev.getDate().get(Calendar.DAY_OF_MONTH));
			
			Link link=new Link();
			link.setHref(roUri);
			link.setTitle("Description for");
			link.setRel("http://www.openarchives.org/ore/terms/describes");
			Link link2=new Link();
			link2.setHref("http://sandbox.wf4ever-project.org/decayMonitoring");
			link2.setTitle("RODL");
			link2.setRel("http://purl.org/dc/terms/source");
			List<Link> links = new ArrayList<Link>();
			links.add(link);
			links.add(link2);
			e.setOtherLinks(links);
			
			String[] idAux=roUri.split("/");
			String id= idAux[idAux.length-1]+":"+year+":"+month+":"+day;
			e.setId(id);
			e.setPublished(ev.getDate().getTime());
			e.setUpdated(d);
			List<Content> contents= new ArrayList<Content>();
			Content c= new Content();
			c.setType("html");
			String cont="";
			cont=cont+"Completeness evaluates to which extent a RO satisfies the requirements specified in a checklist.<br>";
			cont=cont+"Stability measures the ability of a RO to preserve its properties (Completeness) through time.<br>";
			cont=cont+"Reliability combines Completeness and Stability to capture the expected behaviour of a RO and its reliability in the future.<br><br>";
			if (i>0){
			cont=cont+"Completeness[0,1]="+ev.getCompleteness()+"&nbsp&nbsp&nbsp(previous value="+evals.get(i-1).getCompleteness()+")<br>";
			cont=cont+"Stability[0,1]="+ev.getStability()+"&nbsp&nbsp&nbsp(previous value="+evals.get(i-1).getStability()+")<br>";
			cont=cont+"Reliability[0,1]="+ev.getReliability()+"&nbsp&nbsp&nbsp(previous value="+evals.get(i-1).getReliability()+")<br>";
			}
			else{
				cont=cont+"Completeness[0,1]="+ev.getCompleteness()+"<br>";
				cont=cont+"Stability[0,1]="+ev.getStability()+"<br>";
				cont=cont+"Reliability[0,1]="+ev.getReliability()+"<br>";
			}
			cont=cont+"Checklist Results:<br><ol>";
			for (CheckedRule rule: ev.getRules()){
				cont=cont+"<li>"+rule.getItem()+" (This is a \""+rule.getType()+"\" requirement. Satisfied?: "+rule.getCheck()+")</li>";
			}
			cont=cont+"</ol>";
			c.setValue(cont);
			contents.add(c);
			e.setContents(contents);
			/*Content sum= new Content();
			sum.setValue(cont);*/
			e.setSummary(c);
			
			//prueba categories
			/*List<Category> categories= new ArrayList<Category>();
			Category c= new Category();
			c.setTerm("Stability");
			c.setLabel("99");
			categories.add(c);
			e.setCategories(categories);*/
			//
			entries.add(e);
		}//end for
		}//end if
		
		return entries;
	}
	

}
