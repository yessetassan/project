package project.services;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.models.Book;
import project.models.Person;
import project.repositories.BookRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final EntityManager entityManager;
    @Autowired
    public BookService(BookRepository bookRepository, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;
    }

    public List<Book> findBookByPersonId(int id){
        return bookRepository.findAllByPersonId(id);
    }
    public List<Book> all(){
        return bookRepository.findAll();
    }

    @Transactional
    public void save(Book book) {
        Session session = entityManager.unwrap(Session.class);
        book.setDate(new Date());
        session.save(book);
    }
    public Book show(int id){
        return bookRepository.findById(id);
    }
    @Transactional
    public void update(Book book, int id) {
        Session session = entityManager.unwrap(Session.class);
        Book b = session.get(Book.class, id);
        b.setYear(book.getYear());
        b.setName(book.getName());
        session.update(b);
    }
    @Transactional
    public void update(int id, Person person) {
        Session session = entityManager.unwrap(Session.class);
        Book b = session.get(Book.class, id);
        b.setPerson(person);
        b.setDate(new Date());
        session.update(b);
    }
    @Transactional
    public void delete(int id) {
        Session session = entityManager.unwrap(Session.class);
        Book b = session.get(Book.class, id);
        session.delete(b);
    }

    public List<Book> all2(int page, int books_per_page){
        return bookRepository.findAll(PageRequest.of(page,books_per_page, Sort.by("id"))).getContent();
    }

    public List<Book> findAllByName(String name){
        return bookRepository.findAllByName(name);
    }


}
