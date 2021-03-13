package edu.cpci.shetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>, CrudRepository<Rol, Long> {

}
