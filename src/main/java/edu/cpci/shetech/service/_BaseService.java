package edu.cpci.shetech.service;

import java.util.List;

public interface _BaseService <T>{
	
	
	public List<T> getAll();
	public T getOneById(Long id);
	public void save(T entidad);
    public void delete(Long id);
	
}
