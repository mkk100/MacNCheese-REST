package net.thomas.Mac.N.Cheese.mapper;

import net.thomas.Mac.N.Cheese.dto.RecipeDto;
import net.thomas.Mac.N.Cheese.entity.Recipe;

public class RecipeMapper {
    public static RecipeDto maptToRecipeDto(Recipe recipe) {
        return new RecipeDto(
                recipe.getName(),
                recipe.getIngredients(),
                recipe.getDirections()
        );
    }

    public static Recipe maptToRecipe(RecipeDto recipeDto) {
        return new Recipe(
                recipeDto.getName(),
                recipeDto.getIngredients(),
                recipeDto.getDirections()
        );
    }
}
