package com.example.railwayticketreservation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "t_user_password")
public class Login {

    @Id
    @Column(nullable = false, updatable = false, length = 20)
    private String password;

    @OneToOne
    @JoinColumn(name = "id", nullable = false, unique = true)
    private Register id;

    public Login() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Register getId() {
        return id;
    }

    public void setId(Register id) {
        this.id = id;
    }

}