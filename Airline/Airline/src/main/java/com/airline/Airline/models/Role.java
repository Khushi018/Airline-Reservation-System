package com.airline.Airline.models;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter  @AllArgsConstructor
@Data

@NoArgsConstructor

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }
  
}