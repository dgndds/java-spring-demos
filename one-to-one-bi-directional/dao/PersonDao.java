package com.xen0n.mapping_one_to_one.dao;

import com.xen0n.mapping_one_to_one.entity.Person;
import com.xen0n.mapping_one_to_one.entity.PersonDetail;

public interface PersonDao {
    Person getPersonById(int id);

    void savePerson(Person person);

    void deletePersonById(int id);

    PersonDetail getPersonDetailById(int id);

    void deletePersonDetailById(int id);
}
