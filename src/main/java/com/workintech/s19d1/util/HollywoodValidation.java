package com.workintech.s19d1.util;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class HollywoodValidation {
    public static void checkActor(Actor actor) {
        if (actor == null) {
            throw new ApiException("Actor nesnesi null!", HttpStatus.BAD_REQUEST);
        }
        if (actor.getFirstName() == null) {
            throw new ApiException("First name eksik!", HttpStatus.BAD_REQUEST);
        }
        if (actor.getLastName() == null) {
            throw new ApiException("Last name eksik!", HttpStatus.BAD_REQUEST);
        }
        if (actor.getGender() == null) {
            throw new ApiException("Gender eksik!", HttpStatus.BAD_REQUEST);
        }
        if (actor.getBirthDate() == null) {
            throw new ApiException("Birth date eksik!", HttpStatus.BAD_REQUEST);
        }
    }


    public static void checkMovie(Movie movie){
        if(movie == null || movie.getName() == null ||movie.getDirectorName() == null || movie.getRating() <= 0 ) {
            throw new ApiException("Eksik veya hatalı veri!", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkId(Long id) {
        if (id == null || id <= 0) {
            throw new ApiException("Geçersiz ID: " + id, HttpStatus.BAD_REQUEST);
        }
    }
}
