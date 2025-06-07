package com.example.person_service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.person_service.dto.PersonDto;
import com.example.person_service.entity.Person;
import com.example.person_service.repository.PersonRepository;
import com.example.person_service.service.PersonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public PersonDto createPerson(PersonDto dto) {
        Person person = new Person();
        person.setFullName(dto.getFullName());
        person.setBirthDate(dto.getBirthDate());
        person.setVisible(true);
        return toDto(repository.save(person));
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto dto) {
        Person person = repository.findById(id).orElseThrow();
        person.setFullName(dto.getFullName());
        person.setBirthDate(dto.getBirthDate());
        return toDto(repository.save(person));
    }

    @Override
    public PersonDto getPerson(Long id) {
        return toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<PersonDto> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void deletePerson(Long id) {
        repository.deleteById(id);
    }

    private PersonDto toDto(Person p) {
        PersonDto dto = new PersonDto();
        dto.setFullName(p.getFullName());
        dto.setBirthDate(p.getBirthDate());
        return dto;
    }
}