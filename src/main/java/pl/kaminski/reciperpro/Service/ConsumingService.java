package pl.kaminski.reciperpro.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kaminski.reciperpro.Model.Example;
import pl.kaminski.reciperpro.Model.Result;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ConsumingService implements IConsumingService {

    @Value("${api.url}")
    private String apiUrl;

    @Override
    public List<Result> getRecipes(String ingredient, String title) {
        String apiUrlModified = apiUrl;
        if (!ingredient.isEmpty() && !title.isEmpty()) {
            apiUrlModified = apiUrlModified.concat("?i=").concat(ingredient.trim()).concat("&q=").concat(title.trim());
        } else {
            if (!ingredient.isEmpty()) {
                apiUrlModified += "?i=" + ingredient.trim();
            } else {
                if (!title.isEmpty()) {
                    apiUrlModified += "?q=" + title.trim();
                }
            }
        }
        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(apiUrlModified);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        Example example = restTemplate.getForObject(url, Example.class);
        if (example != null && example.getResults() != null) {
            return new ArrayList<Result>(example.getResults());
        } else
            return null;
    }
}
