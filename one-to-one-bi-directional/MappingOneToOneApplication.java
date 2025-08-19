package com.xen0n.mapping_one_to_one;

import com.xen0n.mapping_one_to_one.dao.PersonDao;
import com.xen0n.mapping_one_to_one.entity.Person;
import com.xen0n.mapping_one_to_one.entity.PersonDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MappingOneToOneApplication {

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

			deletePersonDetail(personDao);
		};
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
		System.out.println("********        MAPPER APP         ********");
		System.out.println("*******************************************");
		System.out.println("******** Author: M.D.Y.            ********");
		System.out.println("******** Date: 31/07/2025          ********");
		System.out.println("******** Desc: One to One Map demo ********");
		System.out.println("*******************************************");
		System.out.println();
		System.out.println();
	}
}
