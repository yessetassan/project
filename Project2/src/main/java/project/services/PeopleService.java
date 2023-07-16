package project.services;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.models.Book;
import project.models.Person;
import project.repositories.PeopleRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final EntityManager entityManager;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository, EntityManager entityManager) {
        this.peopleRepository = peopleRepository;
        this.entityManager = entityManager;
    }

    public List<Person> all(){
        return peopleRepository.findAll();
    }

    public Person show(int id) {
        return peopleRepository.findById(id);
    }
    @Transactional
    public void update(int id, Person person){
        Session session = entityManager.unwrap(Session.class);
        Person p = session.get(Person.class,id);
        p.setAge(person.getAge());
        p.setName(person.getName());
        p.setEmail(person.getEmail());
        session.update(p);
    }
    @Transactional
    public void add(Person person){
        Session session = entityManager.unwrap(Session.class);
        session.save(person);
    }
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
