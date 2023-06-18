package com.tradebulls.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tradebulls.entity.Category;
import com.tradebulls.entity.Product;
import com.tradebulls.exception.CartException;
import com.tradebulls.repo.CategoryRepo;
import com.tradebulls.repo.ProductRepo;

@Service
public class CartServiceImpl implements CartService {
	
		@Autowired
		private CategoryRepo categoryRepo;
		
		@Autowired
		private ProductRepo productRepo;
		
		@Override
		public Iterable<Category> saveCategory(List<Category> category) {
			return categoryRepo.saveAll(category);  //return policy;
		}

		@Override
		public Iterable<Product> saveProduct(List<Product> productList) {
			return productRepo.saveAll(productList);
		}
		
		@Override
		public Iterable<Category> getAllCategories() {
			return categoryRepo.findAll(); //to get the all list
		}
		
		@Override
		public Iterable<Product> getAllProducts() {
			return productRepo.findAll(); //to get the all list
		}
		
		@Override
		public Optional<Category> getCategoryById(Integer Id) {
			Optional<Category> category=categoryRepo.findById(Id);//null
			if(category==null) { //6==null or null==null
				throw new CartException("Policy id "+Id+" incorrect..");
			}
			return category;
		}
		
		@Override
		public Optional<Product> getProductById(Long Id) {
			Optional<Product> product=productRepo.findById(Id);//null
			if(product==null) { //6==null or null==null
				throw new CartException("Policy id "+Id+" incorrect..");
			}
			return product;
		}
		
		@Override
		public void deleteCategory(Integer id) {
			categoryRepo.deleteById(id);
		}
		
		@Override
		public void deleteProduct(Long id) {
			productRepo.deleteById(id);
		}
		
		@Override
		public void updateCategoryById(Integer id, Category categoryDetails) {
			Category updateCategory = categoryRepo.findById(id).orElse(null);
			updateCategory.setCategoryId(categoryDetails.getCategoryId());
			updateCategory.setCategoryName(categoryDetails.getCategoryName());
			updateCategory.setProducts(categoryDetails.getProducts());
			categoryRepo.save(updateCategory);
		}
		
		@Override
		public void updateProductById(Long id, Product productDetails) {
			Product updateProduct = productRepo.findById(id).orElse(null);
			updateProduct.setProductId(productDetails.getProductId());
			updateProduct.setCategory(productDetails.getCategory()); 
			updateProduct.setName(productDetails.getName());
			productRepo.save(updateProduct);
		}
		
		@Override
		public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy)
		    {
			   Pageable paging = PageRequest.of(pageNo, pageSize);

			   Page<Product> pagedResult = productRepo.findAll(paging);

		        if(pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Product>();
		        }
		    }
		
		@Override
		public List<Category> getAllCategories(Integer pageNo, Integer pageSize, String sortBy)
	    {
		   Pageable paging = PageRequest.of(pageNo, pageSize);

		   Page<Category> pagedResult = categoryRepo.findAll(paging);

	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Category>();
	        }
	    }

		
	
}