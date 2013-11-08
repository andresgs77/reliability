package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import storageEvaluation.Evaluation;
import storageEvaluation.LoadStorageATOM;
import atom.AtomFeed;
import atom.AtomFeedEntryDAO;
import atom.AtomFeedLinkDAO;
import atom.AtomFeedTitileBuilder;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.WireFeedOutput;

@Path("/notifications")
public class NotificationResourceB {
	
	 @GET
	 @Produces(MediaType.APPLICATION_ATOM_XML)
	 public Response getAtomFeeds(@QueryParam("ro") String roUri,
	            @QueryParam("from") String from, @QueryParam("to") String to) {
		 
		 AtomFeedEntryDAO entryDAO = new AtomFeedEntryDAO();
		 AtomFeedLinkDAO linksDAO = new AtomFeedLinkDAO();
		 
		 /*String[] fromSplit= from.split(",");
		 String[] toSplit= to.split(",");*/
		 
		 Calendar fromDate=null;
		 if (from!=null){
		 from=from.substring(0,from.indexOf("T")); 
	     String[] fromSplit= from.split("-");
	     fromDate= new GregorianCalendar();
		 fromDate.set(Calendar.YEAR, Integer.parseInt(fromSplit[0]));
		 fromDate.set(Calendar.MONTH, Integer.parseInt(fromSplit[1])-1);
		 fromDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fromSplit[2]));
	     //Date dateFrom = fromDate.getTime();
		 }
	     
		 Calendar toDate=null;;
	     if (to!=null){
	     to=to.substring(0,to.indexOf("T"));
	     String[] toSplit= to.split("-");
		 toDate = new GregorianCalendar();
	     toDate.set(Calendar.YEAR, Integer.parseInt(toSplit[0]));
	     toDate.set(Calendar.MONTH, Integer.parseInt(toSplit[1])-1);
	     toDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(toSplit[2]));
	     //Date dateTo = toDate.getTime();
	     }
	     
	     LoadStorageATOM ls=new LoadStorageATOM();
	     Feed feed = AtomFeed.createNewFeed(AtomFeedTitileBuilder.buildTitle(roUri, fromDate, toDate));
	     List<Entry> completeEntries=new ArrayList<Entry>();
	     if (roUri!=null){
	    	 ArrayList<Evaluation> evs=ls.LoadStoredXML(roUri, fromDate, toDate);
	    	 if (evs!=null){
	    		feed.setAlternateLinks(linksDAO.addLinks(roUri));
	    	 	completeEntries.addAll(entryDAO.addEntries(roUri, evs));
	    	 }
	     }
	     else{
	    	 //listar todos los ros disponibles -> de los archivos mejor
	    	 ArrayList<String> files=ls.getAllROs();
	    	 //recorrerlos y añadir entries
	    	 for(String f: files){
	    		 //System.out.println(f);
	    		 completeEntries.addAll(entryDAO.addEntries(f, ls.LoadStoredXML(f, fromDate, toDate)));
	    		 //System.out.println("ok");
	    	 }
	     }
	     
	     feed.setEntries(completeEntries);
	     //feed.setEntries(entryDAO.addEntries(roUri, ls.LoadStoredXML(roUri, fromDate, toDate)));
	     
	     
	     WireFeedOutput wire = new WireFeedOutput();
	     try {
	            return Response.ok(wire.outputString(feed)).build();
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }

}
