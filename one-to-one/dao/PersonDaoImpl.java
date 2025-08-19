package com.xen0n.mapping_one_to_one.dao;

import com.xen0n.mapping_one_to_one.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
