package com.example.railwayticketreservation.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "m_train")
public class Train {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer noSl;

    @Column(nullable = false)
    private Integer no3rdAc;

    @Column(nullable = false)
    private Integer no2ndAc;

    @Column(nullable = false)
    private Integer no1stAc;

    @OneToMany(mappedBy = "train")
    private Set<Route> route;

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

    public Integer getNoSl() {
        return noSl;
    }

    public void setNoSl(Integer noSl) {
        this.noSl = noSl;
    }

    public Integer getNo3rdAc() {
        return no3rdAc;
    }

    public void setNo3rdAc(Integer no3rdAc) {
        this.no3rdAc = no3rdAc;
    }

    public Integer getNo2ndAc() {
        return no2ndAc;
    }

    public void setNo2ndAc(Integer no2ndAc) {
        this.no2ndAc = no2ndAc;
    }

    public Integer getNo1stAc() {
        return no1stAc;
    }

    public void setNo1stAc(Integer no1stAc) {
        this.no1stAc = no1stAc;
    }

    public Set<Route> getRoute() {
        return route;
    }

    public void setRoute(Set<Route> route) {
        this.route = route;
    }
}
