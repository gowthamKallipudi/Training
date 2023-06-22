package com.example.railwayticketreservation.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "m_station")
public class Station {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "station")
    private Set<RouteDetails> station;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RouteDetails> getStation() {
        return station;
    }

    public void setStation(Set<RouteDetails> station) {
        this.station = station;
    }
}
