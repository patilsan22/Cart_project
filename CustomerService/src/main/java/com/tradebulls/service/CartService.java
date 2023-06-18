package com.tradebulls.service;

import java.util.List;
import java.util.Optional;

import com.tradebulls.entity.Category;
import com.tradebulls.entity.Product;

public interface CartService {
		
	public Iterable<Category> saveCategory(List<Category> category);

	public Iterable<Product> saveProduct(List<Product> productList);
	
	public Iterable<Category> getAllCategories();
	
	public Iterable<Product> getAllProducts();
	
	public Optional<Category> getCategoryById(Integer Id);
	
	public Optional<Product> getProductById(Long Id);
	
	public void deleteCategory(Integer id);
	
	public void deleteProduct(Long id);
	
	public void updateCategoryById(Integer id, Category categoryDetails);
	
	public void updateProductById(Long id, Product productDetails);
	
	public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy);
	
	public List<Category> getAllCategories(Integer pageNo, Integer pageSize, String sortBy);

	
		
	
}