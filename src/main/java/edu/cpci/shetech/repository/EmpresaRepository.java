package edu.cpci.shetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>, CrudRepository<Empresa, Long> {

}
