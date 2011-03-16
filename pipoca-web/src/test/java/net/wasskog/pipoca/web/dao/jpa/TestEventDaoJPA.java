package net.wasskog.pipoca.web.dao.jpa;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.wasskog.pipoca.web.data.dao.interfaces.EventDao;
import net.wasskog.pipoca.web.data.dataobjects.Event;

/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestEventDaoJPA {

	@Autowired
	private EventDao eventDao;

	protected Event event;

	@Before
	public void startTransaction()
	{
		event = new Event();
		event.setLocation("new Location");
		event.setTitle("new Title");
		event = eventDao.save(event);
	}


	/**
	 * Test method for {@link net.wasskog.data.dao.jpa.EventDaoJPAImp#findAll()}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testFindAll() {
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		Assert.assertEquals(events, eventDao.findAll());
	}

	/**
	 * Test method for {@link net.wasskog.data.dao.jpa.EventDaoJPAImp#countAll()}.
	 */
	@Test @Transactional
	@Rollback
	public void testCountAll() {
		Assert.assertEquals(1, eventDao.countAll());
	}

	/**
	 * Test method for {@link net.wasskog.data.dao.jpa.AbstractDaoJPAImpl#delete(net.wasskog.data.dataobjects.DomainObject)}.
	 */
	@Test @Transactional
	@Rollback
	public void testDelete() {
		eventDao.delete(event);
		Assert.assertEquals(0, eventDao.countAll());
	}

	/**
	 * Test method for {@link net.wasskog.data.dao.jpa.AbstractDaoJPAImpl#load(java.io.Serializable)}.
	 */
	@Test @Transactional
	@Rollback
	public void testLoad() {
		Event event2 = eventDao.load(event.getId());
		Assert.assertEquals(event, event2);
	}

	/**
	 * Test method for {@link net.wasskog.data.dao.jpa.AbstractDaoJPAImpl#save(net.wasskog.data.dataobjects.DomainObject)}.
	 */
	@Test @Transactional
	@Rollback
	public void testSave() {
		//if we have got this far then save works
	}

}
