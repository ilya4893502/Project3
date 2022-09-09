package com.spring.project.dto.app;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class PlayerDTO {

    private String playerName;
    private String positionPlayer;

    @DateTimeFormat(pattern = "dd/MM/yyyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirthPlayer;
    private String playerCountry;
    private int redCards;
    private int yellowCards;
    private int goalsScored;
    private int assists;
    private int playerNumber;
    private String injury;
    private int games;
    private byte[] playerImage;
    private MultipartFile file;


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPositionPlayer() {
        return positionPlayer;
    }

    public void setPositionPlayer(String positionPlayer) {
        this.positionPlayer = positionPlayer;
    }

    public Date getDateOfBirthPlayer() {
        return dateOfBirthPlayer;
    }

    public void setDateOfBirthPlayer(Date dateOfBirthPlayer) {
        this.dateOfBirthPlayer = dateOfBirthPlayer;
    }

    public String getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(String playerCountry) {
        this.playerCountry = playerCountry;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getInjury() {
        return injury;
    }

    public void setInjury(String injury) {
        this.injury = injury;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public byte[] getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(byte[] playerImage) {
        this.playerImage = playerImage;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
