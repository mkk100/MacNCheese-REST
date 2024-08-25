package net.thomas.Mac.N.Cheese.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(NON_DEFAULT)
@Table(name="macNcheese")
public class Recipe {
    @Id
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @Column(name="ingredients", nullable=false)
    private String ingredients;
    @Column(name="directions", nullable = false)
    private String directions;
}