package net.thomas.Mac.N.Cheese.service.impl;

import lombok.AllArgsConstructor;
import net.thomas.Mac.N.Cheese.dto.RecipeDto;
import net.thomas.Mac.N.Cheese.entity.Recipe;
import net.thomas.Mac.N.Cheese.exception.ResourceNotFoundException;
import net.thomas.Mac.N.Cheese.mapper.RecipeMapper;
import net.thomas.Mac.N.Cheese.repository.RecipeRepo;
import net.thomas.Mac.N.Cheese.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeImpl implements RecipeService {
    private RecipeRepo recipeRepo;

    @Override
    public RecipeDto createRecipe(RecipeDto recipeDto) {
        Recipe recipe = RecipeMapper.maptToRecipe(recipeDto);
        if (recipe.getDirections() == null || recipe.getDirections().isEmpty()) {
            throw new IllegalArgumentException("Directions cannot be null or empty");
        }
        Recipe savedRecipe = recipeRepo.save(recipe);
        return RecipeMapper.maptToRecipeDto(savedRecipe);
    }

    @Override
    public RecipeDto getRecipeByName(String name) {
        Recipe recipe = recipeRepo.findById(name)
                .orElseThrow(()->new ResourceNotFoundException(name + " not found"));
        return RecipeMapper.maptToRecipeDto(recipe);
    }

    @Override
    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepo.findAll();
        return recipes.stream().map((recipe)->RecipeMapper.maptToRecipeDto(recipe))
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDto updateRecipe(String name, RecipeDto recipeDto) {
        Recipe recipe = recipeRepo.findById(name).orElseThrow(
                ()-> new ResourceNotFoundException(name +  " recipe not found")
        );
        recipe.setName(recipeDto.getName());
        recipe.setDirections(recipeDto.getDirections());
        recipe.setIngredients(recipeDto.getIngredients());
        Recipe updatedRecipe = recipeRepo.save(recipe);
        return RecipeMapper.maptToRecipeDto(updatedRecipe);
    }

    @Override
    public void deleteRecipe(String name) {
        Recipe recipe = recipeRepo.findById(name).orElseThrow(
                ()-> new ResourceNotFoundException(name + " recipe not found.")
        );
        recipeRepo.delete(recipe);
    }
}

