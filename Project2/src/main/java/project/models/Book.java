package project.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "related", insertable = false,updatable = false)
    private Integer related;

    @Column(name = "date")
    private Date date;

    @ManyToOne()
    @JoinColumn(name = "related", referencedColumnName = "id")
    private Person person;

    public Book(String name, int year, Integer related, Date date) {
        this.name = name;
        this.year = year;
        this.related = related;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", related=" + related +
                '}';
    }

    public Book(){

    }

    public Book(String name, int year) {
        this.name = name;
        this.year = year;
        this.related = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getRelated() {
        return related;
    }

    public void setRelated(Integer related) {
        this.related = related;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
