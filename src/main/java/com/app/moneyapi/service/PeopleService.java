package com.app.moneyapi.service;

import com.app.moneyapi.entity.People;
import com.app.moneyapi.repository.PeopleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public People update(Long id, People people) {
        People peopleSave = getPeopleById(id);
        BeanUtils.copyProperties(people, peopleSave, "id");
        return peopleRepository.save(peopleSave);
    }

    public void updateActive(Long id, Boolean active) {
        People peopleSave = getPeopleById(id);
        peopleSave.setActive(active);
        peopleRepository.save(peopleSave);
    }

    private People getPeopleById(Long id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EmptyResultDataAccessException(1);
                });
    }

}
