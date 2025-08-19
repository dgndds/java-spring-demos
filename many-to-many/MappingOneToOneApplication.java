package com.xen0n.mapping_one_to_one;

import com.xen0n.mapping_one_to_one.dao.PersonDao;
import com.xen0n.mapping_one_to_one.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MappingOneToOneApplication {

	static long startTime = System.currentTimeMillis();

	public static void main(String[] args) {
		SpringApplication.run(MappingOneToOneApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PersonDao personDao) {
		return  runner -> {
			printHeader();

			// Mini create show delete loop :)
			/*
			int createdPersonId = createPerson(personDao);
			getPerson(personDao, createdPersonId);
			deletePerson(personDao, createdPersonId);
			findPersonDetailById(personDao);
			deletePersonDetail(personDao);
			 */

			//createPersonWithCourses(personDao);
			//findPersonWithCourses(personDao);
			//findCoursesOfPerson(personDao);

			//findPersonByIdJoinFetch(personDao);

			//createCourseWithReviews(personDao);
			//getCourseAndReviews(personDao);
			//deleteCourseAndReviews(personDao);

			//createCourseAndStudents(personDao);
			//getCourseAndStudents(personDao);
			//getStudentAndCourses(personDao);
			//addMoreCoursesToStudent(personDao);
			//deleteCourse(personDao);

			//deleteStudent(personDao);
			printFooter();
		};
	}

	private void deleteStudent(PersonDao personDao) {
		int id = 2;
		personDao.deleteStudentById(id);

		System.out.println("Deleted student: " + id);
	}

	private void deleteCourse(PersonDao personDao) {
		int id = 9;
		personDao.deleteCourseById(id);
		System.out.println("Deleted course: " + id);
	}

	private void addMoreCoursesToStudent(PersonDao personDao) {
		int id = 2;
		Student student = personDao.findStudentAndCoursesByStudentId(id);

		Person person = new Person("Anita","Ramsey");
		PersonDetail details = new PersonDetail("Iran", "Tehran");
		person.setPersonDetails(details);

		Course course1 = new Course("Ultimate Pacman course 2");
		Course course2 = new Course("Ultimate Java course 2");

		course1.setPerson(person);
		course2.setPerson(person);

		student.addCourse(course1);
		student.addCourse(course2);

		printObjectDetails("Found Student", student);

	 	personDao.updateStudent(student);
	}

	private void getStudentAndCourses(PersonDao personDao) {
		int id = 2;

		Student student = personDao.findStudentAndCoursesByStudentId(id);

		printObjectDetails("Found Student", student);
		printObjectDetails("Found Courses", student.getCourses());
	}

	private void getCourseAndStudents(PersonDao personDao) {
		int id = 6;
		Course course = personDao.findCourseAndStudentsByCourseId(id);

		printObjectDetails("Found Course", course);
		printObjectDetails("Found Students", course.getStudents());
	}

	private void createCourseAndStudents(PersonDao personDao) {
		Person person = new Person("John", "Elder");
		PersonDetail personDetail = new PersonDetail("Germany","Nurnberg");
		person.setPersonDetails(personDetail);

		Course course = new Course("Ultimate photoshop course for free 2025");
		course.setPerson(person);

		Student st1 = new Student("Justin", "Sowen", "justin@mail.com");
		Student st2 = new Student("Murat", "Dar", "murat@mail.com");

		course.addStudent(st1);
		course.addStudent(st2);

		printObjectDetails("Saving course", course);

		personDao.saveCourse(course);
	}

	private void deleteCourseAndReviews(PersonDao personDao) {
		int id = 4;
		personDao.deleteCourseById(id);
		System.out.println("Deleted course with id: " + id);
	}

	private void getCourseAndReviews(PersonDao personDao) {
		int id = 4;
		Course course = personDao.findCourseAndReviewsByCourseId(id);

		printObjectDetails("Found Course", course);
		printObjectDetails("Found Course Reviews", course.getReviews());
	}

	private void createCourseWithReviews(PersonDao personDao) {
		Course course = new Course("Ultimate Redis Course");

		Person person = new Person("Jonathan", "Redison");
		PersonDetail personDetail = new PersonDetail("Ukranie","Kyiv");
		person.setPersonDetails(personDetail);

		course.setPerson(person);

		course.addReview(new Review("Great course"));
		course.addReview(new Review("Nice course"));
		course.addReview(new Review("Bad course"));

		personDao.saveCourse(course);

		printObjectDetails("Saved Course", course);
	}

	private void findPersonByIdJoinFetch(PersonDao personDao) {
		int id = 11;
		Person foundPerson = personDao.findPersonByIdJoinFetch(id);

		printObjectDetails("Found Person", foundPerson);
		printObjectDetails("Found Person Details", foundPerson.getPersonDetails());
		printObjectDetails("Found Courses", foundPerson.getCourseList());

	}

	private void findCoursesOfPerson(PersonDao personDao) {
		int id = 11;

		Person foundPerson = personDao.getPersonById(id);

		printObjectDetails("Found Person", foundPerson);

		List<Course> courses = personDao.findCoursesByPersonId(id);
		foundPerson.setCourseList(courses);

		printObjectDetails("Found Courses", foundPerson.getCourseList());
	}

	private void findPersonWithCourses(PersonDao personDao) {
		int id = 11;

		Person foundPerson = personDao.getPersonById(id);

		printObjectDetails("Found Person", foundPerson);
		printObjectDetails("Found Courses", foundPerson.getCourseList());
	}

	private void createPersonWithCourses(PersonDao personDao) {
		Person person = new Person("Rose", "Summer");
		PersonDetail personDetail = new PersonDetail("Canada", "Toronto");

		person.setPersonDetails(personDetail);

		Course firstCourse = new Course("Ultimate Docker Course");
		Course secondCourse = new Course("Ultimate Vue Course");

		person.addCourse(firstCourse);
		person.addCourse(secondCourse);

		personDao.savePerson(person);

		printObjectDetails("Saved Person", person);
		printObjectDetails("Saved Courses", person.getCourseList());

	}

	private void deletePersonDetail(PersonDao personDao) {
		int id = 8;
		personDao.deletePersonDetailById(id);

		System.out.println("Deleted person detail with id: " + id);
	}

	private void findPersonDetailById(PersonDao personDao) {
		int id = 1;

		PersonDetail detail = personDao.getPersonDetailById(id);

		System.out.println("Found person detail:  " + detail);
		System.out.println("Found Person: " + detail.getPerson());
	}

	private void deletePerson(PersonDao personDao, int createdPersonId) {
		personDao.deletePersonById(createdPersonId);

		System.out.println("Deleted person with id: " + createdPersonId);
	}

	private void getPerson(PersonDao personDao, int createdPersonId) {
		Person person = personDao.getPersonById(createdPersonId);
		System.out.println("Found Person: " + person);
	}

	private int createPerson(PersonDao personDao) {
		Person person = new Person("Mustafa", "Tosun");
		PersonDetail personDetail = new PersonDetail("Japan","Tokio");

		person.setPersonDetails(personDetail);

		personDao.savePerson(person);

		System.out.println("Saved person: " + person);

		return person.getId();
	}

	private static void printHeader() {
		System.out.println("*******************************************");
		System.out.println("********      MAPPER Demo APP      ********");
		System.out.println("*******************************************");
		System.out.println("******** Author: M.D.Y.            ********");
		System.out.println("******** Date: 31/07/2025          ********");
		System.out.println("******** Desc: Demo for Mappings:  ********");
		System.out.println("******** 		One to One		   ********");
		System.out.println("******** 		One to Many		   ********");
		System.out.println("******** 		Many to One 	   ********");
		System.out.println("******** 		Many to Many	   ********");
		System.out.println("*******************************************");
		System.out.println();
		System.out.println();
	}

	// For a list of objects
	public static <T> void printObjectDetails(String title, List<T> objects) {
		System.out.println(title + ": ");
		System.out.println("=================================================");
		int index = 1;
		for (T item : objects) {
			System.out.println(index + ". " + item);
			index++;
		}
		System.out.println();
	}

	// For a single object
	public static <T> void printObjectDetails(String title, T object) {
		System.out.println(title + ": ");
		System.out.println("=================================================");
		System.out.println("1. " + object);
		System.out.println();
	}

	private static void printFooter(){
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		System.out.println();
		System.out.println("**********************************");
		System.out.println("**==============================**");
		System.out.println("**     Execution Completed!     **");
		System.out.println("**"+"    Execution Time: "+ timeElapsed +"ms    "+"**");
		System.out.println("**==============================**");
		System.out.println("**********************************");
		System.out.println();
		System.out.println("====== The more you sweat in training, the less you bleed in combat. ======");
		System.out.println();
		System.out.println("Created By M.D.Y - 01/08/2025");
	}
}
