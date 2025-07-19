package org.pizzeria.java.spring_la_mia_pizzeria_crud.services;

import java.util.List;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepo;

    public List<Pizza> findAll() {
        return pizzaRepo.findAll();
    }

    public Page<Pizza> findAllByTitle(Pageable pagination, String title) {
        Page<Pizza> pizzaList = pizzaRepo.findAllByNameContaining(pagination, title);

        return pizzaList;
    }

    public Pizza findById(Integer id) {
        return pizzaRepo.findById(id).get();
    }

    public void save(Pizza pizza) {
        pizzaRepo.save(pizza);
    }

    public void delete(Pizza pizza) {
        pizzaRepo.delete(pizza);
    }

}
