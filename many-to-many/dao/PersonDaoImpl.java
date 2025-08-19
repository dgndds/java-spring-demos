package com.xen0n.mapping_one_to_one.dao;

import com.xen0n.mapping_one_to_one.entity.Course;
import com.xen0n.mapping_one_to_one.entity.Person;
import com.xen0n.mapping_one_to_one.entity.PersonDetail;
import com.xen0n.mapping_one_to_one.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

    private EntityManager entityManager;

    @Autowired
    public PersonDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Person getPersonById(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        entityManager.persist(person);
    }

    @Override
    @Transactional
    public void deletePersonById(int id) {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }

    @Override
    public PersonDetail getPersonDetailById(int id) {
        return entityManager.find(PersonDetail.class, id);
    }

    @Override
    @Transactional
    public void deletePersonDetailById(int id) {
        PersonDetail personDetail = entityManager.find(PersonDetail.class, id);
        personDetail.getPerson().setPersonDetails(null);

        entityManager.remove(personDetail);
    }

    @Override
    public List<Course> findCoursesByPersonId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where person.id = :personId", Course.class);
        query.setParameter("personId", id);
        return query.getResultList();
    }

    @Override
    public Person findPersonByIdJoinFetch(int id) {
        TypedQuery<Person> query = entityManager.createQuery(
                "Select p FROM Person p " +
                        "JOIN FETCH p.courseList " +
                        "JOIN FETCH p.personDetails " +
                        "WHERE p.id = :personId",
                Person.class);

        query.setParameter("personId", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c join fetch c.reviews where c.id = :courseId" , Course.class);
        query.setParameter("courseId", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c join fetch c.students where c.id=:courseId", Course.class);
        query.setParameter("courseId", id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("Select s from Student s join fetch s.courses where s.id=:studentId", Student.class);
        query.setParameter("studentId", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class, id);

        if(student != null) {
            List<Course> courses = student.getCourses();
            
            for(Course course : courses) {
                course.getStudents().remove(student);
            }

            entityManager.remove(student);
        }
    }
}
