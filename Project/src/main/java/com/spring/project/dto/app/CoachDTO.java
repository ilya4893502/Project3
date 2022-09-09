package com.spring.project.dto.app;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class CoachDTO {

    private String coachName;
    private String coachCountry;

    @DateTimeFormat(pattern = "dd/MM/yyyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirthCoach;


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
}
