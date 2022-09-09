package com.spring.project.models.app;

import com.spring.project.models.account.Person;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_country")
    private String teamCountry;

    @Column(name = "number_of_points")
    private int numberOfPoints;

    @Column(name = "number_of_games")
    private int numberOfGames;

    @Column(name = "number_of_wins")
    private int numberOfWins;

    @Column(name = "number_of_defeats")
    private int numberOfDefeats;

    @Column(name = "number_of_draws")
    private int numberOfDraws;

    @Column(name = "goals_scored")
    private int goalsScored;

    @Column(name = "goals_conceded")
    private int goalsConceded;

    @Column(name = "stadium")
    private String stadium;

    @OneToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "coach_id")
    private Coach coach;

    @ManyToMany(mappedBy = "teams")
    private List<Player> players;

    @ManyToMany(mappedBy = "teams")
    private List<League> leagues;

    @ManyToMany
    @JoinTable(
            name = "Team_Match",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Match> matches;

    @ManyToMany(mappedBy = "teams")
    private List<Person> people;


    public Team(){}

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCountry() {
        return teamCountry;
    }

    public void setTeamCountry(String teamCountry) {
        this.teamCountry = teamCountry;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfDefeats() {
        return numberOfDefeats;
    }

    public void setNumberOfDefeats(int numberOfDefeats) {
        this.numberOfDefeats = numberOfDefeats;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamId == team.teamId && numberOfPoints == team.numberOfPoints && numberOfGames == team.numberOfGames && numberOfWins == team.numberOfWins && numberOfDefeats == team.numberOfDefeats && numberOfDraws == team.numberOfDraws && goalsScored == team.goalsScored && goalsConceded == team.goalsConceded && Objects.equals(teamName, team.teamName) && Objects.equals(teamCountry, team.teamCountry) && Objects.equals(stadium, team.stadium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, teamName, teamCountry, numberOfPoints, numberOfGames, numberOfWins, numberOfDefeats, numberOfDraws, goalsScored, goalsConceded, stadium);
    }
}
