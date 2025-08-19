package com.xen0n.mapping_one_to_one.dao;

import com.xen0n.mapping_one_to_one.entity.Person;

public interface PersonDao {
    Person getPersonById(int id);

    void savePerson(Person person);

    void deletePersonById(int id);
}
