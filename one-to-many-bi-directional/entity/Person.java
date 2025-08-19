package com.xen0n.mapping_one_to_one.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="person_details_id")
    private PersonDetail personDetails;

    @OneToMany(mappedBy = "person",
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private List<Course> courseList;

    public Person() {
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public PersonDetail getPersonDetails() {
        return personDetails;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setPersonDetails(PersonDetail personDetailsId) {
        this.personDetails = personDetailsId;
    }

    public void addCourse(Course course) {
        if(courseList == null) {
            courseList = new ArrayList<>();
        }

        courseList.add(course);

        course.setPerson(this);
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", personDetails=" + personDetails +
                '}';
    }
}
