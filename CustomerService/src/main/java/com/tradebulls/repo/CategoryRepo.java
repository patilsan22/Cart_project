package com.tradebulls.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tradebulls.entity.Category;


@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {

	List<Category> findAll();
	
	Page<Category> findAll(Pageable paging);
}