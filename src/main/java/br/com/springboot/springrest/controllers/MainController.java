package br.com.springboot.springrest.controllers;

import br.com.springboot.springrest.models.ModelPerson;
import br.com.springboot.springrest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/listUsers")
    public ResponseEntity<List<ModelPerson>> usersList() {
        List<ModelPerson> people = personRepository.findAll();

        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PostMapping(value = "/saveUser")
    @ResponseStatus
    public ResponseEntity<ModelPerson> saveUser (@RequestBody ModelPerson user) {
        ModelPerson userSaved = personRepository.save(user);

        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteUser")
    @ResponseStatus
    public ResponseEntity<String> deleteUser (@RequestParam Long idUser) {
        personRepository.deleteById(idUser);

        return new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping(value = "/searchById")
    @ResponseStatus
    public ResponseEntity<ModelPerson> searchById (@RequestParam(name = "idUser") Long idUser) {
        ModelPerson person = personRepository.findById(idUser).get();

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping(value = "/updateUser")
    @ResponseStatus
    public ResponseEntity<?> updateUser (@RequestBody ModelPerson user) {
        if (user.getId() == null) {
            return new ResponseEntity<String>("ID não informado.", HttpStatus.UNAUTHORIZED);
        } else {
            Optional<ModelPerson> person = personRepository.findById(user.getId());
            if (person.isEmpty()) {
                return new ResponseEntity<String>("ID não encontrado", HttpStatus.UNAUTHORIZED);
            }
            ModelPerson userSaved = personRepository.saveAndFlush(user);
            return new ResponseEntity<ModelPerson>(userSaved, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/searchByName")
    @ResponseStatus
    public ResponseEntity<List<ModelPerson>> searchByName (@RequestParam(name = "name") String firstName) {
        List<ModelPerson> person = personRepository.findByName(firstName);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

}
