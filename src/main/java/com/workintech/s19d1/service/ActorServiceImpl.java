package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.ActorRepository;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ActorServiceImpl implements ActorService{

    private final ActorRepository actorRepository;

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ApiException("actor is not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void delete(Actor actor) {
        actorRepository.delete(actor);
    }

    @Override
    public Actor updateActor(Actor actor) {
        HollywoodValidation.checkActor(actor);
        Actor actorFound = actorRepository.findById(actor.getId())
                .orElseThrow(() -> new ApiException("Actor bulunamadı! ID: " + actor.getId(), HttpStatus.NOT_FOUND));

        actorFound.setFirstName(actor.getFirstName());
        actorFound.setLastName(actor.getLastName());
        actorFound.setGender(actor.getGender());
        actorFound.setBirthDate(actor.getBirthDate());
        return actorFound;
    }
}
