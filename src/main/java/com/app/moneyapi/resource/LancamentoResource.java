package com.app.moneyapi.resource;

import com.app.moneyapi.dto.LancamentoDTO;
import com.app.moneyapi.entity.Lancamento;
import com.app.moneyapi.repository.filter.LancamentoFilter;
import com.app.moneyapi.event.ResourceCreateEvent;
import com.app.moneyapi.repository.LancamentoRepository;
import com.app.moneyapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public List<Lancamento> getAll() {
        return lancamentoRepository.findAll();
    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<LancamentoDTO> getResumeLancamento(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.getResumeLancamento(lancamentoFilter, pageable);
    }

    @GetMapping("/pesquisa")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<Lancamento> search(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.searchByFilter(lancamentoFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Lancamento> get(@PathVariable Long id) {
        return lancamentoRepository.findById(id)
                .map(l -> ResponseEntity.ok().body(l))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> create(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSave = lancamentoService.save(lancamento);
        publisher.publishEvent(new ResourceCreateEvent(this, response, lancamentoSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSave);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long id) {
        lancamentoRepository.deleteById(id);
    }

}
