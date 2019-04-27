package com.esgi.projetjee.dao;

import com.esgi.projetjee.model.Event;
import com.esgi.projetjee.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface InterestRepository extends JpaRepository<Interest, Integer> {

}
