package org.pizzeria.java.spring_la_mia_pizzeria_crud.repo;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    Page<Pizza> findAllByNameContaining(Pageable pagination, String name);

}