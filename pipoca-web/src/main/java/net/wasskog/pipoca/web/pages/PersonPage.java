package net.wasskog.pipoca.web.pages;

import java.util.List;

import net.wasskog.pipoca.web.data.dao.interfaces.PersonDao;
import net.wasskog.pipoca.web.data.dataobjects.Event;
import net.wasskog.pipoca.web.data.dataobjects.Person;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import org.apache.wicket.spring.injection.annot.SpringBean;

public class PersonPage extends WebPage {
	
	@SpringBean
	private PersonDao personDao;
	
	
	public PersonPage(final PageParameters pp)
	{
		Form<Person> personForm = new Form<Person>("personForm", new CompoundPropertyModel<Person>(new Person()));
		personForm.add(new TextField<String>("firstName").setRequired(true));
		personForm.add(new TextField<String>("lastName").setRequired(true));
		
		final WebMarkupContainer wmc = new WebMarkupContainer("listContainer");
		
		wmc.add(new ListView<Person>("list", new PropertyModel<List<Person>>(this, "personDao.findAll")){

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Person> item) {
				Person person = item.getModelObject();
				item.add(new Label("personFirstName", person.getFirstName()));
				item.add(new Label("personLastName", person.getLastName()));
			}
			
		});
		wmc.setOutputMarkupId(true);
		add(wmc);
		
		personForm.add(new AjaxSubmitLink("submit"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Person person = (Person) form.getModelObject();
				Person newPerson = new Person();
				newPerson.setFirstName(person.getFirstName());
				newPerson.setLastName(person.getLastName());
				personDao.save(person);
				target.addComponent(wmc);
			}
		});
		
		
		add(personForm);
		
	}
	

}
