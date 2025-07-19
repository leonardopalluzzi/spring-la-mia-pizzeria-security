package org.pizzeria.java.spring_la_mia_pizzeria_crud.controllers;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Promotion;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.PromotionRepository;
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
@RequestMapping("/promotions")
public class PromotionsController {

    @Autowired
    private PromotionRepository promotionRepo;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("promotion") Promotion promotion, BindingResult bindingResult,
            Model model) throws Exception {
        try {
            if (bindingResult.hasErrors()) {
                return "promotions/create";
            }

            if (promotion.getStartDate().isAfter(promotion.getEndDate())) {
                throw new Exception("Start date cannot be after end date");
            }

            promotionRepo.save(promotion);

            return "redirect:/menu/pizza/" + promotion.getPizza().getId();

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "promotions/create";
        }

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, Model model) {

        Promotion promotion = promotionRepo.findById(id).get();

        promotionRepo.deleteById(id);

        return "redirect:/menu/pizza/" + promotion.getPizza().getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {

        Promotion promotion = promotionRepo.findById(id).get();
        boolean edit = true;
        model.addAttribute("promotion", promotion);
        model.addAttribute("edit", edit);

        return "promotions/create";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("promotion") Promotion promotion,
            BindingResult bindingResult,
            Model model) throws Exception {

        try {
            if (bindingResult.hasErrors()) {
                return "promotions/create";
            }

            if (promotion.getStartDate().isAfter(promotion.getEndDate())) {
                throw new Exception("Start date cannot be after end date");
            }

            promotionRepo.save(promotion);

            return "redirect:/menu/pizza/" + promotion.getPizza().getId();

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "promotions/create";
        }
    }
}
