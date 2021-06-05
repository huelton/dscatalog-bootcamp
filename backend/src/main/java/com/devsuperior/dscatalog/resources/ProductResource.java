package com.devsuperior.dscatalog.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductResource {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
		Page<ProductDTO> list = productService.findAllPaged(pageable);
		list.getContent().forEach(prod -> {
			// Hateos to each id product
			prod.add(linkTo(methodOn(ProductResource.class).findById(prod.getId())).withSelfRel());
			// Hateos to each id category
			prod.getCategories().forEach(
					cat -> cat.add(linkTo(methodOn(CategoryResource.class).findById(cat.getId())).withSelfRel()));
		});

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO dto = productService.findById(id);
		Pageable pageable = null;
		// Hateos to each id category
		dto.getCategories()
				.forEach(cat -> cat.add(linkTo(methodOn(CategoryResource.class).findById(cat.getId())).withSelfRel()));
		// Hateos to list of products
		dto.add(linkTo(methodOn(ProductResource.class).findAll(pageable)).withRel("Product List"));
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping()
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
		dto = productService.save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
		dto = productService.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
