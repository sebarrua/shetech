package edu.cpci.shetech.service;

import java.util.List;

public interface _BaseService <T>{

	List<T> getAll();
	T getOneById(Long id);
	void save(T entidad);
    void delete(Long id);
	
}
