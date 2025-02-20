package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/actor")
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public List<Actor> getAllActor() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor getActor(@PathVariable long id) {
        HollywoodValidation.checkId(id);
        return actorService.findById(id);
    }

    @PostMapping
    public Actor addMovie(@RequestBody ActorRequest actorRequest) {
        Actor actor = actorRequest.getActor();
        HollywoodValidation.checkActor(actor); // ✅ Artık firstName null olmayacak

        actor.setMovies(actorRequest.getMovies()); // Filmleri ilişkilendir
        return actorService.save(actor);
    }

    @PutMapping("/{id}")
    public Actor update(@PathVariable("id") long id, @RequestBody Actor actor) {
        actorService.updateActor(actor);
        return actor;
    }

    @DeleteMapping("/{id}")
    public Actor remove(@PathVariable long id){
        Actor foundActor = actorService.findById(id);
        actorService.delete(foundActor);
        return foundActor;
    }
}
