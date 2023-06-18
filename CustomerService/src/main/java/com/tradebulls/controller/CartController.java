
package com.tradebulls.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradebulls.entity.Category;
import com.tradebulls.entity.Product;
import com.tradebulls.exception.CartException;
import com.tradebulls.service.CartServiceImpl;

@RestController
@RequestMapping("/api")
public class CartController {
	
	@Autowired
	private CartServiceImpl productService ;
	
	/*
	 * This method is used to Save Multiple All the details.
	 */
	
	@PostMapping("/categories")
	public ResponseEntity<Iterable<Category>> createCategory(@RequestBody List<Category> categoryList)
			throws Exception {
		Iterable<Category> createdCategory = this.productService.saveCategory(categoryList);
		return ResponseEntity.ok().body(createdCategory); //createdPolicy that is return to createPolicy
	}
	
	@PostMapping("/products")
	public ResponseEntity<Iterable<Product>> createProduct(@RequestBody List<Product> productList)
			throws Exception {
		Iterable<Product> createdProduct = this.productService.saveProduct(productList);
		return ResponseEntity.ok().body(createdProduct); 
		}
	
	/*
	 * This method is used to get All the details.
	 */
	
	@GetMapping("/getAllCategories")
	public @ResponseBody Iterable<Category> getAllCategorieList() throws Exception {
		return productService.getAllCategories(); 
	}
	
	@GetMapping("/getAllProduct")
	public @ResponseBody Iterable<Product> getAllProductsList() throws Exception {
		return productService.getAllProducts(); 
	}
	
	/*
	 * This method is used to get the details by using id.
	 */
	@GetMapping("/getCategory/{id}")
	public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable("id") Integer id) { //getting data by using id.
		Optional<Category> policy = productService.getCategoryById(id); //method calling
		return ResponseEntity.ok().body(policy);
	}
	
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") Long id) { //getting data by using id.
		Optional<Product> product = productService.getProductById(id); //method calling
		return ResponseEntity.ok().body(product);
	}
	
	/*
	 * This method is used to Update the details by id.
	 */
	
	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable Integer id, @RequestBody Category categoryDetails) {
		try {
			productService.updateCategoryById(id, categoryDetails);
			return ResponseEntity.ok("Record Updated");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		try {
			productService.updateProductById(id, productDetails);
			return ResponseEntity.ok("Record Updated");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * This method is used to Delete the details by id.
	 */
	
	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Integer id) throws CartException {
		try {
			productService.deleteCategory(id);
			return ResponseEntity.ok("Record Deleted");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id) throws CartException {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.ok("Record Deleted");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * This method is used to get All the details By Pagination.
	 */
	
    @GetMapping("/getAllProductPaging")
    public ResponseEntity<List<Product>> getAllProduct(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Product> list = productService.getAllProducts(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/getAllCategoryPaging")
    public ResponseEntity<List<Category>> getAllCategory(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Category> prodList = productService.getAllCategories(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Category>>(prodList, new HttpHeaders(), HttpStatus.OK);
    }
	


}
