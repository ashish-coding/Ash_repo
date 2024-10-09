package com.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.entity.Books;

public interface BookRepository extends CrudRepository<Books,Integer>{
	public Books findById(int id);
}
