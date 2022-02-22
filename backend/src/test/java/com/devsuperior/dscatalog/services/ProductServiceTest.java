package com.devsuperior.dscatalog.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.utils.ProductFactory;

@Profile("test")
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepository productRepository;
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private PageImpl<Product> page; //simular um Pageable
	private Product product;
	
	@BeforeEach
	void setup() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 4L;
		product = ProductFactory.createProduct();
		page = new PageImpl<>(List.of(product));
		
		when(productRepository.findAll((Pageable) any())).thenReturn(page);
		when(productRepository.save(any())).thenReturn(product);
		when(productRepository.findById(existingId)).thenReturn(Optional.of(product));
		when(productRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		doNothing().when(productRepository).deleteById(existingId);
		doThrow(EmptyResultDataAccessException.class).when(productRepository).deleteById(nonExistingId);
		doThrow(DataIntegrityViolationException.class).when(productRepository).deleteById(dependentId);
	}	
	
	@Test
    public void deleteShouldDoNothingWhenIdExist() {
		assertDoesNotThrow(() -> {
			productService.delete(existingId);
		});
		
		verify(productRepository, times(1)).deleteById(existingId);
	}
	
	@Test
    public void deleteShouldTrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		assertThrows(ResourceNotFoundException.class, () -> {
			productService.delete(nonExistingId);
		});
		
		verify(productRepository, times(1)).deleteById(nonExistingId);
	}

	@Test
    public void deleteShouldTrowDatabaseExceptionWhenIdViolatedIntegrity() {
		assertThrows(DatabaseException.class, () -> {
			productService.delete(dependentId);
		});
		
		verify(productRepository, times(1)).deleteById(dependentId);
	}
}
