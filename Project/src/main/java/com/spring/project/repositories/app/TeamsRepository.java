package com.spring.project.repositories.app;

import com.spring.project.models.app.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findByTeamName(String teamName);

    List<Team> findByTeamNameStartingWith(String startLetter);
}
