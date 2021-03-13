package edu.cpci.shetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>, CrudRepository<Comentario, Long> {

}
