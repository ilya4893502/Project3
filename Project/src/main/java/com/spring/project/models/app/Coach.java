package com.spring.project.models.app;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Coach")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private int coachId;

    @Column(name = "coach_name")
    private String coachName;

    @Column(name = "coach_country")
    private String coachCountry;

    @DateTimeFormat(pattern = "dd/MM/yyyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth_coach")
    private Date dateOfBirthCoach;

    @OneToOne(mappedBy = "coach")
    private Team team;


    public Coach(){}

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachCountry() {
        return coachCountry;
    }

    public void setCoachCountry(String coachCountry) {
        this.coachCountry = coachCountry;
    }

    public Date getDateOfBirthCoach() {
        return dateOfBirthCoach;
    }

    public void setDateOfBirthCoach(Date dateOfBirthCoach) {
        this.dateOfBirthCoach = dateOfBirthCoach;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return coachId == coach.coachId && Objects.equals(coachName, coach.coachName) && Objects.equals(coachCountry, coach.coachCountry) && Objects.equals(dateOfBirthCoach, coach.dateOfBirthCoach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coachId, coachName, coachCountry, dateOfBirthCoach);
    }
}
