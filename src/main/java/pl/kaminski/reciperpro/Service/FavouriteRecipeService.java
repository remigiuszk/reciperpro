package pl.kaminski.reciperpro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaminski.reciperpro.Model.FavouriteRecipe;
import pl.kaminski.reciperpro.Repo.RecipeRepo;

import java.util.List;

@Service
public class FavouriteRecipeService implements IFavouriteRecipeService {
    private RecipeRepo recipeRepo;

    @Autowired
    public FavouriteRecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Override
    public List<FavouriteRecipe> getAllFavourites() {
        List<FavouriteRecipe> favouriteRecipes = recipeRepo.findAll();
        return favouriteRecipes;
    }

    @Override
    public Boolean addFavourite(FavouriteRecipe favouriteRecipe) {
        try {
            recipeRepo.save(favouriteRecipe);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteFavouriteById(long id) {
        try {
            recipeRepo.deleteById(id);
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }
}
