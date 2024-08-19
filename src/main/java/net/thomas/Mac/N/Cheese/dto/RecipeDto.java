package net.thomas.Mac.N.Cheese.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private String name;
    private String ingredients;
    private String directions;
}
