package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.MovieRepository;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ApiException("Movie is not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }


    @Override
    public Movie updateMovie(Movie movie) {
        HollywoodValidation.checkMovie(movie);
        Movie movieFound = movieRepository.findById(movie.getId())
                .orElseThrow(() -> new ApiException("Actor bulunamadı! ID: " + movie.getId(), HttpStatus.NOT_FOUND));

        movieFound.setName(movie.getName());
        movieFound.setDirectorName(movie.getDirectorName());
        movieFound.setRating(movie.getRating());
        movieFound.setReleaseDate(movie.getReleaseDate());
        return movieFound;
    }
}
