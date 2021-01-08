package pl.kaminski.reciperpro.Service;

import pl.kaminski.reciperpro.Model.FavouriteRecipe;

import java.util.List;

public interface IFavouriteRecipeService {
    List<FavouriteRecipe> getAllFavourites();
    Boolean addFavourite(FavouriteRecipe favouriteRecipe);
    Boolean deleteFavouriteById(long id);
}
