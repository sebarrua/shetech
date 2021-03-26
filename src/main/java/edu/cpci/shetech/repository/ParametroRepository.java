package edu.cpci.shetech.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.Parametro;

@Repository
@EnableJpaRepositories
public interface ParametroRepository extends JpaRepository<Parametro, Long>, CrudRepository<Parametro, Long>, PagingAndSortingRepository<Parametro, Long> {
	
	//static String estadoPosteo = "ESTADO.POSTEO"; 
	
	@Query("select p.parametros from parametro p where p.tipoParametro = ?1")
    List<Parametro> findByTipoParametro(String estadoPosteo);

}
