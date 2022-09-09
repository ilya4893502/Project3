package com.spring.project.dto.app;

import com.spring.project.models.app.League;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;

public class MatchDTO {

    private int matchId;
    private String judge;
    private int numberOfTimes;
    private int goalsTeamOne;
    private int goalsTeamTwo;
    private int ballPossessionTeamOne;
    private int ballPossessionTeamTwo;
    private int goalAttemptsTeamOne;
    private int goalAttemptsTeamTwo;
    private int shotsOnGoalTeamOne;
    private int shotsOnGoalTeamTwo;
    private int freeKicksTeamOne;
    private int freeKicksTeamTwo;
    private int cornersTeamOne;
    private int cornersTeamTwo;
    private int offsidesTeamOne;
    private int offsidesTeamTwo;
    private int redCardsTeamOne;
    private int redCardsTeamTwo;
    private int yellowCardsTeamOne;
    private int yellowCardsTeamTwo;
    private String tacticsTeamOne;
    private String tacticsTeamTwo;
    private int tour;
    private String matchStadium;
    private String nameTeamOne;
    private String nameTeamTwo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date matchDate;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date matchTime;
    private int playTime;


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
}
