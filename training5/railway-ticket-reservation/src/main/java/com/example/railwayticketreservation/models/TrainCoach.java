package com.example.railwayticketreservation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "m_train_coach_info")
public class TrainCoach {
    @Id
    @Column(nullable = false, updatable = false, length = 2)
    private String coach;

    @Column(nullable = false)
    private Integer noOfSeats;

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
}
