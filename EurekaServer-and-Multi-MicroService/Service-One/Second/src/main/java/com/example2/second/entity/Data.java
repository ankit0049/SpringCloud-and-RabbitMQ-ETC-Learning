package com.example2.second.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Data {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name ;
    String lastName;
    double price;
}
