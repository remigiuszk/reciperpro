package pl.kaminski.reciperpro.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kaminski.reciperpro.Model.FavouriteRecipe;

public interface RecipeRepo extends JpaRepository<FavouriteRecipe, Long> {
}
