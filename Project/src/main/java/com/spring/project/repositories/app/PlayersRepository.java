package com.spring.project.repositories.app;

import com.spring.project.models.app.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayersRepository extends JpaRepository<Player, Integer> {

    Optional<Player> findByPlayerName(String playerName);
}
