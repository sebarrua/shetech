package edu.cpci.shetech.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.Parametro;
import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Puntuacion;
import edu.cpci.shetech.entity.Usuario;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long>, CrudRepository<Puntuacion, Long>, PagingAndSortingRepository<Puntuacion, Long> {
	
	@Query("select p from puntuacion p where p.posteo = ?1")
    List<Puntuacion> getPuntuacionByPosteo(Posteo posteo);
	
	@Query("select p from puntuacion p where p.posteo = ?1 and p.posteo = ?2")
    Puntuacion findByPosteoAndUsuario(Posteo posteo, Usuario usuario);

}
