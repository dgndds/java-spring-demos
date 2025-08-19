package com.xen0n.mapping_one_to_one.dao;

import com.xen0n.mapping_one_to_one.entity.Course;
import com.xen0n.mapping_one_to_one.entity.Person;
import com.xen0n.mapping_one_to_one.entity.PersonDetail;
import com.xen0n.mapping_one_to_one.entity.Student;

import java.util.List;

public interface PersonDao {
    Person getPersonById(int id);

    void savePerson(Person person);

    void deletePersonById(int id);

    PersonDetail getPersonDetailById(int id);

    void deletePersonDetailById(int id);

    List<Course> findCoursesByPersonId(int id);

    Person findPersonByIdJoinFetch(int id);

    void saveCourse(Course course);

    Course findCourseAndReviewsByCourseId(int id);

    void deleteCourseById(int id);

    Course findCourseAndStudentsByCourseId(int id);

    Student findStudentAndCoursesByStudentId(int id);

    void updateStudent(Student student);

    void deleteStudentById(int id);
}
