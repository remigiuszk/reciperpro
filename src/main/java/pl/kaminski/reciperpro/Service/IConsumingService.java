package pl.kaminski.reciperpro.Service;

import pl.kaminski.reciperpro.Model.Result;

import java.util.List;

public interface IConsumingService {
    List<Result> getRecipes(String ingredient, String title);
}
