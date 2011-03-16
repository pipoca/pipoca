package net.wasskog.pipoca.web;

import net.wasskog.pipoca.web.data.dao.interfaces.EventDao;
import net.wasskog.pipoca.web.data.dataobjects.Event;

/**s
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class DataInitialiser {
	
	private static String[] dummyTitles = {"Wicket Event", "Party", "Breakfast At Tiffany's", "Holiday"};
	private static String[] dummyLocations = {"London", "Paris", "Pub", "New York"};
	
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	private EventDao eventDao;
	
	public void dataInit()
	{
		for(int i = 0; i < 10; i++)
		{
			Event event = new Event();
			event.setTitle(dummyTitles[(int)(Math.random() * dummyTitles.length)]);
			event.setLocation(dummyLocations[(int)(Math.random() * dummyLocations.length)]);
			eventDao.save(event);
		}
	}

}
