package pl.kaminski.reciperpro.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FavouriteRecipe {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;

    public FavouriteRecipe() {
    }

    public FavouriteRecipe(String title, String href, String ingredients, String thumbnail) {
        this.title = title;
        this.href = href;
        this.ingredients = ingredients;
        this.thumbnail = thumbnail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
