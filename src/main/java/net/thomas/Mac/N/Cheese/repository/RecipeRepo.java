package net.thomas.Mac.N.Cheese.repository;

import net.thomas.Mac.N.Cheese.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe,String> {

}
