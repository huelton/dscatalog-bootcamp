package com.devsuperior.dscatalog.client.category.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.client.category.entity.CategoryClient;
import com.devsuperior.dscatalog.client.category.entity.CategoryContentClient;
import com.devsuperior.dscatalog.client.category.interf.CategoryClientService;

@RestController
@RequestMapping(value="/categories/client")
public class CategoryClientResource {

    private final CategoryClientService catService;

    @Autowired
    public CategoryClientResource(CategoryClientService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<CategoryContentClient> findAllCategoryClient() {

        CategoryContentClient cli = catService.findAllCategoryClient();

        return cli != null ? ResponseEntity.ok().body(cli) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryClient> findByCategoryClient(@PathVariable String id) {

        CategoryClient cli = catService.findByCategoryClient(id);

        return cli != null ? ResponseEntity.ok().body(cli) : ResponseEntity.notFound().build();
    }
}
