package com.spring.project.repositories.app;

import com.spring.project.models.app.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachesRepository extends JpaRepository<Coach, Integer> {

    Optional<Coach> findByCoachName(String coachName);
}
