package org.pizzeria.java.spring_la_mia_pizzeria_crud.controllers;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/pizza")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/")
    public ResponseEntity<Pizza> index() {
        ResponseEntity<Pizza> = pizzaService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {

    }

    @PostMapping("/create")
    public void store(@ResponseBody pizza) {

    }

    @PutMapping("edit")
    public void edit(@ResponseBody pizza){

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

    }

}
