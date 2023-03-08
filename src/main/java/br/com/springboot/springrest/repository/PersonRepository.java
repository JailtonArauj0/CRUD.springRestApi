package br.com.springboot.springrest.repository;

import br.com.springboot.springrest.models.ModelPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<ModelPerson, Long> {

    @Query(value = "select u from ModelPerson u where u.firstName like %?1%")
    List<ModelPerson> findByName(String name);

}
