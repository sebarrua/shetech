package edu.cpci.shetech.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.Parametro;
import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Usuario;

@Repository
public interface PosteoRepository extends JpaRepository<Posteo, Long>, CrudRepository<Posteo, Long>, PagingAndSortingRepository<Posteo, Long> {
	
	@Query("select p from posteo p where p.usuario = ?1")
    List<Posteo> getPosteosByUsuario(Usuario usuario);
	
	@Query("select p from posteo p where p.estado = ?1")
    List<Posteo> getPosteosByEstado(Parametro parametro);
	
	@Query("select p from posteo p where p.usuario = ?1 and p.estado = ?2")
    List<Posteo> findByUsuarioAndEstado(Usuario usuario, Parametro parametro);
	
//	@Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
//	  User findByLastnameOrFirstname(@Param("lastname") String lastname, @Param("firstname") String firstname);

}
