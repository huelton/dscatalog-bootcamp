package com.devsuperior.dscatalog.client.category.interf;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.dscatalog.client.category.entity.CategoryClient;
import com.devsuperior.dscatalog.client.category.entity.CategoryContentClient;

@FeignClient(url= "https://sdcatalog-huelton.herokuapp.com/categories/" , name = "categories")
public interface CategoryClientService {

    @GetMapping("/{id}")
    CategoryClient findByCategoryClient(@PathVariable("id") String id);

    @GetMapping
    CategoryContentClient findAllCategoryClient();
}
