package org.pizzeria.java.spring_la_mia_pizzeria_crud.services;

import java.util.List;
import java.util.Optional;

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

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepo.findById(id);
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepo.save(pizza);
    }

    public void delete(Pizza pizza) {
        pizzaRepo.delete(pizza);
    }

    public void deleteById(Integer id) {
        Pizza pizza = pizzaRepo.findById(id).get();
        pizzaRepo.delete(pizza);
    }

}
