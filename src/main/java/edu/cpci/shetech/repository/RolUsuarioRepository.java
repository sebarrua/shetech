package edu.cpci.shetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.RolUsuario;

@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long>, CrudRepository<RolUsuario, Long> {

}
