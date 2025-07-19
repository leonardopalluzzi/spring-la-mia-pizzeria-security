package org.pizzeria.java.spring_la_mia_pizzeria_crud.controllers;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.IngredientsRepository;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    PizzaRepository repo;

    @Autowired
    IngredientsRepository ingredientRepo;

    @GetMapping
    public String dashboard(Model model, @RequestParam(name = "page", required = false) Integer page,
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
            nextPageLink = "/dashboard?page=" + (page + 1) + "&limit=10";
        }
        if (pizzas.hasPrevious()) {
            prevPageLink = "/dashboard?page=" + (page - 1) + "&limit=10";
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("nextPageLink", nextPageLink);
        model.addAttribute("prevPageLink", prevPageLink);

        return "pizze/dashboard";
    }

    @GetMapping("/results")
    public String index(Model model, @RequestParam(name = "page") int page,
            @RequestParam(name = "limit") int limit, @RequestParam(name = "name") String name) {

        String nextPageLink = "";
        String prevPageLink = "";

        Pageable pagination = PageRequest.of(page, limit);

        Page<Pizza> pizzas = repo.findAllByNameContaining(pagination, name);

        if (pizzas.hasNext()) {
            nextPageLink = "/dashboard?page=" + (page + 1) + "&limit=10";
        }
        if (pizzas.hasPrevious()) {
            prevPageLink = "/dashboard?page=" + (page - 1) + "&limit=10";
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("nextPageLink", nextPageLink);
        model.addAttribute("prevPageLink", prevPageLink);

        return "pizze/dashboard";
    }

    @GetMapping("/pizza/create")
    public String create(Model model) {

        String endpoint = "/dashboard/pizza/create";

        model.addAttribute("endpoint", endpoint);
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepo.findAll());
        return "pizze/create";
    }

    @PostMapping("/pizza/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepo.findAll());

            return "pizze/create";
        }

        repo.save(pizza);

        return "redirect:/menu";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        String endpoint = "/dashboard/pizza/update/" + id;

        model.addAttribute("pizza", repo.findById(id).get());
        model.addAttribute("endpoint", endpoint);
        model.addAttribute("ingredients", ingredientRepo.findAll());

        return "pizze/edit";
    }

    @PostMapping("/pizza/update/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepo.findAll());

            return "pizze/edit";
        }

        repo.save(pizza);

        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        repo.deleteById(id);

        return "redirect:/dashboard";
    }
}
