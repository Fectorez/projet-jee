package com.esgi.projetjee.dao;

import com.esgi.projetjee.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    @Async
    CompletableFuture<Event> findByUuid(String uuid);
}
