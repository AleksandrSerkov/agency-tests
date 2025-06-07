package com.example.person_service.dto;
import lombok.Data;

@Data
public class AddressDto {
    private String city;
    private String street;
    private String region;
}