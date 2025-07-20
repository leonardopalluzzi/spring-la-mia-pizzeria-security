package org.pizzeria.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/pizza")
public class PizzaRestController {

    @Autowired 
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> index(@RequestParam(name = "title", required = false) String title) {
        List<Pizza> result = pizzaService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {

        Optional<Pizza> result = pizzaService.findById(id);

        if(result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result.get(), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(pizzaService.save(pizza), HttpStatus.OK);

    }

    @PutMapping("/edit")
    public ResponseEntity<Pizza> edit(@RequestBody Pizza pizza){
        return new ResponseEntity<Pizza>(pizzaService.save(pizza), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id) {
        Optional<Pizza> pizza = pizzaService.findById(id);

        if(pizza.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pizzaService.delete(pizza.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
