package com.spring.project.models.app;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "position_player")
    private String positionPlayer;

    @DateTimeFormat(pattern = "dd/MM/yyyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth_player")
    private Date dateOfBirthPlayer;

    @Column(name = "player_country")
    private String playerCountry;

    @Column(name = "red_cards")
    private int redCards;

    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "goals_scored")
    private int goalsScored;

    @Column(name = "assists")
    private int assists;

    @Column(name = "player_number")
    private int playerNumber;

    @Column(name = "injury")
    private String injury;

    @Column(name = "games")
    private int games;

    @Lob
    @Column(name = "player_image")
    private byte[] playerImage;

    @ManyToMany
    @JoinTable(
            name = "Player_Team",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;

    @ManyToMany
    @JoinTable(
            name = "Player_Match",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Match> matches;


    public Player(){}


    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId == player.playerId && redCards == player.redCards && yellowCards == player.yellowCards && goalsScored == player.goalsScored && assists == player.assists && playerNumber == player.playerNumber && games == player.games && Objects.equals(playerName, player.playerName) && Objects.equals(positionPlayer, player.positionPlayer) && Objects.equals(dateOfBirthPlayer, player.dateOfBirthPlayer) && Objects.equals(playerCountry, player.playerCountry) && Objects.equals(injury, player.injury);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, playerName, positionPlayer, dateOfBirthPlayer, playerCountry, redCards, yellowCards, goalsScored, assists, playerNumber, injury, games);
    }
}
