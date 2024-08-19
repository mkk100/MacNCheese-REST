package net.thomas.Mac.N.Cheese.controller;

import lombok.AllArgsConstructor;
import net.thomas.Mac.N.Cheese.dto.RecipeDto;
import net.thomas.Mac.N.Cheese.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {
    private RecipeService recipeService;
    // Add Employee
    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto) {
        RecipeDto savedRecipe = recipeService.createRecipe(recipeDto);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }
    @GetMapping("{name}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable("name") String name) {
        RecipeDto recipeDto = recipeService.getRecipeByName(name);
        return ResponseEntity.ok(recipeDto);
    }
    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> recipeDtos = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipeDtos);
    }
    @PutMapping("{name}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable("name") String name, @RequestBody RecipeDto recipeDto) {
        RecipeDto updatedRecipe = recipeService.updateRecipe(name, recipeDto);
        return ResponseEntity.ok(updatedRecipe);
    }
    @DeleteMapping("{name}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("name") String name) {
        recipeService.deleteRecipe(name);
        return ResponseEntity.ok("Recipe Deleted");
    }

}
