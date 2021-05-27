package com.app.moneyapi.resource;

import com.app.moneyapi.dto.request.CategoryRequest;
import com.app.moneyapi.dto.response.CategoryResponse;
import com.app.moneyapi.entity.Category;
import com.app.moneyapi.event.ResourceCreateEvent;
import com.app.moneyapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(category)).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_CATEGORY') and #oauth2.hasScope('white')")
    public ResponseEntity<CategoryRequest> create(@Valid @RequestBody CategoryRequest categoryRequest, HttpServletResponse response) {
        Category categorySave = categoryRepository.save(new Category(categoryRequest));
        publisher.publishEvent(new ResourceCreateEvent(this, response, categorySave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoryRequest(categorySave));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
    public ResponseEntity getCategory(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(c -> ResponseEntity.ok().body(new CategoryResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

}
