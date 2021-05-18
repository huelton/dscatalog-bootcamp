package com.devsuperior.dscatalog.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.devsuperior.dscatalog.entities.Category;

public class CategoryDTO extends RepresentationModel<CategoryDTO> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	public CategoryDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getName();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
