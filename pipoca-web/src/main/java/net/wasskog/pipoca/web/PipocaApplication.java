package net.wasskog.pipoca.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import net.wasskog.pipoca.web.pages.EventPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 * 
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class PipocaApplication extends WebApplication
{    
	/**
	 * Constructor
	 */
	public PipocaApplication()
	{
	}
	
	protected SpringComponentInjector getSpringInjector()
	{
		return new SpringComponentInjector(this);
	}

	@Override
	protected void init() {
		super.init();

		mountBookmarkablePage("event", EventPage.class);
		
		addComponentInstantiationListener(getSpringInjector());

	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

}
