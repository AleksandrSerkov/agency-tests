package com.example.person_service.service;

import java.util.List;

import com.example.person_service.dto.PersonDto;

public interface PersonService {
    PersonDto createPerson(PersonDto dto);
    PersonDto updatePerson(Long id, PersonDto dto);
    PersonDto getPerson(Long id);
    List<PersonDto> getAll();
    void deletePerson(Long id);
}
