package com.esgi.projetjee.repository;

import com.esgi.projetjee.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {

    @Query(value = "from Interest i where i.name in :interestsNames")
    List<Interest> findWhereNameIsIn(@Param("interestsNames") String[] interestsNames);
}
