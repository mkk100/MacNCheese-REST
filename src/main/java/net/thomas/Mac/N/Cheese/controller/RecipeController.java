package net.thomas.Mac.N.Cheese.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import net.thomas.Mac.N.Cheese.dto.RecipeDto;
import net.thomas.Mac.N.Cheese.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {
    private RecipeService recipeService;
    private final ObjectMapper objectMapper = new ObjectMapper();


    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @CrossOrigin("http://localhost:3000/Post")
    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(HttpServletRequest request) throws IOException {
        // Read and parse the raw payload
        BufferedReader reader = request.getReader();
        StringBuilder payload = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            payload.append(line);
        }

        // Convert payload to JsonNode to extract "data"
        JsonNode jsonNode = objectMapper.readTree(payload.toString());
        JsonNode dataNode = jsonNode.get("data");

        // Map the "data" node to RecipeDto
        RecipeDto recipeDto = objectMapper.treeToValue(dataNode, RecipeDto.class);

        // Log and process the DTO
        logger.info("RecipeDto: " + recipeDto);

        // Call the service to create the recipe
        RecipeDto savedRecipe = recipeService.createRecipe(recipeDto);

        return ResponseEntity.ok(savedRecipe);
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
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable("name") String name, @RequestBody String payload) {
        try {
            // Parse the JSON payload
            JsonNode rootNode = objectMapper.readTree(payload);
            JsonNode dataNode = rootNode.get("data");

            // Convert the 'data' node to RecipeDto
            RecipeDto recipeDto = objectMapper.treeToValue(dataNode, RecipeDto.class);

            RecipeDto updatedRecipe = recipeService.updateRecipe(name, recipeDto);
            return ResponseEntity.ok(updatedRecipe);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("{name}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("name") String name) {
        recipeService.deleteRecipe(name);
        return ResponseEntity.ok("Recipe Deleted");
    }

}
