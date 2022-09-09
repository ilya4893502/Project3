package com.spring.project.models.app;

import com.spring.project.models.account.Person;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int matchId;

    @Column(name = "judge")
    private String judge;

    @Column(name = "match_status")
    private String matchStatus;

    @Column(name = "number_of_times")
    private int numberOfTimes;

    @Column(name = "goals_team_one")
    private int goalsTeamOne;

    @Column(name = "goals_team_two")
    private int goalsTeamTwo;

    @Column(name = "ball_possession_team_one")
    private int ballPossessionTeamOne;

    @Column(name = "ball_possession_team_two")
    private int ballPossessionTeamTwo;

    @Column(name = "goal_attempts_team_one")
    private int goalAttemptsTeamOne;

    @Column(name = "goal_attempts_team_two")
    private int goalAttemptsTeamTwo;

    @Column(name = "shots_on_goal_team_one")
    private int shotsOnGoalTeamOne;

    @Column(name = "shots_on_goal_team_two")
    private int shotsOnGoalTeamTwo;

    @Column(name = "free_kicks_team_one")
    private int freeKicksTeamOne;

    @Column(name = "free_kicks_team_two")
    private int freeKicksTeamTwo;

    @Column(name = "corners_team_one")
    private int cornersTeamOne;

    @Column(name = "corners_team_two")
    private int cornersTeamTwo;

    @Column(name = "offsides_team_one")
    private int offsidesTeamOne;

    @Column(name = "offsides_team_two")
    private int offsidesTeamTwo;

    @Column(name = "red_cards_team_one")
    private int redCardsTeamOne;

    @Column(name = "red_cards_team_two")
    private int redCardsTeamTwo;

    @Column(name = "yellow_cards_team_one")
    private int yellowCardsTeamOne;

    @Column(name = "yellow_cards_team_two")
    private int yellowCardsTeamTwo;

    @Column(name = "tactics_team_one")
    private String tacticsTeamOne;

    @Column(name = "tactics_team_two")
    private String tacticsTeamTwo;

    @Column(name = "tour")
    private int tour;

    @Column(name = "match_stadium")
    private String matchStadium;

    @Column(name = "name_team_one")
    private String nameTeamOne;

    @Column(name = "name_team_two")
    private String nameTeamTwo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "match_date")
    private Date matchDate;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "match_time")
    private Date matchTime;

    @Column(name = "play_time")
    private int playTime;

    @ManyToMany(mappedBy = "matches")
    private List<Team> teams;

    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "league_id")
    private League league;

    @ManyToMany(mappedBy = "matches")
    private List<Player> players;


    public Match(){}

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public int getNumberOfTimes() {
        return numberOfTimes;
    }

    public void setNumberOfTimes(int numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    public int getGoalsTeamOne() {
        return goalsTeamOne;
    }

    public void setGoalsTeamOne(int goalsTeamOne) {
        this.goalsTeamOne = goalsTeamOne;
    }

    public int getGoalsTeamTwo() {
        return goalsTeamTwo;
    }

    public void setGoalsTeamTwo(int goalsTeamTwo) {
        this.goalsTeamTwo = goalsTeamTwo;
    }

    public int getBallPossessionTeamOne() {
        return ballPossessionTeamOne;
    }

    public void setBallPossessionTeamOne(int ballPossessionTeamOne) {
        this.ballPossessionTeamOne = ballPossessionTeamOne;
    }

    public int getBallPossessionTeamTwo() {
        return ballPossessionTeamTwo;
    }

    public void setBallPossessionTeamTwo(int ballPossessionTeamTwo) {
        this.ballPossessionTeamTwo = ballPossessionTeamTwo;
    }

    public int getGoalAttemptsTeamOne() {
        return goalAttemptsTeamOne;
    }

    public void setGoalAttemptsTeamOne(int goalAttemptsTeamOne) {
        this.goalAttemptsTeamOne = goalAttemptsTeamOne;
    }

    public int getGoalAttemptsTeamTwo() {
        return goalAttemptsTeamTwo;
    }

    public void setGoalAttemptsTeamTwo(int goalAttemptsTeamTwo) {
        this.goalAttemptsTeamTwo = goalAttemptsTeamTwo;
    }

    public int getShotsOnGoalTeamOne() {
        return shotsOnGoalTeamOne;
    }

    public void setShotsOnGoalTeamOne(int shotsOnGoalTeamOne) {
        this.shotsOnGoalTeamOne = shotsOnGoalTeamOne;
    }

    public int getShotsOnGoalTeamTwo() {
        return shotsOnGoalTeamTwo;
    }

    public void setShotsOnGoalTeamTwo(int shotsOnGoalTeamTwo) {
        this.shotsOnGoalTeamTwo = shotsOnGoalTeamTwo;
    }

    public int getFreeKicksTeamOne() {
        return freeKicksTeamOne;
    }

    public void setFreeKicksTeamOne(int freeKicksTeamOne) {
        this.freeKicksTeamOne = freeKicksTeamOne;
    }

    public int getFreeKicksTeamTwo() {
        return freeKicksTeamTwo;
    }

    public void setFreeKicksTeamTwo(int freeKicksTeamTwo) {
        this.freeKicksTeamTwo = freeKicksTeamTwo;
    }

    public int getCornersTeamOne() {
        return cornersTeamOne;
    }

    public void setCornersTeamOne(int cornersTeamOne) {
        this.cornersTeamOne = cornersTeamOne;
    }

    public int getCornersTeamTwo() {
        return cornersTeamTwo;
    }

    public void setCornersTeamTwo(int cornersTeamTwo) {
        this.cornersTeamTwo = cornersTeamTwo;
    }

    public int getOffsidesTeamOne() {
        return offsidesTeamOne;
    }

    public void setOffsidesTeamOne(int offsidesTeamOne) {
        this.offsidesTeamOne = offsidesTeamOne;
    }

    public int getOffsidesTeamTwo() {
        return offsidesTeamTwo;
    }

    public void setOffsidesTeamTwo(int offsidesTeamTwo) {
        this.offsidesTeamTwo = offsidesTeamTwo;
    }

    public int getRedCardsTeamOne() {
        return redCardsTeamOne;
    }

    public void setRedCardsTeamOne(int redCardsTeamOne) {
        this.redCardsTeamOne = redCardsTeamOne;
    }

    public int getRedCardsTeamTwo() {
        return redCardsTeamTwo;
    }

    public void setRedCardsTeamTwo(int redCardsTeamTwo) {
        this.redCardsTeamTwo = redCardsTeamTwo;
    }

    public int getYellowCardsTeamOne() {
        return yellowCardsTeamOne;
    }

    public void setYellowCardsTeamOne(int yellowCardsTeamOne) {
        this.yellowCardsTeamOne = yellowCardsTeamOne;
    }

    public int getYellowCardsTeamTwo() {
        return yellowCardsTeamTwo;
    }

    public void setYellowCardsTeamTwo(int yellowCardsTeamTwo) {
        this.yellowCardsTeamTwo = yellowCardsTeamTwo;
    }

    public String getTacticsTeamOne() {
        return tacticsTeamOne;
    }

    public void setTacticsTeamOne(String tacticsTeamOne) {
        this.tacticsTeamOne = tacticsTeamOne;
    }

    public String getTacticsTeamTwo() {
        return tacticsTeamTwo;
    }

    public void setTacticsTeamTwo(String tacticsTeamTwo) {
        this.tacticsTeamTwo = tacticsTeamTwo;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public String getMatchStadium() {
        return matchStadium;
    }

    public void setMatchStadium(String matchStadium) {
        this.matchStadium = matchStadium;
    }

    public String getNameTeamOne() {
        return nameTeamOne;
    }

    public void setNameTeamOne(String nameTeamOne) {
        this.nameTeamOne = nameTeamOne;
    }

    public String getNameTeamTwo() {
        return nameTeamTwo;
    }

    public void setNameTeamTwo(String nameTeamTwo) {
        this.nameTeamTwo = nameTeamTwo;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return matchId == match.matchId && numberOfTimes == match.numberOfTimes && goalsTeamOne == match.goalsTeamOne && goalsTeamTwo == match.goalsTeamTwo && ballPossessionTeamOne == match.ballPossessionTeamOne && ballPossessionTeamTwo == match.ballPossessionTeamTwo && goalAttemptsTeamOne == match.goalAttemptsTeamOne && goalAttemptsTeamTwo == match.goalAttemptsTeamTwo && shotsOnGoalTeamOne == match.shotsOnGoalTeamOne && shotsOnGoalTeamTwo == match.shotsOnGoalTeamTwo && freeKicksTeamOne == match.freeKicksTeamOne && freeKicksTeamTwo == match.freeKicksTeamTwo && cornersTeamOne == match.cornersTeamOne && cornersTeamTwo == match.cornersTeamTwo && offsidesTeamOne == match.offsidesTeamOne && offsidesTeamTwo == match.offsidesTeamTwo && redCardsTeamOne == match.redCardsTeamOne && redCardsTeamTwo == match.redCardsTeamTwo && yellowCardsTeamOne == match.yellowCardsTeamOne && yellowCardsTeamTwo == match.yellowCardsTeamTwo && tour == match.tour && playTime == match.playTime && Objects.equals(judge, match.judge) && Objects.equals(matchStatus, match.matchStatus) && Objects.equals(tacticsTeamOne, match.tacticsTeamOne) && Objects.equals(tacticsTeamTwo, match.tacticsTeamTwo) && Objects.equals(matchStadium, match.matchStadium) && Objects.equals(nameTeamOne, match.nameTeamOne) && Objects.equals(nameTeamTwo, match.nameTeamTwo) && Objects.equals(matchDate, match.matchDate) && Objects.equals(matchTime, match.matchTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, judge, matchStatus, numberOfTimes, goalsTeamOne, goalsTeamTwo, ballPossessionTeamOne, ballPossessionTeamTwo, goalAttemptsTeamOne, goalAttemptsTeamTwo, shotsOnGoalTeamOne, shotsOnGoalTeamTwo, freeKicksTeamOne, freeKicksTeamTwo, cornersTeamOne, cornersTeamTwo, offsidesTeamOne, offsidesTeamTwo, redCardsTeamOne, redCardsTeamTwo, yellowCardsTeamOne, yellowCardsTeamTwo, tacticsTeamOne, tacticsTeamTwo, tour, matchStadium, nameTeamOne, nameTeamTwo, matchDate, matchTime, playTime);
    }
}
