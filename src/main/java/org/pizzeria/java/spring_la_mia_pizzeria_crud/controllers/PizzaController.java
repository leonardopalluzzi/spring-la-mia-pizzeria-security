package org.pizzeria.java.spring_la_mia_pizzeria_crud.controllers;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Promotion;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/menu")
public class PizzaController {

    @Autowired
    PizzaRepository repo;

    @GetMapping
    public String index(Model model, @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "limit", required = false) Integer limit) {

        String nextPageLink = "";
        String prevPageLink = "";

        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Pageable pagination = PageRequest.of(page, limit);

        Page<Pizza> pizzas = repo.findAll(pagination);

        if (pizzas.hasNext()) {
            nextPageLink = "/menu?page=" + (page + 1) + "&limit=10";
        }
        if (pizzas.hasPrevious()) {
            prevPageLink = "/menu?page=" + (page - 1) + "&limit=10";
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("nextPageLink", nextPageLink);
        model.addAttribute("prevPageLink", prevPageLink);

        return "pizze/index";
    }

    @GetMapping("/results")
    public String index(Model model, @RequestParam(name = "page") int page,
            @RequestParam(name = "limit") int limit, @RequestParam(name = "name") String name) {

        String nextPageLink = "";
        String prevPageLink = "";

        Pageable pagination = PageRequest.of(page, limit);

        Page<Pizza> pizzas = repo.findAllByNameContaining(pagination, name);

        if (pizzas.hasNext()) {
            nextPageLink = "/menu?page=" + (page + 1) + "&limit=10";
        }
        if (pizzas.hasPrevious()) {
            prevPageLink = "/menu?page=" + (page - 1) + "&limit=10";
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("nextPageLink", nextPageLink);
        model.addAttribute("prevPageLink", prevPageLink);

        return "pizze/index";
    }

    @GetMapping("/pizza/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Pizza pizza = repo.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizze/show";
    }

    @GetMapping("/promotions/{id}/create")
    public String create(@PathVariable("id") Integer id, Model model) {
        Promotion promotion = new Promotion();

        promotion.setPizza(repo.findById(id).get());

        model.addAttribute("promotion", promotion);
        return "promotions/create";
    }

}
