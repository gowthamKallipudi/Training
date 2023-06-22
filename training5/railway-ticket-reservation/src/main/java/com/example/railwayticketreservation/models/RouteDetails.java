package com.example.railwayticketreservation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "m_route_details")
public class RouteDetails {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqNo;

    @Column(nullable = false)
    private Integer haltInMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getHaltInMinutes() {
        return haltInMinutes;
    }

    public void setHaltInMinutes(Integer haltInMinutes) {
        this.haltInMinutes = haltInMinutes;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
