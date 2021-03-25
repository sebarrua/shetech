package edu.cpci.shetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.Parametro;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long>, CrudRepository<Parametro, Long> {

}
