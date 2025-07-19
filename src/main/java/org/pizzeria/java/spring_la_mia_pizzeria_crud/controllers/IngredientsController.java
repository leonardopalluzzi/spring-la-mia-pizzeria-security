package org.pizzeria.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    IngredientsRepository IngredientRepo;

    @GetMapping
    public String index(Model model) {

        List<Ingredient> ingredients = IngredientRepo.findAll();

        model.addAttribute("ingredients", ingredients);

        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        Ingredient ingredient = new Ingredient();

        model.addAttribute("ingredient", ingredient);
        return "ingredients/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }

        IngredientRepo.save(ingredient);

        return "redirect:/ingredients";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        Ingredient ingredient = IngredientRepo.findById(id).get();

        model.addAttribute("ingredient", ingredient);
        model.addAttribute("edit", true);

        return "ingredients/create";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }

        IngredientRepo.save(ingredient);

        return "redirect:/ingredients";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {

        Ingredient ingredient = IngredientRepo.findById(id).get();

        IngredientRepo.delete(ingredient);

        return "redirect:/ingredients";
    }

}
