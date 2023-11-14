package com.example.employeesjson.service;

import com.example.employeesjson.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private static final String FILE_PATH = "src/main/resources/example.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private List<Person> persons;

    public PersonService() {
        loadPersons();
    }

    private void loadPersons() {
        try {
            // Load existing persons from JSON file
            File file = new File(FILE_PATH);
            if (file.exists()) {
                persons = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));
            } else {
                persons = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Person> getAllPersons() {
        return persons;
    }

    public Person addPerson(Person person) {
        persons.add(person);
        savePersons();
        return person;
    }

    public void deletePersonById(int id) {
        persons.removeIf(person -> person.getId() == id);
        savePersons();
    }

    private void savePersons() {
        try {
            // Save updated persons to the JSON file
            objectMapper.writeValue(new File(FILE_PATH), persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

