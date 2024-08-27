package io.javabraine.moviecatalogservice.resources;

import io.javabraine.moviecatalogservice.models.CatalogItem;
import io.javabraine.moviecatalogservice.models.Movie;
import io.javabraine.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.javabraine.moviecatalogservice.models.CatalogItem;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatatlog(@PathVariable("userId") String userId){


        // get all rated movie IDs
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("5768",3)
        );
       return ratings.stream().map(rating ->{
          Movie movie =restTemplate.getForObject("http://localhost:8081/movies/" +rating.getMovieId(), Movie.class);
         /*    Movie movie =webClientBuilder.build()
           //          .get()
             //        .uri("http://localhost:8081/movies/" +rating.getMovieId())
               //      .retrieve()
                 //    .bodyToMono(Movie.class)
                     .block();
*/
               return new CatalogItem(movie.getName(),"Desc",rating.getRating());
       })
                .collect(Collectors.toList());
        // For each movie ID, call movie info service and get details
        // put them all together

    }
}
