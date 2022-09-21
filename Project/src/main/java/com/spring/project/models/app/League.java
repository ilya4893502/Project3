package com.spring.project.models.app;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "League")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private int leagueId;

    @Column(name = "league_name")
    private String leagueName;

    @Column(name = "league_country")
    private String leagueCountry;

    @Column(name = "season")
    private String season;

    @Column(name = "league_image_name")
    private String leagueImageName;

    @Lob
    @Column(name = "league_image")
    private byte[] leagueImage;

    @OneToMany(mappedBy = "league")
    private List<Match> matches;

    @ManyToMany
    @JoinTable(
            name = "League_Team",
            joinColumns = @JoinColumn(name = "league_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;


    public League(){}

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueCountry() {
        return leagueCountry;
    }

    public void setLeagueCountry(String leagueCountry) {
        this.leagueCountry = leagueCountry;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getLeagueImageName() {
        return leagueImageName;
    }

    public void setLeagueImageName(String leagueImageName) {
        this.leagueImageName = leagueImageName;
    }

    public byte[] getLeagueImage() {
        return leagueImage;
    }

    public void setLeagueImage(byte[] leagueImage) {
        this.leagueImage = leagueImage;
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
        League league = (League) o;
        return leagueId == league.leagueId && Objects.equals(leagueName, league.leagueName) && Objects.equals(leagueCountry, league.leagueCountry) && Objects.equals(season, league.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueId, leagueName, leagueCountry, season);
    }
}
