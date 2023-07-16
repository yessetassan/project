package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Book;
import project.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository  extends JpaRepository<Person, Integer> {
    Person findById(int id);

}
