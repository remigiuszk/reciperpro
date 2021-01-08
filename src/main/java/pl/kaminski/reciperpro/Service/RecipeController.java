package pl.kaminski.reciperpro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kaminski.reciperpro.Model.FavouriteRecipe;
import pl.kaminski.reciperpro.Model.Result;

import java.util.List;

@Controller
public class RecipeController implements IRecipeController {
    private IConsumingService consumingService;
    private IFavouriteRecipeService favouriteService;

    @Autowired
    public RecipeController(IConsumingService consumingService, IFavouriteRecipeService favouriteService) {
        this.consumingService = consumingService;
        this.favouriteService = favouriteService;
    }

    @EventListener(ApplicationReadyEvent.class)
    @RequestMapping("/")
    public String defaultRedirect() {
        return "main";
    }

    @RequestMapping("/redirectToMain")
    public String redirectToMain() {
        return "redirect:/";
    }

    @RequestMapping("/searched")
    public String FindRecipes(Model model, @RequestParam(value = "ingredient") String ingredient, @RequestParam(value = "title") String title) {
        model.addAttribute("resultList", consumingService.getRecipes(ingredient, title));
        return "searched";
    }

    @RequestMapping("/savefavourite")
    public String SaveFavourite(@RequestParam("title") String title, @RequestParam("ingredients") String ingredients, @RequestParam("href") String href, @RequestParam("thumbnail") String thumbnail) {
        FavouriteRecipe favouriteRecipe = new FavouriteRecipe(title, href, ingredients, thumbnail);
        favouriteService.addFavourite(favouriteRecipe);
        return "redirect:/favourites";
    }

    @RequestMapping("/favourites")
    public String GetFavourites(Model model) {
        model.addAttribute("favouritesList", favouriteService.getAllFavourites());
        return "favourites";
    }

    @RequestMapping(value = "/favourites/delete/{id}", method = RequestMethod.GET)
    public String deleteGoal(@PathVariable Long id) {
        if (favouriteService.deleteFavouriteById(id)) {
            return "redirect:/favourites";
        } else {
            return "redirect:/operationfailed";
        }
    }
    @RequestMapping("/operationfailed")
    public String redirectToError(){
        return "error";
    }
}




