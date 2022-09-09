package com.spring.project.repositories.app;

import com.spring.project.models.app.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchesRepository extends JpaRepository<Match, Integer> {

    Optional<Match> findByMatchId(int matchId);

    List<Match> findByNameTeamOneAndNameTeamTwo(String nameTeamOne, String nameTeamTwo);
}
