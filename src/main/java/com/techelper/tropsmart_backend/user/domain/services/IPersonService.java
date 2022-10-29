package com.techelper.tropsmart_backend.user.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.user.domain.models.Person;
import com.techelper.tropsmart_backend.user.domain.services.comunications.PersonResponse;

public interface IPersonService extends ICrudService<Person> {
    PersonResponse findPeopleById(int id);
    PersonResponse findAllPersons();
}