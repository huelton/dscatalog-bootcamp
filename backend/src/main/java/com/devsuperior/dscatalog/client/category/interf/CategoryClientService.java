package com.devsuperior.dscatalog.client.category.interf;

import com.devsuperior.dscatalog.client.category.entity.CategoryClient;
import com.devsuperior.dscatalog.client.category.entity.CategoryContentClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url= "https://sdcatalog-huelton.herokuapp.com/categories/" , name = "categories")
public interface CategoryClientService {

    @GetMapping("/{id}")
    CategoryClient findByCategoryClient(@PathVariable("id") String id);

    @GetMapping
    CategoryContentClient findAllCategoryClient();
}
