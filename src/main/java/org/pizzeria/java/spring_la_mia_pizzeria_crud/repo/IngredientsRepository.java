package org.pizzeria.java.spring_la_mia_pizzeria_crud.repo;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {

}
