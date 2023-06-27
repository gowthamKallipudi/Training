package com.example.railwayticketreservation.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "t_booking")
@Table(name = "t_booking")
public class Bookings {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 3)
    private String coach;

    @Column(nullable = false)
    private Integer seatNo;

    @ManyToOne
    @JoinColumn(name = "source", nullable = false)
    private Station stationS;

    @ManyToOne
    @JoinColumn(name = "destination", nullable = false)
    private Station stationD;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Register user;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public Register getUser() {
        return user;
    }

    public void setUser(Register user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Station getStationS() {
        return stationS;
    }

    public void setStationS(Station stationS) {
        this.stationS = stationS;
    }

    public Station getStationD() {
        return stationD;
    }

    public void setStationD(Station stationD) {
        this.stationD = stationD;
    }
}
