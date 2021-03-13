package edu.cpci.shetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cpci.shetech.entity.Posteo;

@Repository
public interface PosteoRepository extends JpaRepository<Posteo, Long>, CrudRepository<Posteo, Long> {

}
