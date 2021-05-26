package com.app.moneyapi.resource;

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

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_CATEGORY') and #oauth2.hasScope('white')")
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category categorySave = categoryRepository.save(category);
        publisher.publishEvent(new ResourceCreateEvent(this, response, categorySave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySave);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
    public ResponseEntity getCategory(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

}
