package com.techelper.tropsmart_backend.user.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.user.domain.models.Person;
import com.techelper.tropsmart_backend.user.domain.models.User;
import com.techelper.tropsmart_backend.user.domain.repositories.IPersonRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IUserRepository;
import com.techelper.tropsmart_backend.user.domain.services.IPersonService;
import com.techelper.tropsmart_backend.user.domain.services.comunications.PersonResponse;
import com.techelper.tropsmart_backend.user.domain.services.outputs.PersonOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }


    @Override
    public PersonResponse findPeopleById(int id) {
        try
        {
            User getUser = userRepository.findUserByPersonId(id).get();
            Person getPerson = personRepository.findById(id).get();

            PersonOutput newPersonOutput = new PersonOutput();
            newPersonOutput.setEmail(getUser.getEmail());
            newPersonOutput.setFirstName(getPerson.getFirstName());
            newPersonOutput.setLastName(getPerson.getLastName());
            if(getPerson.getPersonType()==1)
                newPersonOutput.setUserType("Customer");
            if(getPerson.getPersonType()==2)
                newPersonOutput.setUserType("Driver");
            return new PersonResponse(newPersonOutput);
        }
        catch (Exception e)
        {
            return new PersonResponse("An error ocurred while getting the person: "+e.getMessage());

        }

    }

    @Override
    public PersonResponse findAllPersons() {
        try
        {
            List<Person> personList = personRepository.findAll();
            List<PersonOutput> personOutputList = new ArrayList<>();
            for (Person p:personList) {
                Optional<User> getUser = userRepository.findUserByPersonId(p.getId());
                PersonOutput newPersonOutput = new PersonOutput();
                newPersonOutput.setEmail(getUser.get().getEmail());
                newPersonOutput.setFirstName(p.getFirstName());
                newPersonOutput.setLastName(p.getLastName());
                if(p.getPersonType()==1)
                    newPersonOutput.setUserType("Customer");
                if(p.getPersonType()==2)
                    newPersonOutput.setUserType("Driver");
                personOutputList.add(newPersonOutput);
            }
            return new PersonResponse(personOutputList);
        }
        catch (Exception e)
        {
            return new PersonResponse("An error ocurred while getting the person list: "+e.getMessage());
        }

    }
}
