package com.app.moneyapi.resource;

import com.app.moneyapi.entity.People;
import com.app.moneyapi.event.ResourceCreateEvent;
import com.app.moneyapi.repository.PeopleRepository;
import com.app.moneyapi.service.PeopleService;
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
@RequestMapping("/people")
public class PeopleResource {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PEOPLE') and #oauth2.hasScope('read')")
    public List<People> getAll() {
        return peopleRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_PEOPLE') and #oauth2.hasScope('white')")
    public ResponseEntity<People> create(@Valid @RequestBody People people, HttpServletResponse response) {
        People peopleSave = peopleRepository.save(people);
        publisher.publishEvent(new ResourceCreateEvent(this, response, peopleSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(peopleSave);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PEOPLE') and #oauth2.hasScope('read')")
    public ResponseEntity get(@PathVariable Long id) {
        return peopleRepository.findById(id)
                .map(people -> ResponseEntity.ok().body(people))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVE_PEOPLE') and #oauth2.hasScope('white')")
    public void delete(@PathVariable Long id) {
        peopleRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CREATE_PEOPLE') and #oauth2.hasScope('white')")
    public ResponseEntity<People> update(@PathVariable Long id, @Valid @RequestBody People people) {
        People peopleSave = peopleService.update(id, people);
        return ResponseEntity.ok(peopleSave);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CREATE_PEOPLE') and #oauth2.hasScope('white')")
    public void updateActive(@PathVariable Long id, @RequestBody Boolean active) {
        peopleService.updateActive(id, active);
    }

}
