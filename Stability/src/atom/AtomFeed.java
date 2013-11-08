package atom;


import java.util.Calendar;
import java.util.Collections;

import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Person;

public class AtomFeed {
	
	//
    public static final String AUTHOR_EMAIL = "rodl@wf4ever.org";
    //
    public static final String AUTHOR_NAME = "Provided by Wf4ever";
    //
    public static final String FEED_TYPE = "atom_1.0";
   
    
    public static Feed createNewFeed(String title) {
        Feed feed = new Feed();
        feed.setFeedType(FEED_TYPE);
        //feed.setId(id.toString());
        feed.setTitle(title);
        feed.setUpdated(Calendar.getInstance().getTime());
        
        Person author = new Person();
        author.setEmail(AUTHOR_EMAIL);
        author.setName(AUTHOR_NAME);
        feed.setAuthors(Collections.singletonList(author));
        return feed;
    }

}
