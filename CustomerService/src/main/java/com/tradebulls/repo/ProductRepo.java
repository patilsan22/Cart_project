package com.tradebulls.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tradebulls.entity.Product;


@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

	Iterable<Product> findAll();
	
	@Query(value = "SELECT * FROM USER u inner join PRODUCT p on u.id = p.id WHERE p.catagory = car ", nativeQuery = true)
	List<Product> findProduct();
	
    List<Product> findAllByName(String name, Pageable pageable);

	Page<Product> findAll(Pageable paging);
}