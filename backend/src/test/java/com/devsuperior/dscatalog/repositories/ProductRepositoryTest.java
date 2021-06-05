package com.devsuperior.dscatalog.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.utils.ProductFactory;

@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;
	private String changeProductName;
	
	@BeforeEach
	void setup() {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25;
		changeProductName = "Phone Test";
	}
	
	@Test
	public void getShouldReturnObjectNoEmptyGetByIdWhenIdExist() {
		Optional<Product> result = productRepository.findById(existingId);	
		
		assertTrue(result.isPresent());
	}
	
	@Test
	public void getShouldReturnObjectEmptyGetByIdWhenIdNotExist() {
		Optional<Product> result = productRepository.findById(nonExistingId);	
		
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
		Product product = ProductFactory.createProduct();
		product.setId(null);
		
		product = productRepository.save(product);
		
		assertNotNull(product.getId());		
		assertEquals(countTotalProducts + 1, product.getId());
	}
	
	@Test
	public void updateShouldUpdateProductNameWhenFieldChange() {
		Product product = ProductFactory.createProduct();
		product = productRepository.save(product);
		
		product.setName(changeProductName);
		
		product = productRepository.save(product);
		
		assertNotNull(product.getId());		
		assertEquals(existingId, product.getId());
		assertEquals(changeProductName, product.getName());
		
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExist() {		
		productRepository.deleteById(existingId);		
		Optional<Product> result = productRepository.findById(existingId);	
		
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			
			productRepository.deleteById(nonExistingId);
		});

	}
}
