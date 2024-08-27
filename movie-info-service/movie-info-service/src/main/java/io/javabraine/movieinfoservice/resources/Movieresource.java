package io.javabraine.movieinfoservice.resources;

import io.javabraine.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class Movieresource {
    @RequestMapping("/{movieId}")
    public Movie getmovieInfo(@PathVariable("movieId") String movieId)
    {
    return  new Movie(movieId,"Neelendra Movie");
    }
}
