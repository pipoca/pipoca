package net.wasskog.pipoca.web.data.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import net.wasskog.pipoca.web.data.dao.interfaces.PersonDao;
import net.wasskog.pipoca.web.data.dataobjects.Person;

public class PersonDaoJPAImp extends AbstractDaoJPAImpl<Person> implements PersonDao {

	public PersonDaoJPAImp() {
		super(Person.class);
	}	
	
	@Transactional
	public List<Person> findAll() {
		return getJpaTemplate().execute(new JpaCallback<List<Person>>() {
			public List<Person> doInJpa(EntityManager em) throws PersistenceException {
				TypedQuery<Person> query = em.createQuery("select p from Person p", Person.class);
				return query.getResultList();
			}
		});
	}

	@Transactional
	public int countAll() {
		return getJpaTemplate().execute(new JpaCallback<Integer>() {
			public Integer doInJpa(EntityManager em) throws PersistenceException {
				TypedQuery<Long> query = em.createQuery("select count (p) from Person p", Long.class);
				return (query.getSingleResult()).intValue();
			}
		});

	}

}
