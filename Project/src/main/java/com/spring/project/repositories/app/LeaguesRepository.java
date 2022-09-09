package com.spring.project.repositories.app;

import com.spring.project.models.app.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaguesRepository extends JpaRepository<League, Integer> {

    Optional<League> findByLeagueName(String leagueName);
}
