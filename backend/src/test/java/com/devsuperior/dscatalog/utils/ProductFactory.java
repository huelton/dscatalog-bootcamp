package com.devsuperior.dscatalog.utils;

import java.time.Instant;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

public class ProductFactory {

	public static Product createProduct() {
		Product product = new Product(1L,"IPhone", "Good Phone", 3500.0, "https://img.com/img.png", Instant.parse("2021-04-15T03:00:00Z"));
		product.getCategories().add(new Category(2L,"Eletronics"));
		return product;
	}
	
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product,product.getCategories());
	}
}
