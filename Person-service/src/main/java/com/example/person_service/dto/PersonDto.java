package com.example.person_service.dto;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class PersonDto {
    private String fullName;
    private LocalDate birthDate;
    private List<DocumentDto> documents;
    private List<ContactDto> contacts;
    private List<AddressDto> addresses;
    private AddressDto registrationAddress;
}