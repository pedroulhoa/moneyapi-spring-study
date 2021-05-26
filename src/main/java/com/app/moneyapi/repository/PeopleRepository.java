package com.app.moneyapi.repository;

import com.app.moneyapi.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
