package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovie() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable long id) {
        HollywoodValidation.checkId(id);
        return movieService.findById(id);
    }

    @PostMapping
    public Movie addActor(@RequestBody Movie movie){
        HollywoodValidation.checkMovie(movie);
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable("id") long id, @RequestBody Movie movie) {
        movieService.updateMovie(movie);
        return movie;
    }

    @DeleteMapping("/{id}")
    public Movie remove(@PathVariable long id){
        Movie foundMovie = movieService.findById(id);
        movieService.delete(foundMovie);
        return foundMovie;
    }
}
