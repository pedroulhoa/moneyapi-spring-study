package com.app.moneyapi.resource;

import com.app.moneyapi.entity.Categoria;
import com.app.moneyapi.event.ResourceCreateEvent;
import com.app.moneyapi.repository.CategoriaRepository;
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
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('white')")
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSave = categoriaRepository.save(categoria);
        publisher.publishEvent(new ResourceCreateEvent(this, response, categoriaSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSave);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public ResponseEntity getCategoria(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

}
