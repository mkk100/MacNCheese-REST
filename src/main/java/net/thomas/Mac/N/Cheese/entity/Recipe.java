package net.thomas.Mac.N.Cheese.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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