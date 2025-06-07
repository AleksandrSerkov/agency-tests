package com.example.person_service.dto;
import com.example.person_service.entity.DocumentType;

import lombok.Data;

@Data
public class DocumentDto {
    private DocumentType type;
    private String number;
}