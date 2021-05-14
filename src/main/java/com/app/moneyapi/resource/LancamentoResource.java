package com.app.moneyapi.resource;

import com.app.moneyapi.entity.Lancamento;
import com.app.moneyapi.repository.filter.LancamentoFilter;
import com.app.moneyapi.event.ResourceCreateEvent;
import com.app.moneyapi.repository.LancamentoRepository;
import com.app.moneyapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Lancamento> getAll() {
        return lancamentoRepository.findAll();
    }

    @GetMapping("/pesquisa")
    public List<Lancamento> search(LancamentoFilter lancamentoFilter) {
        return lancamentoRepository.searchByFilter(lancamentoFilter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> get(@PathVariable Long id) {
        return lancamentoRepository.findById(id)
                .map(l -> ResponseEntity.ok().body(l))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lancamento> create(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSave = lancamentoService.save(lancamento);
        publisher.publishEvent(new ResourceCreateEvent(this, response, lancamentoSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSave);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        lancamentoRepository.deleteById(id);
    }

}
