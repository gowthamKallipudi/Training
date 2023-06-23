package com.example.railwayticketreservation.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "m_route")
public class Route {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer startDayOfTheWeek;

    @OneToMany(mappedBy = "route")
    private Set<RouteDetails> routeDetails;

    @OneToMany(mappedBy = "route")
    private Set<Bookings> bookings;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartDayOfTheWeek() {
        return startDayOfTheWeek;
    }

    public void setStartDayOfTheWeek(Integer startDayOfTheWeek) {
        this.startDayOfTheWeek = startDayOfTheWeek;
    }

    public Set<RouteDetails> getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(Set<RouteDetails> routeDetails) {
        this.routeDetails = routeDetails;
    }

    public Set<Bookings> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Bookings> bookings) {
        this.bookings = bookings;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
