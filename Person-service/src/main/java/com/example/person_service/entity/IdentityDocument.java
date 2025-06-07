package com.example.person_service.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentityDocument {
    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private String number;

    @ManyToOne
    private Person person;
}