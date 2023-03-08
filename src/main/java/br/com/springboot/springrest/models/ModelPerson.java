package br.com.springboot.springrest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter @Getter
@Entity
@Table(name = "person")
public class ModelPerson implements Serializable{

    @Serial
    private static final long serialVersionUID = -6322426944071014824L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
}
