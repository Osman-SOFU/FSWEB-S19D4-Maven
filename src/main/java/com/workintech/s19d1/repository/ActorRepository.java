package com.workintech.s19d1.repository;

import com.workintech.s19d1.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query("SELECT a FROM Actor a WHERE a.firstName = :name")
    Optional<Actor> findByName(String name);
}
