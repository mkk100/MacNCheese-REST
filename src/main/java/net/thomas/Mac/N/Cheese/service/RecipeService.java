package net.thomas.Mac.N.Cheese.service;

import net.thomas.Mac.N.Cheese.dto.RecipeDto;

import java.util.List;

public interface RecipeService {
    RecipeDto createRecipe(RecipeDto recipeDto);
    RecipeDto getRecipeByName(String name);
    List<RecipeDto> getAllRecipes();
    RecipeDto updateRecipe(String name, RecipeDto recipeDto);
    void deleteRecipe(String name);
}
