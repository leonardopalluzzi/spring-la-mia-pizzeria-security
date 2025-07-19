package org.pizzeria.java.spring_la_mia_pizzeria_crud.controllers;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    PizzaRepository repo;

    @GetMapping
    public String home(Model model) {
        int page = 0;
        int limit = 5;
        Pageable pagination = PageRequest.of(page, limit);

        Page<Pizza> pizzas = repo.findAll(pagination);
        model.addAttribute("pizzas", pizzas.toList());
        return "home";
    }

}
